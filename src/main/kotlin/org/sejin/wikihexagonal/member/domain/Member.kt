package org.sejin.wikihexagonal.member.domain

data class Member(
    val id: MemberId,
    val name: String,
)

data class MemberId(
    val value: Long,
)
