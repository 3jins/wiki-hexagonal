package org.sejin.wikihexagonal.document.domain

import org.amshove.kluent.*
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
        @DisplayName("should be updated when valid data are given")
        @Test
        fun shouldBeUpdatedWithValidData() {
            val document: Document = documentWithFullData()

            assertDoesNotThrow {
                document.amend(
                    content = faker.onePiece.quotes(),
                )
            }
        }

        @DisplayName("should add a snapshot when a document is amended")
        @Test
        fun shouldAddSnapshot() {
            val document: Document = documentWithFullData()

            document.amend(
                content = faker.onePiece.quotes(),
            )

            document.snapshots.shouldHaveSize(2)
        }

        @DisplayName("should update only with given data")
        @Test
        fun shouldBeUpdatedOnlyWithGivenData() {
            val document: Document = Document.write(
                title = faker.onePiece.characters(),
                content = faker.onePiece.quotes(),
            )

            document.amend(
                content = faker.heroesOfTheStorm.quotes(),
            )

            document.snapshots[0].title.shouldBeEqualTo(document.snapshots[1].title)
            document.snapshots[0].content.shouldNotBeEqualTo(document.snapshots[1].content)
        }
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
