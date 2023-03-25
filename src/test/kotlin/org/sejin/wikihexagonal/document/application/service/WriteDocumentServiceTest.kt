package org.sejin.wikihexagonal.document.application.service

import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand
import org.sejin.wikihexagonal.document.application.port.`in`.WriteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.faker

@DisplayName("WriteDocumentService")
internal class WriteDocumentServiceTest {
    private val createDocumentPort: CreateDocumentPort = mockk(relaxed = true)

    private val writeDocumentUseCase: WriteDocumentUseCase = WriteDocumentService(
        createDocumentPort,
    )

    @DisplayName("should write a document with the given command")
    @Test
    fun shouldWriteDocumentWithGivenCommand() {
        val fakeTitle: String = faker.onePiece.characters()
        val fakeContent: String = faker.onePiece.quotes()

        writeDocumentUseCase.writeDocument(
            WriteDocumentCommand(
                title = fakeTitle,
                content = fakeContent
            )
        )

        verify {
            createDocumentPort.createDocument(withArg {
                it.getLatestSnapshot().title.shouldBeEqualTo(fakeTitle)
                it.getLatestSnapshot().content.shouldBeEqualTo(fakeContent)
            })
        }
    }
}
