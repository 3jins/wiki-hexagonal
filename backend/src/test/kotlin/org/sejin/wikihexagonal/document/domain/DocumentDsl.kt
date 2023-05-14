package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId
import java.time.LocalDateTime

@DslMarker
annotation class DocumentDsl

fun documentWithFullData() =
    faker.randomProvider.randomClassInstance<DocumentBuilder>()
        .build()

fun document(
    author: Member,
    status: DocumentStatus,
    title: String,
    content: String,
) = faker.randomProvider.randomClassInstance<DocumentBuilder>()
    .author(author)
    .status(status)
    .title(title)
    .content(content)
    .build()

@DocumentDsl
data class DocumentBuilder(
    val id: DocumentId,
    var author: Member,
    var status: DocumentStatus,
    var title: String,
    var content: String,
) {
    fun author(author: Member): DocumentBuilder {
        this.author = author
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
        author = this.author,
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
