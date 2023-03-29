package org.sejin.wikihexagonal.document.application.service

import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.CreateDocumentSnapshotPort
import org.sejin.wikihexagonal.document.application.port.out.ReadDocumentPort
import org.sejin.wikihexagonal.document.application.port.out.UpdateDocumentPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DocumentServiceSpringConfiguration(
    private val createDocumentPort: CreateDocumentPort,
    private val readDocumentPort: ReadDocumentPort,
    private val updateDocumentPort: UpdateDocumentPort,
    private val createDocumentSnapshotPort: CreateDocumentSnapshotPort,
) {
    @Bean
    fun writeDocumentService(): WriteDocumentService {
        return WriteDocumentService(createDocumentPort = createDocumentPort)
    }

    @Bean
    fun amendDocumentService(): AmendDocumentService {
        return AmendDocumentService(
            readDocumentPort = readDocumentPort,
            updateDocumentPort = updateDocumentPort,
            createDocumentSnapshotPort = createDocumentSnapshotPort,
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
