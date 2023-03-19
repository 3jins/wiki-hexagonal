package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.faker
import java.time.LocalDateTime

@DslMarker
annotation class DocumentSnapshotDsl

fun documentSnapshot(content: String) =
    faker.randomProvider.randomClassInstance<DocumentSnapshotBuilder> {
        typeGenerator { LocalDateTime.now() }
    }
        .content(content)
        .build()

@DocumentSnapshotDsl
data class DocumentSnapshotBuilder(
    var title: String,
    var content: String,
    var createdAt: LocalDateTime,
) {
    fun content(content: String): DocumentSnapshotBuilder {
        this.content = content
        return this
    }

    fun build() = DocumentSnapshot(
        title = title,
        content = content,
        createdAt = createdAt,
    )
}
