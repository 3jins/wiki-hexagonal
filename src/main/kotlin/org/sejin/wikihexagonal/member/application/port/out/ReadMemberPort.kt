package org.sejin.wikihexagonal.member.application.port.out

import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

interface ReadMemberPort {
    fun loadMember(memberId: MemberId): Member?
}
