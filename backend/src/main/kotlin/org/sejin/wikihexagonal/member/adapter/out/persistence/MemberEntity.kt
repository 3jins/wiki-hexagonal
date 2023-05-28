package org.sejin.wikihexagonal.member.adapter.out.persistence

import jakarta.persistence.*
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

@Entity(name = "members")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,

    @Column(nullable = false, length = 30)
    val name: String,
) {
    fun toDomain() = Member.of(
        id = MemberId(this.id),
        name = this.name,
    )

    companion object {
        fun initialOf(member: Member) = MemberEntity(
            name = member.name,
        )

        fun of(member: Member) = MemberEntity(
            id = member.id.value,
            name = member.name,
        )
    }
}
