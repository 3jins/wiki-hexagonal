package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentCommand
import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.AmendDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document

class AmendDocumentService(
    private val readDocumentPort: ReadDocumentPort,
    private val amendDocumentPort: AmendDocumentPort,
) : AmendDocumentUseCase {
    override fun amendDocument(command: AmendDocumentCommand) {
        val documentId = command.documentId
        
        val document: Document = checkNotNull(
            readDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }

        document.amend(
            title = command.title,
            content = command.content,
        )
        amendDocumentPort.amendDocument(
            documentId = documentId,
            document = document,
        )
    }
}
