package org.sejin.wikihexagonal.document.adapter.out.persistence

import org.sejin.wikihexagonal.document.adapter.out.persistence.snapshot.QDocumentSnapshotEntity
import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.member.adapter.out.persistence.QMemberEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class DocumentRepositoryCustomImpl : DocumentRepositoryCustom, QuerydslRepositorySupport(DocumentEntity::class.java) {
    override fun searchDocuments(query: SearchDocumentsQuery): List<DocumentEntity> {
        val documentEntity = QDocumentEntity.documentEntity
        val memberEntity = QMemberEntity.memberEntity
        val documentSnapshotEntity = QDocumentSnapshotEntity.documentSnapshotEntity

        return from(documentEntity)
            .innerJoin(memberEntity)
            .on(documentEntity.author.id.eq(memberEntity.id))
            .leftJoin(documentSnapshotEntity)
            .on(documentEntity.id.eq(documentSnapshotEntity.document.id))
            .where(
                query.authorId?.let { memberEntity.id.eq(it.value) },
                query.status?.let { documentEntity.status.eq(it) },
                query.title?.let { documentSnapshotEntity.title.like("%${it}%") },
                query.content?.let { documentSnapshotEntity.content.like("%${it}%") },
            )
            .fetch()
    }
}
