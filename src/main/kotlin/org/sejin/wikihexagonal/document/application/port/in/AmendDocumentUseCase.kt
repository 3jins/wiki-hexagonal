package org.sejin.wikihexagonal.document.application.port.`in`

interface AmendDocumentUseCase {
    fun amendDocument(command: AmendDocumentCommand)
}
