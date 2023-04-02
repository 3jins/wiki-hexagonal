package org.sejin.wikihexagonal.document.adapter.out.persistence

import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentSnapshotPort
import org.sejin.wikihexagonal.document.domain.DocumentSnapshot
import org.springframework.stereotype.Repository

@Repository
class DocumentSnapshotAdapter : CreateDocumentSnapshotPort {
    override fun createDocumentSnapshot(documentSnapshot: DocumentSnapshot): DocumentSnapshot {
        return documentSnapshot
    }
}
