package org.sejin.wikihexagonal.member.adapter.`in`.web.response

import org.sejin.wikihexagonal.member.domain.Member

data class GetMemberResponse(
    val name: String,
) {
    companion object {
        fun of(member: Member) = GetMemberResponse(
            name = member.name,
        )
    }
}
