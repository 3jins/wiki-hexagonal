package org.sejin.wikihexagonal.document.adapter.`in`.web

import com.ninjasquad.springmockk.MockkBean
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.BaseControllerTest
import org.sejin.wikihexagonal.document.application.port.`in`.DeleteDocumentUseCase
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.web.CorsConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(controllers = [DeleteDocumentController::class, CorsConfiguration::class])
@DisplayName("DeleteDocumentController")
internal class DeleteDocumentControllerTest : BaseControllerTest() {

    @MockkBean(relaxed = true)
    private lateinit var deleteDocumentUseCase: DeleteDocumentUseCase

    @Test
    fun testDeleteDocument() {
        val fakeDocumentId = faker.random.nextLong()

        testByDelete("/wiki/documents/${fakeDocumentId}")
            .andExpect(status().isOk)

        verify {
            deleteDocumentUseCase.deleteDocument(
                withArg {
                    it.value.shouldBeEqualTo(fakeDocumentId)
                }
            )
        }
    }
}
