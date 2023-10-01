package org.sejin.wikihexagonal.document.adapter.out.persistence

import jakarta.persistence.*
import org.sejin.wikihexagonal.document.adapter.out.persistence.snapshot.DocumentSnapshotEntity
import org.sejin.wikihexagonal.document.domain.*
import org.sejin.wikihexagonal.member.adapter.out.persistence.MemberEntity
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.MemberId

@Entity(name = "documents")
class DocumentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(
        name = "author_id",
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
    )
    var author: MemberEntity,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    var status: DocumentStatus,

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    var documentSnapshots: List<DocumentSnapshotEntity> = listOf(),
) {
    fun update(document: Document) {
        this.author = MemberEntity.of(document.author)
        this.status = document.status
        addSnapshot(document.getLatestSnapshot())
    }

    private fun addSnapshot(newSnapshot: DocumentSnapshot) {
        val newSnapshotEntity = DocumentSnapshotEntity.initialOf(
            documentSnapshot = newSnapshot,
            documentEntity = this,
        )
        this.documentSnapshots = this.documentSnapshots.plus(newSnapshotEntity)
    }

    fun toDomain(): Document {
        val author: Member = Member.of(
            id = MemberId(this.author.id),
            name = this.author.name,
        )

        return Document.of(
            id = DocumentId(this.id),
            author = author,
            status = this.status,
            snapshots = this.documentSnapshots.map {
                DocumentSnapshot.of(
                    id = DocumentSnapshotId(it.id),
                    title = it.title,
                    content = it.content,
                    createdBy = author,
                    createdAt = it.createdAt,
                )
            },
        )
    }

    companion object {
        fun initialOf(document: Document): DocumentEntity {
            val documentEntity = DocumentEntity(
                author = MemberEntity.of(document.author),
                status = document.status,
            )

            document.snapshots.forEach {
                documentEntity.addSnapshot(it)
            }

            return documentEntity
        }
    }
}
