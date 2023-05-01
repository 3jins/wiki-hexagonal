package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.application.port.`in`.SearchDocumentsUseCase
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document

class SearchDocumentsService(
    private val readDocumentPort: ReadDocumentPort,
) : SearchDocumentsUseCase {
    override fun searchDocuments(query: SearchDocumentsQuery): List<Document> {
        return readDocumentPort.searchDocuments(query)
    }
}
