package org.sejin.wikihexagonal.document.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface DocumentRepository : JpaRepository<DocumentEntity, Long>, DocumentRepositoryCustom
