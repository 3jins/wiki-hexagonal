package org.sejin.wikihexagonal.document.application.port.out

interface DeleteDocumentPort {
    fun deleteDocument(documentId: Long)
}
