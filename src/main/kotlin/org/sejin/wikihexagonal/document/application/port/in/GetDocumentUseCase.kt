package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.domain.Document

interface GetDocumentUseCase {
    fun getDocument(documentId: Long): Document
}
