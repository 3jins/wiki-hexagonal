package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand
import org.sejin.wikihexagonal.document.application.port.`in`.WriteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.document.domain.Document

class WriteDocumentService(
    private val createDocumentPort: CreateDocumentPort,
) : WriteDocumentUseCase {
    override fun writeDocument(command: WriteDocumentCommand) {
        val document: Document = Document.write(
            title = command.title,
            content = command.content,
        )

        createDocumentPort.createDocument(document)
    }
}
