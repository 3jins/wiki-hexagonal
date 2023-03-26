package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId

interface ReadDocumentPort {
    fun loadDocument(documentId: DocumentId): Document?

    fun searchDocuments(query: SearchDocumentsQuery): List<Document>
}
