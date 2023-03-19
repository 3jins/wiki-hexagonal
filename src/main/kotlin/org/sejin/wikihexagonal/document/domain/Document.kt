package org.sejin.wikihexagonal.document.domain

import java.time.LocalDateTime

data class Document(
//    val author: Member,
    val snapshots: MutableList<DocumentSnapshot>,
) {
    fun amend(
        title: String = getTitle(),
        content: String = getContent(),
    ) {
        val latestSnapshot: DocumentSnapshot = getLatestSnapshot()
        val amendedVersionSnapshot: DocumentSnapshot = latestSnapshot.copy(
            title = title,
            content = content,
        )
        snapshots.add(amendedVersionSnapshot)
    }

    private fun getTitle(): String =
        getLatestSnapshot().title

    private fun getContent(): String =
        getLatestSnapshot().content

    private fun getLatestSnapshot(): DocumentSnapshot =
        this.snapshots.last()

    companion object {
        fun write(
            title: String,
            content: String,
        ): Document {
            val documentSnapshot = DocumentSnapshot(
                title = title,
                content = content,
                createdAt = LocalDateTime.now(),
            )

            return Document(
                snapshots = mutableListOf(documentSnapshot),
            )
        }
    }
}
