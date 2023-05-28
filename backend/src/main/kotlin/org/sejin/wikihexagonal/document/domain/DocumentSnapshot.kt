package org.sejin.wikihexagonal.document.domain

import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.sejin.wikihexagonal.member.domain.Member
import java.time.LocalDateTime

data class DocumentSnapshot(
    val id: DocumentSnapshotId?,
    val title: String,
    val content: String,
    val createdBy: Member,
    val createdAt: LocalDateTime,
) {
    fun amend(
        title: String?,
        content: String?,
        createdBy: Member,
    ): DocumentSnapshot {
        return this.copy(
            title = title ?: this.title,
            content = content ?: this.content,
            createdBy = createdBy,
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
            createdBy: Member,
        ): DocumentSnapshot {
            return DocumentSnapshot(
                id = null,
                title = title,
                content = content,
                createdBy = createdBy,
                createdAt = LocalDateTime.now(),
            )
        }

        fun of(
            id: DocumentSnapshotId?,
            title: String,
            content: String,
            createdBy: Member,
            createdAt: LocalDateTime,
        ) = DocumentSnapshot(
            id = id,
            title = title,
            content = content,
            createdBy = createdBy,
            createdAt = createdAt,
        )
    }
}

data class DocumentSnapshotId(
    val value: Long,
)
