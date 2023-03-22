package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.domain.Document

interface AmendDocumentPort {
    fun amendDocument(documentId: Long, document: Document)
}
