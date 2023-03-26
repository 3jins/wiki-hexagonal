package org.sejin.wikihexagonal.member.application.port.`in`

import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

interface GetMemberUseCase {
    fun getMember(memberId: MemberId): Member
}
