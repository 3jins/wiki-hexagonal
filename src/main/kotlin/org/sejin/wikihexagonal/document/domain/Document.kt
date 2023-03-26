package org.sejin.wikihexagonal.document.domain

data class Document(
    val id: DocumentId?,
    var status: DocumentStatus,
    val snapshots: MutableList<DocumentSnapshot>,
) {
    fun amend(documentSnapshot: DocumentSnapshot) {
        snapshots.add(documentSnapshot)
    }

    fun delete() {
        this.status = DocumentStatus.DELETED
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
                snapshots = mutableListOf(documentSnapshot),
            )
        }
    }
}

data class DocumentId(
    val value: Long,
)
