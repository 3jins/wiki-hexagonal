package org.sejin.wikihexagonal.document.domain

import org.intellij.markdown.flavours.gfm.GFMFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import java.time.LocalDateTime

data class DocumentSnapshot(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
) {
    fun renderContent(): String {
        val flavour = GFMFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour = flavour).buildMarkdownTreeFromString(this.content)

        return HtmlGenerator(
            markdownText = this.content,
            root = parsedTree,
            flavour = flavour,
        ).generateHtml()
    }
}
