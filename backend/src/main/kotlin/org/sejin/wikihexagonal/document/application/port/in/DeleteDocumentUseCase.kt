package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.domain.DocumentId

interface DeleteDocumentUseCase {
    fun deleteDocument(documentId: DocumentId)
}
