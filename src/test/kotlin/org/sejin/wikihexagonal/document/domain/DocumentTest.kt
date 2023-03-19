package org.sejin.wikihexagonal.document.domain

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeOnOrBefore
import org.amshove.kluent.shouldHaveSingleItem
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.faker
import java.time.LocalDateTime

@DisplayName("Document")
internal class DocumentTest {

    @DisplayName("write")
    @Nested
    inner class WriteTest {
        @DisplayName("should be written when valid data are given")
        @Test
        fun shouldBeWrittenWithValidData() {
            val document: Document = Document.write(
                title = faker.onePiece.characters(),
                content = faker.onePiece.quotes(),
            )

            document.shouldNotBeNull()
        }

        @DisplayName("should leave a snapshot when a new document is written")
        @Test
        fun shouldLeaveSnapshot() {
            val fakeTitle = faker.onePiece.characters()
            val fakeContent = faker.onePiece.quotes()

            val document: Document = Document.write(
                title = fakeTitle,
                content = fakeContent,
            )

            document.snapshots.shouldHaveSingleItem()
            document.snapshots[0].title.shouldBeEqualTo(fakeTitle)
            document.snapshots[0].content.shouldBeEqualTo(fakeContent)
            document.snapshots[0].createdAt.shouldBeOnOrBefore(LocalDateTime.now())
        }
    }

    @DisplayName("amend")
    @Nested
    inner class AmendTest {

    }

    @DisplayName("delete")
    @Nested
    inner class DeleteTest {

    }

    @DisplayName("render")
    @Nested
    inner class RenderTest {
    }
}
