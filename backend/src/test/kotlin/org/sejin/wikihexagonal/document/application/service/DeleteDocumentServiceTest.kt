package org.sejin.wikihexagonal.document.application.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sejin.wikihexagonal.document.application.port.`in`.DeleteDocumentUseCase
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.document.domain.DocumentId
import org.sejin.wikihexagonal.document.domain.DocumentStatus
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker

@DisplayName("DeleteDocumentService")
internal class DeleteDocumentServiceTest {
    private val readDocumentPort: ReadDocumentPort = mockk(relaxed = true)
    private val updateDocumentPort: UpdateDocumentPort = mockk(relaxed = true)

    private val deleteDocumentUseCase: DeleteDocumentUseCase = DeleteDocumentService(
        readDocumentPort,
        updateDocumentPort,
    )

    @DisplayName("should delete a matching document")
    @Test
    fun shouldDeleteMatchingDocument() {
        val fakeDocumentId: DocumentId = faker.randomProvider.randomClassInstance()

        every {
            readDocumentPort.loadDocument(eq(fakeDocumentId))
        }.returns(documentWithFullData())

        deleteDocumentUseCase.deleteDocument(documentId = fakeDocumentId)

        verify {
            updateDocumentPort.updateDocument(
                withArg {
                    it.status.shouldBeEqualTo(DocumentStatus.DELETED)
                },
            )
        }
    }

    @DisplayName("should fail if target document does not exist")
    @Test
    fun shouldFailIfTargetDocumentDoesNotExist() {
        every {
            readDocumentPort.loadDocument(any())
        } returns null

        assertThrows<IllegalStateException> {
            deleteDocumentUseCase.deleteDocument(documentId = faker.randomProvider.randomClassInstance())
        }
    }
}
