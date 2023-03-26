package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.faker

@DslMarker
annotation class DocumentDsl

fun documentWithFullData() =
    faker.randomProvider.randomClassInstance<DocumentBuilder>()
        .buildWithWrite()

fun document(
    status: DocumentStatus,
    title: String,
    content: String,
) = faker.randomProvider.randomClassInstance<DocumentBuilder>()
    .status(status)
    .title(title)
    .content(content)
    .build()

@DocumentDsl
data class DocumentBuilder(
    val id: DocumentId,
    var status: DocumentStatus,
    var title: String,
    var content: String,
) {
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

    fun buildWithWrite() = Document.write(
        title = this.title,
        content = this.content,
    )

    fun build() = Document(
        id = this.id,
        status = this.status,
        snapshots = listOf(
            DocumentSnapshot.write(
                title = this.title,
                content = this.content,
            )
        ),
    )
}
