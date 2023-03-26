package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.GetDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId

class GetDocumentService(
    private val readDocumentPort: ReadDocumentPort,
) : GetDocumentUseCase {
    override fun getDocument(documentId: DocumentId): Document {
        return checkNotNull(
            readDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }
    }
}
