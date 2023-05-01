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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(controllers = [AmendDocumentController::class])
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

        val request = AmendDocumentRequest(
            title = fakeTitle,
            content = fakeContent
        )

        testByPatch("/wiki/documents/${fakeDocumentId}", objectMapper.writeValueAsString(request))
            .andExpect(status().isOk)

        verify {
            amendDocumentUseCase.amendDocument(
                withArg {
                    it.documentId.value.shouldBeEqualTo(fakeDocumentId)
                    it.title.shouldBeEqualTo(fakeTitle)
                    it.content.shouldBeEqualTo(fakeContent)
                }
            )
        }
    }
}
