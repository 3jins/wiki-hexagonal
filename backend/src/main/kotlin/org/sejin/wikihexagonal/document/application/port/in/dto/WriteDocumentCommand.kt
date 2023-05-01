package org.sejin.wikihexagonal.document.application.port.`in`.dto

import org.sejin.wikihexagonal.member.domain.MemberId

data class WriteDocumentCommand(
    val authorId: MemberId,
    val title: String,
    val content: String,
)
