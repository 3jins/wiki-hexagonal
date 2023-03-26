package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId

interface UpdateDocumentPort {
    fun updateDocument(documentId: DocumentId, document: Document)
}
