package org.sejin.wikihexagonal.document.adapter.`in`.web.response

import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentStatus
import org.sejin.wikihexagonal.member.domain.Member
import java.time.LocalDateTime

data class GetDocumentResponse(
    val author: AuthorResponse,
    val status: DocumentStatus,
    val latestVersionId: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    data class AuthorResponse(
        val memberId: Long,
        val name: String,
    ) {
        companion object {
            fun of(member: Member) = AuthorResponse(
                memberId = member.id.value,
                name = member.name,
            )
        }
    }

    companion object {
        fun of(document: Document) = GetDocumentResponse(
            author = AuthorResponse.of(
                member = document.author,
            ),
            status = document.status,
            latestVersionId = checkNotNull(document.getLatestSnapshot().id) {
                "스냅샷의 ID가 없습니다: documentId: ${document.id}, title: ${document.getLatestSnapshot().title}"
            }.value,
            title = document.getLatestSnapshot().title,
            content = document.getLatestSnapshot().content,
            createdAt = document.snapshots[0].createdAt,
            updatedAt = document.getLatestSnapshot().createdAt,
        )
    }
}
