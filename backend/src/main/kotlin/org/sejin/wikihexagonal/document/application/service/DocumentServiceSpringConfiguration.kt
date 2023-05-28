package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.sejin.wikihexagonal.member.application.port.out.ReadMemberPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DocumentServiceSpringConfiguration(
    private val createDocumentPort: CreateDocumentPort,
    private val readDocumentPort: ReadDocumentPort,
    private val updateDocumentPort: UpdateDocumentPort,
    private val readMemberPort: ReadMemberPort,
) {
    @Bean
    fun writeDocumentService(): WriteDocumentService {
        return WriteDocumentService(
            createDocumentPort = createDocumentPort,
            readMemberPort = readMemberPort,
        )
    }

    @Bean
    fun amendDocumentService(): AmendDocumentService {
        return AmendDocumentService(
            readDocumentPort = readDocumentPort,
            updateDocumentPort = updateDocumentPort,
            readMemberPort = readMemberPort,
        )
    }

    @Bean
    fun deleteDocumentService(): DeleteDocumentService {
        return DeleteDocumentService(
            readDocumentPort = readDocumentPort,
            updateDocumentPort = updateDocumentPort,
        )
    }

    @Bean
    fun getDocumentService(): GetDocumentService {
        return GetDocumentService(readDocumentPort = readDocumentPort)
    }

    @Bean
    fun searchDocumentsService(): SearchDocumentsService {
        return SearchDocumentsService(readDocumentPort = readDocumentPort)
    }
}
