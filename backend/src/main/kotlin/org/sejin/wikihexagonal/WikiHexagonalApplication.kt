package org.sejin.wikihexagonal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WikiHexagonalApplication

fun main(args: Array<String>) {
	runApplication<WikiHexagonalApplication>(*args)
}
