package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.domain.Document

interface SearchDocumentsUseCase {
    fun searchDocuments(query: SearchDocumentsQuery): List<Document>
}
