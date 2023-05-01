package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.document.application.port.`in`.WriteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.`in`.dto.WriteDocumentCommand
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.MemberId
import org.sejin.wikihexagonal.member.domain.member

@DisplayName("WriteDocumentService")
internal class WriteDocumentServiceTest {
    private val createDocumentPort: CreateDocumentPort = mockk(relaxed = true)
    private val readMemberPort: ReadMemberPort = mockk(relaxed = true)

    private val writeDocumentUseCase: WriteDocumentUseCase = WriteDocumentService(
        createDocumentPort,
        readMemberPort,
    )

    @DisplayName("should write a document with the given command")
    @Test
    fun shouldWriteDocumentWithGivenCommand() {
        // given
        val fakeMemberId: MemberId = faker.randomProvider.randomClassInstance()
        val fakeTitle: String = faker.onePiece.characters()
        val fakeContent: String = faker.onePiece.quotes()

        every{ readMemberPort.loadMember(memberId = fakeMemberId) }
            .returns(member(id = fakeMemberId))

        // when
        writeDocumentUseCase.writeDocument(
            WriteDocumentCommand(
                authorId = fakeMemberId,
                title = fakeTitle,
                content = fakeContent
            )
        )

        // then
        verify {
            createDocumentPort.createDocument(withArg {
                it.getLatestSnapshot().title.shouldBeEqualTo(fakeTitle)
                it.getLatestSnapshot().content.shouldBeEqualTo(fakeContent)
            })
        }
    }
}
