package org.sejin.wikihexagonal.document.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.BaseControllerTest
import org.sejin.wikihexagonal.document.adapter.`in`.web.request.WriteDocumentRequest
import org.sejin.wikihexagonal.document.application.port.`in`.WriteDocumentUseCase
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.web.CorsConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(controllers = [WriteDocumentController::class, CorsConfiguration::class])
@DisplayName("WriteDocumentController")
internal class WriteDocumentControllerTest : BaseControllerTest() {

    @MockkBean(relaxed = true)
    private lateinit var writeDocumentUseCase: WriteDocumentUseCase

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun testWriteDocument() {
        val fakeMemberId = faker.random.nextLong()
        val fakeTitle = faker.onePiece.characters()
        val fakeContent = faker.onePiece.quotes()

        val request = WriteDocumentRequest(
            memberId = fakeMemberId,
            title = fakeTitle,
            content = fakeContent
        )

        testByPost("/wiki/documents", objectMapper.writeValueAsString(request))
            .andExpect(status().isOk)

        verify {
            writeDocumentUseCase.writeDocument(
                withArg {
                    it.authorId.value.shouldBeEqualTo(fakeMemberId)
                    it.title.shouldBeEqualTo(fakeTitle)
                    it.content.shouldBeEqualTo(fakeContent)
                }
            )
        }
    }
}
