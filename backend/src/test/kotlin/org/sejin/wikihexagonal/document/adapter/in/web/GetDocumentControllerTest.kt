package org.sejin.wikihexagonal.document.adapter.`in`.web

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.BaseControllerTest
import org.sejin.wikihexagonal.document.application.port.`in`.GetDocumentUseCase
import org.sejin.wikihexagonal.document.domain.documentWithFullData
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.web.CorsConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(controllers = [GetDocumentController::class, CorsConfiguration::class])
@DisplayName("GetDocumentController")
internal class GetDocumentControllerTest : BaseControllerTest() {

    @MockkBean(relaxed = true)
    private lateinit var getDocumentUseCase: GetDocumentUseCase

    @Test
    fun testGetDocument() {
        val fakeDocument = documentWithFullData()
        every { getDocumentUseCase.getDocument(any()) }.returns(fakeDocument)

        testByGet("/wiki/documents/${faker.random.nextLong()}")
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.author.memberId").value(fakeDocument.author.id.value))
            .andExpect(jsonPath("$.author.name").value(fakeDocument.author.name))
            .andExpect(jsonPath("$.status").value(fakeDocument.status.name))
            .andExpect(jsonPath("$.latestVersionId").value(fakeDocument.getLatestSnapshot().id!!.value))
            .andExpect(jsonPath("$.title").value(fakeDocument.getLatestSnapshot().title))
            .andExpect(jsonPath("$.content").value(fakeDocument.getLatestSnapshot().renderContent()))
            .andExpect(jsonPath("$.createdAt", LocalDateTimeMatcher(fakeDocument.snapshots[0].createdAt)))
            .andExpect(jsonPath("$.updatedAt", LocalDateTimeMatcher(fakeDocument.getLatestSnapshot().createdAt)))
    }
}
