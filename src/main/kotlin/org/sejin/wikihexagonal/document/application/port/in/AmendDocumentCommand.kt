package org.sejin.wikihexagonal.document.application.port.`in`

data class AmendDocumentCommand(
    val documentId: Long,
    val title: String,
    val content: String,
)
