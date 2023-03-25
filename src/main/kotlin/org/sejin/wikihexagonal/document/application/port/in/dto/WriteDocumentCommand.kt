package org.sejin.wikihexagonal.document.application.port.`in`.dto

data class WriteDocumentCommand(
    val title: String,
    val content: String,
)
