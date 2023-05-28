package org.sejin.wikihexagonal.member.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long>
