package org.sejin.wikihexagonal.member.adapter.`in`.web

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.BaseControllerTest
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.application.port.`in`.GetMemberUseCase
import org.sejin.wikihexagonal.member.domain.memberWithFullData
import org.sejin.wikihexagonal.web.CorsConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [GetMemberController::class, CorsConfiguration::class])
@DisplayName("GetMemberController")
internal class GetMemberControllerTest : BaseControllerTest() {

    @MockkBean(relaxed = true)
    private lateinit var getMemberUseCase: GetMemberUseCase

    @Test
    fun testGetMember() {
        val fakeMember = memberWithFullData()

        every {
            getMemberUseCase.getMember(any())
        } returns fakeMember

        testByGet("/wiki/members/${faker.random.nextLong()}")
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(fakeMember.name))
    }
}
