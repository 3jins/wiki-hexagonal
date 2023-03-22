package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.document.application.port.`in`.GetDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.LoadDocumentPort
import org.sejin.wikihexagonal.document.domain.*
import org.sejin.wikihexagonal.faker

@DisplayName("GetDocumentService")
internal class GetDocumentServiceTest {
    private val loadDocumentPort: LoadDocumentPort = mockk(relaxed = true)

    private val getDocumentUseCase: GetDocumentUseCase = GetDocumentService(
        loadDocumentPort,
    )

    @DisplayName("should get the matching document")
    @Test
    fun shouldGetMatchingDocument() {
        // given
        val fakeDocumentId: Long = faker.random.nextLong()
        val fakeDocument: Document = documentWithFullData()

        every {
            loadDocumentPort.loadDocument(eq(fakeDocumentId))
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
            loadDocumentPort.loadDocument(any())
        } returns null

        assertThrows<IllegalStateException> {
            getDocumentUseCase.getDocument(faker.random.nextLong())
        }
    }
}
