package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.document.application.port.`in`.DeleteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.DeleteDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.LoadDocumentPort
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker

@DisplayName("DeleteDocumentService")
internal class DeleteDocumentServiceTest {
    private val loadDocumentPort: LoadDocumentPort = mockk(relaxed = true)
    private val deleteDocumentPort: DeleteDocumentPort = mockk(relaxed = true)

    private val deleteDocumentUseCase: DeleteDocumentUseCase = DeleteDocumentService(
        loadDocumentPort,
        deleteDocumentPort,
    )

    @DisplayName("should delete a matching document")
    @Test
    fun shouldDeleteMatchingDocument() {
        val fakeDocumentId: Long = faker.random.nextLong()

        every {
            loadDocumentPort.loadDocument(eq(fakeDocumentId))
        }.returns(documentWithFullData())

        deleteDocumentUseCase.deleteDocument(documentId = fakeDocumentId)

        verify { deleteDocumentPort.deleteDocument(fakeDocumentId) }
    }

    @DisplayName("should fail if target document does not exist")
    @Test
    fun shouldFailIfTargetDocumentDoesNotExist() {
        every {
            loadDocumentPort.loadDocument(any())
        } returns null

        assertThrows<IllegalStateException> {
            deleteDocumentUseCase.deleteDocument(documentId = faker.random.nextLong())
        }
    }
}
