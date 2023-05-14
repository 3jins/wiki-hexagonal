package org.sejin.wikihexagonal.member.domain

import org.sejin.wikihexagonal.faker

@DslMarker
annotation class MemberDsl

fun member(id: MemberId) =
    faker.randomProvider.randomClassInstance<MemberBuilder>()
        .id(id)
        .build()

fun memberWithFullData() =
    faker.randomProvider.randomClassInstance<MemberBuilder>()
        .build()

@MemberDsl
data class MemberBuilder(
    var id: MemberId,
    var name: String,
) {
    fun id(id: MemberId): MemberBuilder {
        this.id = id
        return this
    }

    fun build() = Member(
        id = this.id,
        name = this.name,
    )
}
