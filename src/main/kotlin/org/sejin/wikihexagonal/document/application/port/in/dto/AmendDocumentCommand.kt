package org.sejin.wikihexagonal.document.application.port.`in`.dto

data class AmendDocumentCommand(
    val documentId: Long,
    val title: String,
    val content: String,
)
