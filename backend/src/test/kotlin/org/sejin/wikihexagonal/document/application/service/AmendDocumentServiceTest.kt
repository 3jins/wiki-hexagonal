package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.`in`.dto.AmendDocumentCommand
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.DocumentId
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.memberWithFullData

@DisplayName("AmendDocumentService")
internal class AmendDocumentServiceTest {
    private val readDocumentPort: ReadDocumentPort = mockk(relaxed = true)
    private val updateDocumentPort: UpdateDocumentPort = mockk(relaxed = true)
    private val readMemberPort: ReadMemberPort = mockk(relaxed = true)

    private val amendDocumentUseCase: AmendDocumentUseCase = AmendDocumentService(
        readDocumentPort,
        updateDocumentPort,
        readMemberPort,
    )

    @DisplayName("should amend a matching document with the given command")
    @Test
    fun shouldAmendMatchingDocumentWithGivenCommand() {
        // given
        val fakeDocumentId: DocumentId = faker.randomProvider.randomClassInstance()
        val fakeTitle: String = faker.heroesOfTheStorm.heroes()
        val fakeContent: String = faker.heroesOfTheStorm.quotes()
        val fakeMemberId: Long = faker.random.nextLong()

        every {
            readDocumentPort.loadDocument(eq(fakeDocumentId))
        } returns documentWithFullData()

        every {
            readMemberPort.loadMember(any())
        } returns memberWithFullData()

        // when
        amendDocumentUseCase.amendDocument(
            AmendDocumentCommand(
                documentId = fakeDocumentId,
                title = fakeTitle,
                content = fakeContent,
                requestMemberId = fakeMemberId,
            )
        )

        // then
        verify {
            updateDocumentPort.updateDocument(
                withArg {
                    it.getLatestSnapshot().title.shouldBeEqualTo(fakeTitle)
                    it.getLatestSnapshot().content.shouldBeEqualTo(fakeContent)
                },
            )
        }
    }

    @DisplayName("should fail if target document does not exist")
    @Test
    fun shouldFailIfTargetDocumentDoesNotExist() {
        every {
            readDocumentPort.loadDocument(any())
        } returns null

        every {
            readMemberPort.loadMember(any())
        } returns memberWithFullData()

        assertThrows<IllegalStateException> {
            amendDocumentUseCase.amendDocument(
                AmendDocumentCommand(
                    documentId = faker.randomProvider.randomClassInstance(),
                    title = faker.heroesOfTheStorm.heroes(),
                    content = faker.heroesOfTheStorm.quotes(),
                    requestMemberId = faker.random.nextLong(),
                )
            )
        }
    }

    @DisplayName("should fail if member with given memberId does not exist")
    @Test
    fun shouldFailIfMemberWithGivenMemberIdDoesNotExist() {
        every {
            readDocumentPort.loadDocument(any())
        } returns documentWithFullData()

        every {
            readMemberPort.loadMember(any())
        } returns null

        assertThrows<IllegalStateException> {
            amendDocumentUseCase.amendDocument(
                AmendDocumentCommand(
                    documentId = faker.randomProvider.randomClassInstance(),
                    title = faker.heroesOfTheStorm.heroes(),
                    content = faker.heroesOfTheStorm.quotes(),
                    requestMemberId = faker.random.nextLong(),
                )
            )
        }
    }
}
