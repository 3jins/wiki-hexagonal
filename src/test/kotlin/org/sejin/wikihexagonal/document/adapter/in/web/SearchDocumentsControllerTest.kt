package org.sejin.wikihexagonal.document.adapter.`in`.web

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.BaseControllerTest
import org.sejin.wikihexagonal.document.application.port.`in`.SearchDocumentsUseCase
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(controllers = [SearchDocumentsController::class])
@DisplayName("SearchDocumentsController")
internal class SearchDocumentsControllerTest : BaseControllerTest() {

    @MockkBean(relaxed = true)
    private lateinit var searchDocumentsUseCase: SearchDocumentsUseCase

    @Test
    fun testSearchDocuments() {
        val fakeDocuments = listOf(
            documentWithFullData(),
            documentWithFullData(),
        )

        every { searchDocumentsUseCase.searchDocuments(any()) }.returns(fakeDocuments)

        testByGet("/wiki/documents")
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.documents[0].documentId").value(fakeDocuments[0].id!!.value))
            .andExpect(jsonPath("$.documents[0].authorId").value(fakeDocuments[0].authorId.value))
            .andExpect(jsonPath("$.documents[0].status").value(fakeDocuments[0].status.name))
            .andExpect(jsonPath("$.documents[0].latestVersionId").value(fakeDocuments[0].getLatestSnapshot().id!!.value))
            .andExpect(jsonPath("$.documents[0].title").value(fakeDocuments[0].getLatestSnapshot().title))
            .andExpect(jsonPath("$.documents[0].content").value(fakeDocuments[0].getLatestSnapshot().content))
            .andExpect(jsonPath("$.documents[0].createdAt", LocalDateTimeMatcher(fakeDocuments[0].snapshots[0].createdAt)))
            .andExpect(jsonPath("$.documents[0].updatedAt", LocalDateTimeMatcher(fakeDocuments[0].getLatestSnapshot().createdAt)))

            .andExpect(jsonPath("$.documents[1].documentId").value(fakeDocuments[1].id!!.value))
            .andExpect(jsonPath("$.documents[1].authorId").value(fakeDocuments[1].authorId.value))
            .andExpect(jsonPath("$.documents[1].status").value(fakeDocuments[1].status.name))
            .andExpect(jsonPath("$.documents[1].latestVersionId").value(fakeDocuments[1].getLatestSnapshot().id!!.value))
            .andExpect(jsonPath("$.documents[1].title").value(fakeDocuments[1].getLatestSnapshot().title))
            .andExpect(jsonPath("$.documents[1].content").value(fakeDocuments[1].getLatestSnapshot().content))
            .andExpect(jsonPath("$.documents[1].createdAt", LocalDateTimeMatcher(fakeDocuments[1].snapshots[0].createdAt)))
            .andExpect(jsonPath("$.documents[1].updatedAt", LocalDateTimeMatcher(fakeDocuments[1].getLatestSnapshot().createdAt)))
    }
}
