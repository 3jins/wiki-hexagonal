package org.sejin.wikihexagonal.document.adapter.out.persistence.snapshot

import jakarta.persistence.*
import org.sejin.wikihexagonal.document.adapter.out.persistence.DocumentEntity
import org.sejin.wikihexagonal.document.domain.DocumentSnapshot
import org.sejin.wikihexagonal.member.adapter.out.persistence.MemberEntity
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "document_snapshots")
@EntityListeners(value = [AuditingEntityListener::class])
class DocumentSnapshotEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "document_id",
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
    )
    val document: DocumentEntity,

    @Column(nullable = false, length = 255)
    val title: String,

    @Column(nullable = false, columnDefinition = "mediumtext")
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id",
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
    )
    val createdBy: MemberEntity,

    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        fun initialOf(
            documentSnapshot: DocumentSnapshot,
            documentEntity: DocumentEntity,
        ) = DocumentSnapshotEntity(
            document = documentEntity,
            title = documentSnapshot.title,
            content = documentSnapshot.content,
            createdBy = documentEntity.author,
        )
    }
}
