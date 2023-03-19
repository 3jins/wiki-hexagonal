package org.sejin.wikihexagonal.document.domain

import java.time.LocalDateTime

data class Document(
//    val author: Member,
    var status: DocumentStatus,
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

    fun delete() {
       this.status = DocumentStatus.DELETED
    }

    fun getLatestSnapshot(): DocumentSnapshot =
        this.snapshots.last()

    private fun getTitle(): String =
        getLatestSnapshot().title

    private fun getContent(): String =
        getLatestSnapshot().content

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
                status = DocumentStatus.ON_DISPLAY,
                snapshots = mutableListOf(documentSnapshot),
            )
        }
    }
}
