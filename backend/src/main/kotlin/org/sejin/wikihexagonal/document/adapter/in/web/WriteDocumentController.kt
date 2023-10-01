package org.sejin.wikihexagonal.document.adapter.`in`.web

import org.sejin.wikihexagonal.document.adapter.`in`.web.request.WriteDocumentRequest
import org.sejin.wikihexagonal.document.application.port.`in`.WriteDocumentUseCase
import org.sejin.wikihexagonal.member.domain.MemberId
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCUMENT_URI)
class WriteDocumentController(
    private val writeDocumentUseCase: WriteDocumentUseCase,
) {
    @PostMapping
    fun writeDocument(
        @RequestBody
        request: WriteDocumentRequest,

        @RequestHeader
        requestMemberId: Long,
    ): Long {
        val memberId = MemberId(value = requestMemberId)
        val command = request.toCommand(memberId = memberId)

        return writeDocumentUseCase.writeDocument(command = command).value
    }
}
