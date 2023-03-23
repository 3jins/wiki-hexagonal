package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentCommand
import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.AmendDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker

@DisplayName("AmendDocumentService")
internal class AmendDocumentServiceTest {
    private val readDocumentPort: ReadDocumentPort = mockk(relaxed = true)
    private val amendDocumentPort: AmendDocumentPort = mockk(relaxed = true)

    private val amendDocumentUseCase: AmendDocumentUseCase = AmendDocumentService(
        readDocumentPort,
        amendDocumentPort,
    )

    @DisplayName("should amend a matching document with the given command")
    @Test
    fun shouldAmendMatchingDocumentWithGivenCommand() {
        val fakeDocumentId: Long = faker.random.nextLong()
        val fakeTitle: String = faker.heroesOfTheStorm.heroes()
        val fakeContent: String = faker.heroesOfTheStorm.quotes()

        every {
            readDocumentPort.loadDocument(eq(fakeDocumentId))
        }.returns(
            documentWithFullData()
        )

        amendDocumentUseCase.amendDocument(
            AmendDocumentCommand(
                documentId = fakeDocumentId,
                title = fakeTitle,
                content = fakeContent
            )
        )

        verify {
            amendDocumentPort.amendDocument(
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
                    documentId = faker.random.nextLong(),
                    title = faker.heroesOfTheStorm.heroes(),
                    content = faker.heroesOfTheStorm.quotes(),
                )
            )
        }
    }
}
