package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.domain.Document

interface LoadDocumentPort {
    fun loadDocument(documentId: Long): Document?
}
