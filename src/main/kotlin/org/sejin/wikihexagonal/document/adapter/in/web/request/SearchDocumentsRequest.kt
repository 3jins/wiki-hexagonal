package org.sejin.wikihexagonal.document.adapter.`in`.web.request

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.domain.DocumentStatus
import org.sejin.wikihexagonal.member.domain.MemberId

data class SearchDocumentsRequest(
    val authorId: Long?,
    val status: DocumentStatus?,
    val title: String?,
    val content: String?,
) {
    fun toQuery() = SearchDocumentsQuery(
        authorId = authorId?.let { MemberId(value = this.authorId) },
        status = this.status,
        title = this.title,
        content = this.content,
    )
}
