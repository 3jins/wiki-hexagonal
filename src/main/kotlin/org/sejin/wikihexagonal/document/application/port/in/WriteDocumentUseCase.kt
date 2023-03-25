package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand

interface WriteDocumentUseCase {
    fun writeDocument(command: WriteDocumentCommand)
}
