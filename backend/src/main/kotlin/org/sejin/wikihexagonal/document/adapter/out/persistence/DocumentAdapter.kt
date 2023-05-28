package org.sejin.wikihexagonal.document.adapter.out.persistence

import org.sejin.wikihexagonal.document.adapter.out.persistence.snapshot.DocumentSnapshotRepository
import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class DocumentAdapter(
    val documentRepository: DocumentRepository,
    val documentSnapshotRepository: DocumentSnapshotRepository,
) : CreateDocumentPort, UpdateDocumentPort, ReadDocumentPort {
    @Transactional(readOnly = false)
    override fun createDocument(document: Document): Long {
        val documentEntity = DocumentEntity.initialOf(document)
        documentSnapshotRepository.saveAll(documentEntity.documentSnapshots)
        return documentRepository.save(documentEntity).id
    }

    @Transactional(readOnly = false)
    override fun updateDocument(document: Document) {
        val documentEntity: DocumentEntity = checkNotNull(documentRepository.findByIdOrNull(id = document.id?.value)) {
            "Document(id = ${document.id}) does not exist."
        }

        documentEntity.update(document = document)
        documentSnapshotRepository.saveAll(documentEntity.documentSnapshots)
        documentRepository.save(documentEntity)
    }

    override fun loadDocument(documentId: DocumentId): Document? {
        return documentRepository.findByIdOrNull(id = documentId.value)?.toDomain()
    }

    override fun searchDocuments(query: SearchDocumentsQuery): List<Document> {
        return documentRepository.searchDocuments(query = query)
            .map { it.toDomain() }
    }
}
