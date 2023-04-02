package org.sejin.wikihexagonal.document.adapter.`in`.web

import org.sejin.wikihexagonal.document.adapter.`in`.web.response.GetDocumentResponse
import org.sejin.wikihexagonal.document.application.port.`in`.*
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCUMENT_URI)
class GetDocumentController(
    private val getDocumentUseCase: GetDocumentUseCase,
) {
    @GetMapping("/{documentId}")
    fun getDocument(@PathVariable documentId: Long): GetDocumentResponse {
        val document: Document = getDocumentUseCase.getDocument(
            documentId = DocumentId(documentId),
        )

        return GetDocumentResponse.of(document = document)
    }
}
