package org.sejin.wikihexagonal.document.adapter.`in`.web

import org.sejin.wikihexagonal.document.adapter.`in`.web.request.SearchDocumentsRequest
import org.sejin.wikihexagonal.document.adapter.`in`.web.response.SearchDocumentsResponse
import org.sejin.wikihexagonal.document.application.port.`in`.*
import org.sejin.wikihexagonal.document.domain.Document
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCUMENT_URI)
class SearchDocumentsController(
    private val searchDocumentsUseCase: SearchDocumentsUseCase,
) {
    @GetMapping
    fun searchDocuments(request: SearchDocumentsRequest): SearchDocumentsResponse {
        val documents: List<Document> = searchDocumentsUseCase.searchDocuments(request.toQuery())
        return SearchDocumentsResponse.of(
            documents = documents,
        )
    }
}
