package org.sejin.wikihexagonal.document.adapter.out.persistence

import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.*
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class DocumentAdapter : CreateDocumentPort, UpdateDocumentPort, ReadDocumentPort {
    override fun createDocument(document: Document): Document {
        return document
    }

    override fun updateDocument(documentId: DocumentId, document: Document) {
    }

    override fun loadDocument(documentId: DocumentId): Document? {
        return mockedData.find { it.id == documentId }
    }

    override fun searchDocuments(query: SearchDocumentsQuery): List<Document> {
        return mockedData.filter { document ->
            query.authorId?.let { document.author.id == query.authorId } ?: true &&
                query.status?.let { document.status == query.status } ?: true &&
                query.title?.let { document.getLatestSnapshot().title.contains(query.title) } ?: true &&
                query.content?.let { document.getLatestSnapshot().content.contains(query.content) } ?: true
        }
    }

    private val mockedData = listOf(
        Document(
            id = DocumentId(1),
            author = Member(
                id = MemberId(value = 1L),
                name = "Sejin Jeon",
            ),
            status = DocumentStatus.ON_DISPLAY,
            snapshots = listOf(
                DocumentSnapshot(
                    id = DocumentSnapshotId(1),
                    title = "첫 번째 게시글",
                    content = "v1",
                    createdAt = LocalDateTime.of(2023, 3, 26, 15, 0, 0, 0),
                ),
                DocumentSnapshot(
                    id = DocumentSnapshotId(3),
                    title = "첫 번째 게시글",
                    content = "v2",
                    createdAt = LocalDateTime.of(2023, 3, 27, 16, 0, 0, 0),
                ),
                DocumentSnapshot(
                    id = DocumentSnapshotId(4),
                    title = "첫 번째 게시글",
                    content = "v3",
                    createdAt = LocalDateTime.of(2023, 3, 27, 17, 0, 0, 0),
                ),
            ),
        ),
        Document(
            id = DocumentId(2),
            author = Member(
                id = MemberId(value = 1L),
                name = "Sejin Jeon",
            ),
            status = DocumentStatus.ON_DISPLAY,
            snapshots = listOf(
                DocumentSnapshot(
                    id = DocumentSnapshotId(2),
                    title = "두 번째 게시글",
                    content = "v1",
                    createdAt = LocalDateTime.of(2023, 3, 27, 15, 0, 0, 0),
                ),
            ),
        ),
    )
}
