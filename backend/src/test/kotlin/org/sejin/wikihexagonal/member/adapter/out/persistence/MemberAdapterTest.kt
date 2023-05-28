package org.sejin.wikihexagonal.member.adapter.out.persistence

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId
import org.sejin.wikihexagonal.member.domain.memberWithFullData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.annotation.DirtiesContext

@DataJpaTest
@DirtiesContext
@ComponentScan(basePackages = ["org.sejin.wikihexagonal.member.adapter.out.persistence"])
class MemberAdapterTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var memberAdapter: MemberAdapter

    @DisplayName("loadMember")
    @Nested
    inner class LoadMemberTest {
        @DisplayName("should load member from database")
        @Test
        fun shouldLoadMemberFromDatabase() {
            val fakeMember: Member = memberWithFullData()
            val fakeMemberId: Long = memberRepository.save(MemberEntity.initialOf(fakeMember)).id

            val member: Member? = memberAdapter.loadMember(memberId = MemberId(fakeMemberId))

            member.shouldNotBeNull()
            member.name.shouldBeEqualTo(fakeMember.name)
        }

        @DisplayName("should return null when id does not match")
        @Test
        fun shouldReturnNullWhenIdDoesNotMatch() {
            val fakeMember: Member = memberWithFullData()
            val fakeMemberId: Long = memberRepository.save(MemberEntity.initialOf(fakeMember)).id

            val member: Member? = memberAdapter.loadMember(memberId = MemberId(fakeMemberId + 1))

            member.shouldBeNull()
        }
    }
}