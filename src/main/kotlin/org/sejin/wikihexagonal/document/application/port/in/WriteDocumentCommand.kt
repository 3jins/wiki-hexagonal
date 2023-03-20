package org.sejin.wikihexagonal.document.application.port.`in`

data class WriteDocumentCommand(
    val title: String,
    val content: String,
)
