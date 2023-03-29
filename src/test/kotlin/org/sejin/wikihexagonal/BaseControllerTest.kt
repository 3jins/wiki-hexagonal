package org.sejin.wikihexagonal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import java.util.*

internal abstract class BaseControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

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

    protected fun testByPatch(uri: String, body: String): ResultActions {
        return testByPatch(
            uri = uri,
            body = body,
            headers = HttpHeaders.EMPTY,
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
                .locale(Locale.KOREA)
        )
    }

    private fun testByPost(uri: String, body: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA)
                .content(body)
        )
    }

    private fun testByPatch(uri: String, body: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.patch(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA)
                .content(body)
        )
    }

    private fun testByDelete(uri: String, headers: HttpHeaders): ResultActions {
        return mockMvc.perform(
            MockMvcRequestBuilders.delete(uri)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .locale(Locale.KOREA)
        )
    }
}
