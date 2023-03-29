package org.sejin.wikihexagonal.document.adapter.`in`.web.request

import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand
import org.sejin.wikihexagonal.member.domain.MemberId

data class WriteDocumentRequest(
    val memberId: Long,
    val title: String,
    val content: String,
) {
    fun toCommand() = WriteDocumentCommand(
        memberId = MemberId(this.memberId),
        title = this.title,
        content = this.content,
    )
}
