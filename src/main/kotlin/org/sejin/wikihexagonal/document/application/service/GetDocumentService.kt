package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.GetDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.LoadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document

class GetDocumentService(
    private val loadDocumentPort: LoadDocumentPort,
) : GetDocumentUseCase {
    override fun getDocument(documentId: Long): Document {
        return checkNotNull(
            loadDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }
    }
}
