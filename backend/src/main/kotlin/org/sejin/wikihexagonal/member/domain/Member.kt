package org.sejin.wikihexagonal.member.domain

data class Member(
    val id: MemberId,
    val name: String,
) {
    companion object {
        fun of(
            id: MemberId,
            name: String,
        ) = Member(
            id = id,
            name = name,
        )
    }
}

data class MemberId(
    val value: Long,
)
