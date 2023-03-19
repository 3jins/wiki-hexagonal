package org.sejin.wikihexagonal.document.domain

import java.time.LocalDateTime

data class DocumentSnapshot(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
)
