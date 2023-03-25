package org.sejin.wikihexagonal.document.application.port.`in`.dto

import org.sejin.wikihexagonal.document.domain.DocumentStatus

data class SearchDocumentsQuery(
    val status: DocumentStatus?,
    val title: String?,
    val content: String?,
)
