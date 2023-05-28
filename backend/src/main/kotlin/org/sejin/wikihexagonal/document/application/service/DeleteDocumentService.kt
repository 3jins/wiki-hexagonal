package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.DeleteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId

class DeleteDocumentService(
    private val readDocumentPort: ReadDocumentPort,
    private val updateDocumentPort: UpdateDocumentPort,
) : DeleteDocumentUseCase {
    override fun deleteDocument(documentId: DocumentId) {
        val document: Document = checkNotNull(
            readDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }

        val deletedDocument: Document = document.delete()
        updateDocumentPort.updateDocument(
            document = deletedDocument,
        )
    }
}
