package org.sejin.wikihexagonal.document.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.BaseControllerTest
import org.sejin.wikihexagonal.document.adapter.`in`.web.request.AmendDocumentRequest
import org.sejin.wikihexagonal.document.application.port.`in`.AmendDocumentUseCase
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.web.CorsConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(controllers = [AmendDocumentController::class, CorsConfiguration::class])
@DisplayName("AmendDocumentController")
internal class AmendDocumentControllerTest : BaseControllerTest() {

    @MockkBean(relaxed = true)
    private lateinit var amendDocumentUseCase: AmendDocumentUseCase

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testAmendDocument() {
        val fakeDocumentId = faker.random.nextLong()
        val fakeTitle = faker.onePiece.characters()
        val fakeContent = faker.onePiece.quotes()
        val fakeRequestMemberId = faker.random.nextLong()

        val request = AmendDocumentRequest(
            title = fakeTitle,
            content = fakeContent
        )

        val httpHeaders = HttpHeaders()
        httpHeaders.set("requestMemberId", fakeRequestMemberId.toString())
        testByPatch(
            uri = "/wiki/documents/${fakeDocumentId}",
            body = objectMapper.writeValueAsString(request),
            headers = httpHeaders,
        )
            .andExpect(status().isOk)

        verify {
            amendDocumentUseCase.amendDocument(
                withArg {
                    it.documentId.value.shouldBeEqualTo(fakeDocumentId)
                    it.title.shouldBeEqualTo(fakeTitle)
                    it.content.shouldBeEqualTo(fakeContent)
                    it.requestMemberId.shouldBeEqualTo(fakeRequestMemberId)
                }
            )
        }
    }
}
