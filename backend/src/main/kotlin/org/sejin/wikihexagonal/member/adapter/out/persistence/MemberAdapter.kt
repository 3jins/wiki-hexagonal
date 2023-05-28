package org.sejin.wikihexagonal.member.adapter.out.persistence

import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberAdapter(
    val memberRepository: MemberRepository,
) : ReadMemberPort {

    override fun loadMember(memberId: MemberId): Member? {
        return memberRepository.findByIdOrNull(memberId.value)?.toDomain()
    }
}
