package org.sejin.wikihexagonal.member.adapter.out.persistence

import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId
import org.springframework.stereotype.Repository

@Repository
class MemberAdapter : ReadMemberPort {
    override fun loadMember(memberId: MemberId): Member? {
        return mockedData.find { it.id == memberId }
    }

    private val mockedData = listOf(
        Member(
            id = MemberId(1),
            name = "Sejin Jeon",
        ),
        Member(
            id = MemberId(2),
            name = "Yongha Woo",
        ),
        Member(
            id = MemberId(3),
            name = "Minkyu Cho",
        ),
        Member(
            id = MemberId(4),
            name = "Hyungsun Song",
        )
    )
}
