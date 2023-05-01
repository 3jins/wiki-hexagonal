package org.sejin.wikihexagonal.member.application.service

import org.sejin.wikihexagonal.member.application.port.`in`.GetMemberUseCase
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

class GetMemberService(
    private val readMemberPort: ReadMemberPort,
) : GetMemberUseCase {
    override fun getMember(memberId: MemberId): Member {
        return checkNotNull(
            readMemberPort.loadMember(memberId = memberId),
        ) { "Member(id = ${memberId}) does not exist." }
    }
}
