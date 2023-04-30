package org.sejin.wikihexagonal.web

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cors")
data class CorsConfiguration(
    val allowedClients: List<String>
)
