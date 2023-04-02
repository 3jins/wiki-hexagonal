package org.sejin.wikihexagonal.document.adapter.`in`.web

import org.sejin.wikihexagonal.document.application.port.`in`.*
import org.sejin.wikihexagonal.document.domain.DocumentId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCUMENT_URI)
class DeleteDocumentController(
    private val deleteDocumentUseCase: DeleteDocumentUseCase,
) {
    @DeleteMapping("/{documentId}")
    fun deleteDocument(@PathVariable documentId: Long) {
        deleteDocumentUseCase.deleteDocument(
            documentId = DocumentId(documentId),
        )
    }
}
