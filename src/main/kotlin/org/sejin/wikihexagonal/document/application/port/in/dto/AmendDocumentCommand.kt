package org.sejin.wikihexagonal.document.application.port.`in`.dto

import org.sejin.wikihexagonal.document.domain.DocumentId

data class AmendDocumentCommand(
    val documentId: DocumentId,
    val title: String?,
    val content: String?,
)
