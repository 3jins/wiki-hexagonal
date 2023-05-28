package org.sejin.wikihexagonal.document.adapter.out.persistence.snapshot

import org.springframework.data.jpa.repository.JpaRepository

interface DocumentSnapshotRepository : JpaRepository<DocumentSnapshotEntity, Long>
