package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldContainAll
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.document.application.port.`in`.dto.SearchDocumentsQuery
import org.sejin.wikihexagonal.document.application.port.`in`.SearchDocumentsUseCase
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.Document
import org.sejin.wikihexagonal.document.domain.DocumentStatus
import org.sejin.wikihexagonal.document.domain.document
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.domain.MemberId

@DisplayName("SearchDocumentsService")
internal class SearchDocumentsServiceTest {
    private val readDocumentPort: ReadDocumentPort = mockk(relaxed = true)

    private val searchDocumentsUseCase: SearchDocumentsUseCase = SearchDocumentsService(
        readDocumentPort,
    )

    @DisplayName("should get documents matching the given query")
    @Test
    fun shouldGetDocumentsMatchingGivenQuery() {
        // given
        val fakeMemberIds: List<MemberId> = listOf(
            faker.randomProvider.randomClassInstance(),
            faker.randomProvider.randomClassInstance(),
        )
        val fakeTitles: List<String> = listOf(
            faker.onePiece.characters(),
            faker.heroesOfTheStorm.heroes(),
        )
        val fakeContents: List<String> = listOf(
            faker.onePiece.quotes(),
            faker.heroesOfTheStorm.quotes(),
        )
        val fakeStatuses: List<DocumentStatus> = listOf(
            DocumentStatus.ON_DISPLAY,
            DocumentStatus.DELETED,
        )

        every {
            readDocumentPort.searchDocuments(any())
        }.returns(
            listOf(
                document(
                    authorId = fakeMemberIds[0],
                    status = fakeStatuses[0],
                    title = fakeTitles[0],
                    content = fakeContents[0],
                ),
                document(
                    authorId = fakeMemberIds[1],
                    status = fakeStatuses[1],
                    title = fakeTitles[1],
                    content = fakeContents[1],
                ),
            )
        )

        // when
        val documents: List<Document> = searchDocumentsUseCase.searchDocuments(
            query = faker.randomProvider.randomClassInstance(),
        )

        // then
        documents.shouldHaveSize(2)
        documents.map { it.status }.shouldContainAll(fakeStatuses)
        documents.map { it.getLatestSnapshot().title }.shouldContainAll(fakeTitles)
        documents.map { it.getLatestSnapshot().content }.shouldContainAll(fakeContents)
    }

    @DisplayName("should be able to search documents if null fields are mixed in the given query")
    @Test
    fun shouldSearchDocumentsIfNullFieldsAreGiven() {
        // given
        every {
            readDocumentPort.searchDocuments(any())
        }.returns(
            listOf(
                documentWithFullData(),
                documentWithFullData(),
            )
        )

        // when
        val documents: List<Document> = searchDocumentsUseCase.searchDocuments(
            SearchDocumentsQuery(
                authorId = null,
                status = null,
                title = faker.clashOfClans.troops(),
                content = null,
            )
        )

        // then
        documents.shouldHaveSize(2)
    }
}
