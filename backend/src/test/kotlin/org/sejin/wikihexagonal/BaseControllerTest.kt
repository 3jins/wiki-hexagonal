package org.sejin.wikihexagonal

import com.ninjasquad.springmockk.MockkBean
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.time.LocalDateTime
import java.util.*

internal abstract class BaseControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean(relaxed = true)
    private lateinit var jpaMetamodelMappingContext: JpaMetamodelMappingContext

    protected fun testByGet(uri: String): ResultActions {
        return testByGet(
            uri = uri,
            headers = HttpHeaders.EMPTY,
        )
    }

    protected fun testByPost(uri: String, body: String): ResultActions {
        return testByPost(
            uri = uri,
            body = body,
            headers = HttpHeaders.EMPTY,
        )
    }

    protected fun testByPost(uri: String, body: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA)
                .content(body),
        )
    }

    protected fun testByPatch(uri: String, body: String): ResultActions {
        return testByPatch(
            uri = uri,
            body = body,
            headers = HttpHeaders.EMPTY,
        )
    }

    protected fun testByPatch(uri: String, body: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.patch(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA)
                .content(body),
        )
    }

    protected fun testByDelete(uri: String): ResultActions {
        return testByDelete(
            uri = uri,
            headers = HttpHeaders.EMPTY,
        )
    }

    private fun testByGet(uri: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA),
        )
    }

    private fun testByDelete(uri: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.delete(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA),
        )
    }

    // `jsonPath` converts LocalDateTime to a String without the last zero digit when its nanosecond ends with zero
    // so that a test using `String.equals` fails with a 10% chance.
    // This custom matcher is introduced to fix this flaw of controller tests.
    internal class LocalDateTimeMatcher(
        private val expectedValue: LocalDateTime,
    ) : BaseMatcher<LocalDateTime?>() {
        override fun matches(item: Any): Boolean {
            val rawData: String = item.toString()
            val parsed = LocalDateTime.parse(rawData)
            if (!parsed.equals(expectedValue)) {
                return false
            }
            return true
        }

        override fun describeTo(description: Description) {
            description.appendText(
                String.format("to be \"$expectedValue\""),
            )
        }
    }
}
