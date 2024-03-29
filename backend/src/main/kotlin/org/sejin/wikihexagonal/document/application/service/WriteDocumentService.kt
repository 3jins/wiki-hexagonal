package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.common.Transactional
import org.sejin.wikihexagonal.document.application.port.`in`.WriteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

open class WriteDocumentService(
    private val createDocumentPort: CreateDocumentPort,
    private val readMemberPort: ReadMemberPort,
) : WriteDocumentUseCase {
    @Transactional
    override fun writeDocument(command: WriteDocumentCommand): MemberId {
        val member: Member = checkNotNull(
            readMemberPort.loadMember(
                memberId = command.authorId,
            ),
        ) { "Member(id = ${command.authorId}) does not exist." }

        val document: Document = Document.write(
            author = member,
            title = command.title,
            content = command.content,
        )

        return createDocumentPort.createDocument(document)
    }
}
