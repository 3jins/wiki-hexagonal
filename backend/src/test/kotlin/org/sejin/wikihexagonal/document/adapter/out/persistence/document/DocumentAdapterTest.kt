package org.sejin.wikihexagonal.document.adapter.out.persistence.document

import org.amshove.kluent.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.sejin.wikihexagonal.document.adapter.out.persistence.DocumentAdapter
import org.sejin.wikihexagonal.document.adapter.out.persistence.DocumentEntity
import org.sejin.wikihexagonal.document.adapter.out.persistence.DocumentRepository
import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.domain.*
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.adapter.out.persistence.MemberEntity
import org.sejin.wikihexagonal.member.adapter.out.persistence.MemberRepository
import org.sejin.wikihexagonal.member.domain.memberWithFullData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.annotation.DirtiesContext
import java.time.LocalDateTime

@DataJpaTest
@DirtiesContext
@ComponentScan(basePackages = ["org.sejin.wikihexagonal.document.adapter.out.persistence"])
class DocumentAdapterTest {
    @Autowired
    private lateinit var documentRepository: DocumentRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var documentAdapter: DocumentAdapter

    @DisplayName("createDocument")
    @Nested
    inner class CreateDocumentTest {
        @DisplayName("should create document entity")
        @Test
        fun shouldCreateDocumentEntity() {
            // given
            val fakeAuthorId: Long = memberRepository.save(MemberEntity.initialOf(memberWithFullData())).id
            val fakeMemberEntity: MemberEntity = memberRepository.findByIdOrNull(fakeAuthorId)!!

            val fakeDocument: Document = document(author = fakeMemberEntity.toDomain())

            // when
            val documentId = documentAdapter.createDocument(document = fakeDocument)

            // then
            val documentEntity: DocumentEntity? = documentRepository.findByIdOrNull(id = documentId.value)
            documentEntity.shouldNotBeNull()
            documentEntity.author.toDomain().shouldBeEqualTo(fakeDocument.author)
            documentEntity.status.shouldBeEqualTo(fakeDocument.status)
            documentEntity.documentSnapshots.shouldHaveSize(1)
            documentEntity.documentSnapshots[0].document.id.shouldBeEqualTo(documentId.value)
            documentEntity.documentSnapshots[0].title.shouldBeEqualTo(fakeDocument.snapshots[0].title)
            documentEntity.documentSnapshots[0].content.shouldBeEqualTo(fakeDocument.snapshots[0].content)
            documentEntity.documentSnapshots[0].createdBy.toDomain().shouldBeEqualTo(fakeDocument.author)
            documentEntity.documentSnapshots[0].createdAt shouldBeAtMost 1.seconds() before LocalDateTime.now()
        }
    }

    @DisplayName("updateDocument")
    @Nested
    inner class UpdateDocumentTest {
        @DisplayName("should update document entity of which id is same with the given one")
        @Test
        fun shouldUpdateDocumentEntity() {
            // given
            val fakeAuthorId: Long = memberRepository.save(MemberEntity.initialOf(memberWithFullData())).id
            val fakeMemberEntity: MemberEntity = memberRepository.findByIdOrNull(fakeAuthorId)!!

            val fakeDocument: Document = document(author = fakeMemberEntity.toDomain())
            val fakeDocumentId: Long = documentRepository.save(DocumentEntity.initialOf(fakeDocument)).id

            val newVersionDocument: Document = Document.of(
                id = DocumentId(fakeDocumentId),
                author = memberWithFullData(),
                status = faker.random.nextEnum(DocumentStatus::class.java),
                snapshots = fakeDocument.snapshots.plus(documentSnapshotWithFullData()),
            )

            // when
            documentAdapter.updateDocument(newVersionDocument)

            // then
            val updatedDocumentEntity: DocumentEntity? = documentRepository.findByIdOrNull(id = fakeDocumentId)
            updatedDocumentEntity.shouldNotBeNull()
            updatedDocumentEntity.author.toDomain().shouldBeEqualTo(newVersionDocument.author)
            updatedDocumentEntity.status.shouldBeEqualTo(newVersionDocument.status)

            updatedDocumentEntity.documentSnapshots.shouldHaveSize(2)
            updatedDocumentEntity.documentSnapshots[0].document.id.shouldBeEqualTo(fakeDocumentId)
            updatedDocumentEntity.documentSnapshots[0].title.shouldBeEqualTo(fakeDocument.snapshots[0].title)
            updatedDocumentEntity.documentSnapshots[0].content.shouldBeEqualTo(fakeDocument.snapshots[0].content)
            updatedDocumentEntity.documentSnapshots[0].createdBy.toDomain().shouldBeEqualTo(fakeDocument.author)
            updatedDocumentEntity.documentSnapshots[1].title.shouldBeEqualTo(newVersionDocument.snapshots[1].title)
            updatedDocumentEntity.documentSnapshots[1].content.shouldBeEqualTo(newVersionDocument.snapshots[1].content)
            updatedDocumentEntity.documentSnapshots[1].createdBy.toDomain().shouldBeEqualTo(newVersionDocument.author)
            updatedDocumentEntity.documentSnapshots[1].createdAt.shouldBeAfter(fakeDocument.snapshots[0].createdAt)
        }

        @DisplayName("should fail to update when id is not exist")
        @Test
        fun shouldFailToUpdateWhenIdIsNotExist() {
            // given
            val fakeAuthorId: Long = memberRepository.save(MemberEntity.initialOf(memberWithFullData())).id
            val fakeMemberEntity: MemberEntity = memberRepository.findByIdOrNull(fakeAuthorId)!!

            val fakeDocument: Document = document(author = fakeMemberEntity.toDomain())
            val fakeDocumentId: Long = documentRepository.save(DocumentEntity.initialOf(fakeDocument)).id

            val newVersionDocument: Document = documentWithFullData().copy(
                id = DocumentId(fakeDocumentId + 1),
            )

            // when, then
            assertThrows<IllegalStateException> {
                documentAdapter.updateDocument(newVersionDocument)
            }
        }
    }

