package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.member.domain.MemberId

data class Document(
    val id: DocumentId?,
    val authorId: MemberId,
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
            authorId: MemberId,
            title: String,
            content: String,
        ): Document {
            val documentSnapshot = DocumentSnapshot.write(
                title = title,
                content = content,
            )

            return Document(
                id = null,
                authorId = authorId,
                status = DocumentStatus.ON_DISPLAY,
                snapshots = listOf(documentSnapshot),
            )
        }
    }
}

data class DocumentId(
    val value: Long,
)
