package org.sejin.wikihexagonal.document.domain

import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.time.LocalDateTime

data class DocumentSnapshot(
    val id: DocumentSnapshotId?,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
) {
    fun amend(
        title: String?,
        content: String?,
    ): DocumentSnapshot {
        return this.copy(
            title = title ?: this.title,
            content = content ?: this.content,
            createdAt = LocalDateTime.now(),
        )
    }

    fun renderContent(): String {
        val flavour = GFMFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour = flavour).buildMarkdownTreeFromString(this.content)

        return HtmlGenerator(
            markdownText = this.content,
            root = parsedTree,
            flavour = flavour,
        ).generateHtml()
    }

    companion object {
        fun write(
            title: String,
            content: String,
        ): DocumentSnapshot {
            return DocumentSnapshot(
                id = null,
                title = title,
                content = content,
                createdAt = LocalDateTime.now(),
            )
        }
    }
}

data class DocumentSnapshotId(
    val value: Long,
)