    @DisplayName("loadDocument")
    @Nested
    inner class LoadDocumentTest {
        @DisplayName("should load document from database")
        @Test
        fun shouldLoadDocumentFromDatabase() {
            // given
            val fakeAuthorId: Long = memberRepository.save(MemberEntity.initialOf(memberWithFullData())).id
            val fakeMemberEntity: MemberEntity = memberRepository.findByIdOrNull(fakeAuthorId)!!

            val fakeDocument: Document = document(author = fakeMemberEntity.toDomain())
            val fakeDocumentId: Long = documentRepository.save(DocumentEntity.initialOf(fakeDocument)).id

            // when
            val document: Document? = documentAdapter.loadDocument(
                documentId = DocumentId(fakeDocumentId),
            )

            // then
            document.shouldNotBeNull()
            document.author.shouldBeEqualTo(fakeDocument.author)
            document.status.shouldBeEqualTo(fakeDocument.status)
            document.snapshots.shouldHaveSize(1)
            document.snapshots[0].title.shouldBeEqualTo(fakeDocument.snapshots[0].title)
            document.snapshots[0].content.shouldBeEqualTo(fakeDocument.snapshots[0].content)
            document.snapshots[0].createdBy.shouldBeEqualTo(fakeDocument.author)
            document.snapshots[0].createdAt shouldBeAtMost 1.seconds() before LocalDateTime.now()
        }

        @DisplayName("should return null when id does not match")
        @Test
        fun shouldReturnNullWhenIdDoesNotMatch() {
            // given
            val fakeAuthorId: Long = memberRepository.save(MemberEntity.initialOf(memberWithFullData())).id
            val fakeMemberEntity: MemberEntity = memberRepository.findByIdOrNull(fakeAuthorId)!!

            val fakeDocument: Document = document(author = fakeMemberEntity.toDomain())
            val fakeDocumentId: Long = documentRepository.save(DocumentEntity.initialOf(fakeDocument)).id

            // when
            val document: Document? = documentAdapter.loadDocument(
                documentId = DocumentId(fakeDocumentId + 1),
            )

            // then
            document.shouldBeNull()
        }
    }

    @DisplayName("searchDocuments")
    @Nested
    inner class SearchDocumentsTest {
        private lateinit var fakeDocuments: List<Document>

        @BeforeEach
        fun given() {
            val fakeAuthorId: Long = memberRepository.save(MemberEntity.initialOf(memberWithFullData())).id
            val fakeMemberEntity: MemberEntity = memberRepository.findByIdOrNull(fakeAuthorId)!!

            val fakeDocument1Id: Long = documentAdapter.createDocument(
                document = document(author = fakeMemberEntity.toDomain()),
            ).value
            val fakeDocument2Id: Long = documentAdapter.createDocument(
                document = document(author = fakeMemberEntity.toDomain()),
            ).value

            fakeDocuments = listOf(
                documentRepository.findByIdOrNull(fakeDocument1Id)!!.toDomain(),
                documentRepository.findByIdOrNull(fakeDocument2Id)!!.toDomain(),
            )
        }

        @DisplayName("should search matching documents from database")
        @Test
        fun shouldSearchDocumentsFromDatabase() {
            // when
            val documents: List<Document> = documentAdapter.searchDocuments(
                query = SearchDocumentsQuery(
                    authorId = fakeDocuments[0].author.id,
                    status = fakeDocuments[0].status,
                    title = fakeDocuments[0].getLatestSnapshot().title,
                    content = fakeDocuments[0].getLatestSnapshot().content,
                ),
            )

            // then
            documents.shouldHaveSize(1)
            documents[0].id.shouldBeEqualTo(fakeDocuments[0].id)
            documents[0].author.name.shouldBeEqualTo(fakeDocuments[0].author.name)
            documents[0].status.shouldBeEqualTo(fakeDocuments[0].status)
            documents[0].snapshots.shouldHaveSize(1)
            documents[0].snapshots[0].title.shouldBeEqualTo(fakeDocuments[0].snapshots[0].title)
            documents[0].snapshots[0].content.shouldBeEqualTo(fakeDocuments[0].snapshots[0].content)
            documents[0].snapshots[0].createdBy.shouldBeEqualTo(fakeDocuments[0].author)
            documents[0].snapshots[0].createdAt shouldBeAtMost 1.seconds() before LocalDateTime.now()
        }

        @DisplayName("should get all documents when query fields are null")
        @Test
        fun shouldGetAllDocumentsWhenQueryFieldsAreNull() {
            // when
            val documents: List<Document> = documentAdapter.searchDocuments(
                query = SearchDocumentsQuery(
                    authorId = null,
                    status = null,
                    title = null,
                    content = null,
                ),
            )

            // then
            documents.shouldHaveSize(2)
        }

        @DisplayName("should get an empty list when anything is matched")
        @Test
        fun shouldGetEmptyListWhenAnythingIsMatched() {
            // when
            val documents: List<Document> = documentAdapter.searchDocuments(
                query = SearchDocumentsQuery(
                    authorId = fakeDocuments[0].author.id, // An empty list should be returned even if some conditions are matched
                    status = null,
                    title = "한글로 쓰면 절대 매칭되지 않겠지",
                    content = null,
                ),
            )

            // then
            documents.shouldBeEmpty()
        }
    }
}
