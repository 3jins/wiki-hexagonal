package org.sejin.wikihexagonal.document.adapter.`in`.web.response

import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentStatus
import java.time.LocalDateTime

data class SearchDocumentsResponse(
    val documents: List<DocumentResponse>,
) {
    data class DocumentResponse(
        val documentId: Long,
        val authorId: Long,
        val status: DocumentStatus,
        val latestVersionId: Long,
        val title: String,
        val content: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    ) {
        companion object {
            fun of (document: Document) = DocumentResponse(
                documentId = checkNotNull(document.id) {
                    "문서의 ID가 없습니다. title: ${document.getLatestSnapshot().title}"
                }.value,
                authorId = document.authorId.value,
                status = document.status,
                latestVersionId = checkNotNull(document.getLatestSnapshot().id) {
                    "스냅샷의 ID가 없습니다. documentId: ${document.id}, title: ${document.getLatestSnapshot().title}"
                }.value,
                title = document.getLatestSnapshot().title,
                content = document.getLatestSnapshot().content,
                createdAt = document.snapshots[0].createdAt,
                updatedAt = document.getLatestSnapshot().createdAt,
            )
        }
    }

    companion object {
        fun of(documents: List<Document>) = SearchDocumentsResponse(
            documents = documents.map { DocumentResponse.of(it) },
        )
    }
}
