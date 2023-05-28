package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.domain.Member
import java.time.LocalDateTime

@DslMarker
annotation class DocumentSnapshotDsl

fun documentSnapshot(
    title: String,
    content: String,
) = faker.randomProvider.randomClassInstance<DocumentSnapshotBuilder> {
    typeGenerator { LocalDateTime.now() }
}
    .title(title)
    .content(content)
    .build()

fun documentSnapshot(content: String) =
    faker.randomProvider.randomClassInstance<DocumentSnapshotBuilder> {
        typeGenerator { LocalDateTime.now() }
    }
        .content(content)
        .build()

fun documentSnapshotWithFullData() =
    faker.randomProvider.randomClassInstance<DocumentSnapshotBuilder> {
        typeGenerator { LocalDateTime.now() }
    }.build()

@DocumentSnapshotDsl
data class DocumentSnapshotBuilder(
    var id: DocumentSnapshotId,
    var title: String,
    var content: String,
    var createdBy: Member,
    var createdAt: LocalDateTime,
) {
    fun title(title: String): DocumentSnapshotBuilder {
        this.title = title
        return this
    }

    fun content(content: String): DocumentSnapshotBuilder {
        this.content = content
        return this
    }

    fun build() = DocumentSnapshot(
        id = this.id,
        title = this.title,
        content = this.content,
        createdBy = this.createdBy,
        createdAt = this.createdAt,
    )
}
