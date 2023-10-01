package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.member.domain.MemberId

interface CreateDocumentPort {
    fun createDocument(document: Document): MemberId
}
