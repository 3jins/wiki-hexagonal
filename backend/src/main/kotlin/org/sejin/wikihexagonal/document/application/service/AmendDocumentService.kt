package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.common.Transactional
import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.`in`.dto.AmendDocumentCommand
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentSnapshot
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

open class AmendDocumentService(
    private val readDocumentPort: ReadDocumentPort,
    private val updateDocumentPort: UpdateDocumentPort,
    private val readMemberPort: ReadMemberPort,
) : AmendDocumentUseCase {
    @Transactional
    override fun amendDocument(command: AmendDocumentCommand) {
        val documentId = command.documentId

        val document: Document = checkNotNull(
            readDocumentPort.loadDocument(documentId = documentId),
        ) { "Document(id = ${documentId}) does not exist." }

        val amendedBy: Member = checkNotNull(
            readMemberPort.loadMember(
                memberId = MemberId(command.requestMemberId),
            ),
        ) { "Member(id = ${command.requestMemberId}) does not exist." }

        val amendedVersionSnapshot: DocumentSnapshot = document.getLatestSnapshot().amend(
            title = command.title,
            content = command.content,
            createdBy = amendedBy,
        )

        val amendedDocument = document.amend(documentSnapshot = amendedVersionSnapshot)
        updateDocumentPort.updateDocument(
            document = amendedDocument,
        )
    }
}
