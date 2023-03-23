package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.DeleteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.DeleteDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document

class DeleteDocumentService(
    private val readDocumentPort: ReadDocumentPort,
    private val deleteDocumentPort: DeleteDocumentPort,
) : DeleteDocumentUseCase {
    override fun deleteDocument(documentId: Long) {
        val document: Document = checkNotNull(
            readDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }

        document.delete()
        deleteDocumentPort.deleteDocument(
            documentId = documentId,
        )
    }
}
