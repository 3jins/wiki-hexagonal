package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.domain.Document

interface UpdateDocumentPort {
    fun updateDocument(documentId: Long, document: Document)
}
