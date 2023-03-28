package org.sejin.wikihexagonal.member.application.service

import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MemberServiceSpringConfiguration(
    private val readMemberPort: ReadMemberPort,
) {
    @Bean
    fun getMemberService(): GetMemberService {
        return GetMemberService(readMemberPort)
    }
}
