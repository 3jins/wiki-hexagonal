package org.sejin.wikihexagonal.document.domain

data class Document(
    val id: DocumentId?,
    val status: DocumentStatus,
    val snapshots: List<DocumentSnapshot>,
) {
    fun amend(documentSnapshot: DocumentSnapshot): Document {
        return this.copy(
            snapshots = snapshots.plus(documentSnapshot)
        )
    }

    fun delete(): Document {
        return this.copy(
            status = DocumentStatus.DELETED
        )
    }

    fun getLatestSnapshot(): DocumentSnapshot =
        this.snapshots.last()

    companion object {
        fun write(
            title: String,
            content: String,
        ): Document {
            val documentSnapshot = DocumentSnapshot.write(
                title = title,
                content = content,
            )

            return Document(
                id = null,
                status = DocumentStatus.ON_DISPLAY,
                snapshots = listOf(documentSnapshot),
            )
        }
    }
}

data class DocumentId(
    val value: Long,
)
