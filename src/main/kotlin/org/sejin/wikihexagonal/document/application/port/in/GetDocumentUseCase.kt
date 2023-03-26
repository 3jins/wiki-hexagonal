package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId

interface GetDocumentUseCase {
    fun getDocument(documentId: DocumentId): Document
}
