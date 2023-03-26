package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.`in`.dto.AmendDocumentCommand
import org.sejin.wikihexagonal.document.application.port.out.AmendDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentSnapshotPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentSnapshot

class AmendDocumentService(
    private val readDocumentPort: ReadDocumentPort,
    private val amendDocumentPort: AmendDocumentPort,
    private val createDocumentSnapshotPort: CreateDocumentSnapshotPort,
) : AmendDocumentUseCase {
    override fun amendDocument(command: AmendDocumentCommand) {
        val documentId = command.documentId

        val document: Document = checkNotNull(
            readDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }

        val amendedVersionSnapshot: DocumentSnapshot = document.getLatestSnapshot().amend(
            title = command.title,
            content = command.content,
        )
        val appendedDocumentSnapshot: DocumentSnapshot =
            createDocumentSnapshotPort.createDocumentSnapshot(documentSnapshot = amendedVersionSnapshot)

        document.amend(documentSnapshot = appendedDocumentSnapshot)
        amendDocumentPort.amendDocument(
            documentId = documentId,
            document = document,
        )
    }
}
