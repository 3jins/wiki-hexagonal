package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand
import org.sejin.wikihexagonal.member.domain.MemberId

interface WriteDocumentUseCase {
    fun writeDocument(command: WriteDocumentCommand): MemberId
}
