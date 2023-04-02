package org.sejin.wikihexagonal.member.application.service

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.application.port.`in`.GetMemberUseCase
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.*

@DisplayName("GetMemberService")
internal class GetMemberServiceTest {
    private val readMemberPort: ReadMemberPort = mockk(relaxed = true)

    private val getMemberUseCase: GetMemberUseCase = GetMemberService(
        readMemberPort,
    )

    @DisplayName("should get the matching member")
    @Test
    fun shouldGetMatchingMember() {
        // given
        val fakeMemberId: MemberId = faker.randomProvider.randomClassInstance()
        val fakeMember: Member = memberWithFullData()

        every {
            readMemberPort.loadMember(eq(fakeMemberId))
        }.returns(fakeMember)

        // when
        val member: Member = getMemberUseCase.getMember(
            memberId = fakeMemberId,
        )

        // then
        member.shouldBeEqualTo(fakeMember)
    }

    @DisplayName("should fail if target member does not exist")
    @Test
    fun shouldFailIfTargetMemberDoesNotExist() {
        every {
            readMemberPort.loadMember(any())
        } returns null

        assertThrows<IllegalStateException> {
            getMemberUseCase.getMember(faker.randomProvider.randomClassInstance())
        }
    }
}
