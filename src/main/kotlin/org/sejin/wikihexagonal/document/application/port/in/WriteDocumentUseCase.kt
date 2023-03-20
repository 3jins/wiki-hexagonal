package org.sejin.wikihexagonal.document.application.port.`in`

interface WriteDocumentUseCase {
    fun writeDocument(command: WriteDocumentCommand)
}
