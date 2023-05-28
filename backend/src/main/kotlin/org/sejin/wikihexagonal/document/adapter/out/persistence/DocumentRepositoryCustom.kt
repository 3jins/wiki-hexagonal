package org.sejin.wikihexagonal.document.adapter.out.persistence

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery

interface DocumentRepositoryCustom {
    fun searchDocuments(query: SearchDocumentsQuery): List<DocumentEntity>
}
