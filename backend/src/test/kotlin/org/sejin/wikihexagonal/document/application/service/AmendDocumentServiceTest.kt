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
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentSnapshotPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.DocumentId
import org.sejin.wikihexagonal.document.domain.documentSnapshot
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker

@DisplayName("AmendDocumentService")
internal class AmendDocumentServiceTest {
    private val readDocumentPort: ReadDocumentPort = mockk(relaxed = true)
    private val updateDocumentPort: UpdateDocumentPort = mockk(relaxed = true)
    private val createDocumentSnapshotPort: CreateDocumentSnapshotPort = mockk(relaxed = true)

    private val amendDocumentUseCase: AmendDocumentUseCase = AmendDocumentService(
        readDocumentPort,
        updateDocumentPort,
        createDocumentSnapshotPort,
    )

    @DisplayName("should amend a matching document with the given command")
    @Test
    fun shouldAmendMatchingDocumentWithGivenCommand() {
        // given
        val fakeDocumentId: DocumentId = faker.randomProvider.randomClassInstance()
        val fakeTitle: String = faker.heroesOfTheStorm.heroes()
        val fakeContent: String = faker.heroesOfTheStorm.quotes()

        every {
            readDocumentPort.loadDocument(eq(fakeDocumentId))
        }.returns(documentWithFullData())

        every {
            createDocumentSnapshotPort.createDocumentSnapshot(any())
        }.returns(
            documentSnapshot(
                title = fakeTitle,
                content = fakeContent,
            )
        )

        // when
        amendDocumentUseCase.amendDocument(
            AmendDocumentCommand(
                documentId = fakeDocumentId,
                title = fakeTitle,
                content = fakeContent
            )
        )

        // then
        verify {
            createDocumentSnapshotPort.createDocumentSnapshot(
                withArg {
                    it.title.shouldBeEqualTo(fakeTitle)
                    it.content.shouldBeEqualTo(fakeContent)
                }
            )

            updateDocumentPort.updateDocument(
                withArg {
                    it.shouldBeEqualTo(fakeDocumentId)
                },
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

        assertThrows<IllegalStateException> {
            amendDocumentUseCase.amendDocument(
                AmendDocumentCommand(
                    documentId = faker.randomProvider.randomClassInstance(),
                    title = faker.heroesOfTheStorm.heroes(),
                    content = faker.heroesOfTheStorm.quotes(),
                )
            )
        }
    }
}
