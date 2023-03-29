package org.sejin.wikihexagonal.member.adapter.`in`.web

import org.sejin.wikihexagonal.member.adapter.`in`.web.response.GetMemberResponse
import org.sejin.wikihexagonal.member.application.port.`in`.GetMemberUseCase
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(MEMBER_URI)
class GetMemberController(
    private val getMemberUseCase: GetMemberUseCase,
) {
    @GetMapping("/{memberId}")
    fun getMember(@PathVariable memberId: Long): GetMemberResponse {
        val member: Member = getMemberUseCase.getMember(
            memberId = MemberId(memberId),
        )

        return GetMemberResponse.of(member = member)
    }
}
