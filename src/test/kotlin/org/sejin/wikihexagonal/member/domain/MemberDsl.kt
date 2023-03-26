package org.sejin.wikihexagonal.member.domain

import org.sejin.wikihexagonal.faker

@DslMarker
annotation class MemberDsl

fun memberWithFullData() =
    faker.randomProvider.randomClassInstance<MemberBuilder>()
        .build()

@MemberDsl
data class MemberBuilder(
    val id: MemberId,
    var name: String,
) {
    fun build() = Member(
        id = this.id,
        name = this.name,
    )
}
