package org.sejin.wikihexagonal.document.application.port.`in`.dto

import org.sejin.wikihexagonal.document.domain.DocumentStatus
import org.sejin.wikihexagonal.member.domain.MemberId

data class SearchDocumentsQuery(
    val authorId: MemberId?,
    val status: DocumentStatus?,
    val title: String?,
    val content: String?,
)
