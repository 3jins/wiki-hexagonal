package org.sejin.wikihexagonal.document.adapter.`in`.web

import org.sejin.wikihexagonal.document.adapter.`in`.web.request.AmendDocumentRequest
import org.sejin.wikihexagonal.document.application.port.`in`.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCUMENT_URI)
class AmendDocumentController(
    private val amendDocumentUseCase: AmendDocumentUseCase,
) {
    @PatchMapping("/{documentId}")
    fun amendDocument(
        @PathVariable
        documentId: Long,

        @RequestBody
        request: AmendDocumentRequest,

        @RequestHeader
        requestMemberId: Long,
    ) {
        amendDocumentUseCase.amendDocument(
            command = request.toCommand(
                documentId = documentId,
                requestMemberId = requestMemberId,
            ),
        )
    }
}
