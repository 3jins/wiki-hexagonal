package org.sejin.wikihexagonal.document.domain

import java.time.LocalDateTime

data class Document(
//    val author: Member,
    val snapshots: List<DocumentSnapshot>,
) {
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
                snapshots = listOf(documentSnapshot),
            )
        }
    }
}
