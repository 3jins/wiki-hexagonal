package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.domain.Document

interface ReadDocumentPort {
    fun loadDocument(documentId: Long): Document?

    fun searchDocuments(query: SearchDocumentsQuery): List<Document>
}
