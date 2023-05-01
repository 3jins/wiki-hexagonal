package org.sejin.wikihexagonal.document.application.port.out

import org.sejin.wikihexagonal.document.domain.DocumentSnapshot

interface CreateDocumentSnapshotPort {
    fun createDocumentSnapshot(documentSnapshot: DocumentSnapshot): DocumentSnapshot
}
