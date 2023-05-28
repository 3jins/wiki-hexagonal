package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.member.domain.Member

data class Document(
    val id: DocumentId?,
    val author: Member,
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
            author: Member,
            title: String,
            content: String,
        ): Document {
            val documentSnapshot = DocumentSnapshot.write(
                title = title,
                content = content,
                createdBy = author,
            )

            return Document(
                id = null,
                author = author,
                status = DocumentStatus.ON_DISPLAY,
                snapshots = listOf(documentSnapshot),
            )
        }

        fun of(
            id: DocumentId,
            author: Member,
            status: DocumentStatus,
            snapshots: List<DocumentSnapshot>,
        ) = Document(
            id = id,
            author = author,
            status = status,
            snapshots = snapshots,
        )
    }
}

data class DocumentId(
    val value: Long,
)
