package org.sejin.wikihexagonal.document.adapter.`in`.web

import org.sejin.wikihexagonal.document.adapter.`in`.web.request.WriteDocumentRequest
import org.sejin.wikihexagonal.document.application.port.`in`.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(DOCUMENT_URI)
class WriteDocumentController(
    private val writeDocumentUseCase: WriteDocumentUseCase,
) {
    @PostMapping
    fun writeDocument(@RequestBody request: WriteDocumentRequest) {
        writeDocumentUseCase.writeDocument(command = request.toCommand())
    }
}
