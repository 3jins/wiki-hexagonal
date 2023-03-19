package org.sejin.wikihexagonal.document.domain

import org.sejin.wikihexagonal.faker

@DslMarker
annotation class DocumentDsl

fun documentWithFullData() =
    faker.randomProvider.randomClassInstance<DocumentBuilder>()
        .buildWithWrite()

@DocumentDsl
data class DocumentBuilder(
    var title: String,
    var content: String,
) {
    fun buildWithWrite() = Document.write(
        title = title,
        content = content,
    )
}
