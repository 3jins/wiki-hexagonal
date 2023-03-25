package org.sejin.wikihexagonal.document.application.port.`in`

import org.sejin.wikihexagonal.document.application.port.`in`.dto.AmendDocumentCommand

interface AmendDocumentUseCase {
    fun amendDocument(command: AmendDocumentCommand)
}
