package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.domain.MemberId
import java.time.LocalDateTime

@DslMarker
annotation class DocumentDsl

fun documentWithFullData() =
    faker.randomProvider.randomClassInstance<DocumentBuilder>()
        .build()

fun document(
    authorId: MemberId,
    status: DocumentStatus,
    title: String,
    content: String,
) = faker.randomProvider.randomClassInstance<DocumentBuilder>()
    .authorId(authorId)
    .status(status)
    .title(title)
    .content(content)
    .build()

@DocumentDsl
data class DocumentBuilder(
    val id: DocumentId,
    var authorId: MemberId,
    var status: DocumentStatus,
    var title: String,
    var content: String,
) {
    fun authorId(authorId: MemberId): DocumentBuilder {
        this.authorId = authorId
        return this
    }

    fun status(documentStatus: DocumentStatus): DocumentBuilder {
        this.status = documentStatus
        return this
    }

    fun title(title: String): DocumentBuilder {
        this.title = title
        return this
    }

    fun content(content: String): DocumentBuilder {
        this.content = content
        return this
    }

    fun build() = Document(
        id = this.id,
        authorId = this.authorId,
        status = this.status,
        snapshots = listOf(
            DocumentSnapshot(
                id = DocumentSnapshotId(faker.random.nextLong()),
                title = this.title,
                content = this.content,
                createdAt = LocalDateTime.now(),
            )
        ),
    )
}
