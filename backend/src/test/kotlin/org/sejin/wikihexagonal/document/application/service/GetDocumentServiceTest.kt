package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.document.application.port.`in`.GetDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.domain.*
import org.sejin.wikihexagonal.faker

@DisplayName("GetDocumentService")
internal class GetDocumentServiceTest {
    private val readDocumentPort: ReadDocumentPort = mockk(relaxed = true)

    private val getDocumentUseCase: GetDocumentUseCase = GetDocumentService(
        readDocumentPort,
    )

    @DisplayName("should get the matching document")
    @Test
    fun shouldGetMatchingDocument() {
        // given
        val fakeDocumentId: DocumentId = faker.randomProvider.randomClassInstance()
        val fakeDocument: Document = documentWithFullData()

        every {
            readDocumentPort.loadDocument(eq(fakeDocumentId))
        }.returns(fakeDocument)

        // when
        val document: Document = getDocumentUseCase.getDocument(
            documentId = fakeDocumentId,
        )

        // then
        document.shouldBeEqualTo(fakeDocument)
    }

    @DisplayName("should fail if target document does not exist")
    @Test
    fun shouldFailIfTargetDocumentDoesNotExist() {
        every {
            readDocumentPort.loadDocument(any())
        } returns null

        assertThrows<IllegalStateException> {
            getDocumentUseCase.getDocument(faker.randomProvider.randomClassInstance())
        }
    }
}
