package org.sejin.wikihexagonal.document.adapter.`in`.web.request

import org.sejin.wikihexagonal.document.application.port.`in`.dto.AmendDocumentCommand
import org.sejin.wikihexagonal.document.domain.DocumentId

data class AmendDocumentRequest(
    val title: String?,
    val content: String?,
) {
    fun toCommand(
        documentId: Long,
        requestMemberId: Long,
    ) = AmendDocumentCommand(
        documentId = DocumentId(documentId),
        title = this.title,
        content = this.content,
        requestMemberId = requestMemberId,
    )
}
