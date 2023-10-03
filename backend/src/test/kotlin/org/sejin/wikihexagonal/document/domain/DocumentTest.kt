package org.sejin.wikihexagonal.document.domain

import org.amshove.kluent.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.faker
import org.sejin.wikihexagonal.member.domain.Member
import org.sejin.wikihexagonal.member.domain.memberWithFullData
import java.time.LocalDateTime

@DisplayName("Document")
internal class DocumentTest {

    @DisplayName("write")
    @Nested
    inner class WriteTest {
        @DisplayName("should be written when valid data are given")
        @Test
        fun shouldBeWrittenWithValidData() {
            val fakeAuthor: Member = memberWithFullData()
            val document: Document = Document.write(
                author = fakeAuthor,
                title = faker.onePiece.characters(),
                content = faker.onePiece.quotes(),
            )

            document.shouldNotBeNull()
            document.id.shouldBeNull()
            document.author.shouldBeEqualTo(fakeAuthor)
            document.status.shouldBeEqualTo(DocumentStatus.ON_DISPLAY)
        }

        @DisplayName("should leave a snapshot when a new document is written")
        @Test
        fun shouldLeaveSnapshot() {
            val fakeTitle = faker.onePiece.characters()
            val fakeContent = faker.onePiece.quotes()

            val document: Document = Document.write(
                author = memberWithFullData(),
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
            val originalId = document.id

            assertDoesNotThrow {
                document.amend(
                    documentSnapshot = documentSnapshotWithFullData()
                )
            }
            document.id.shouldBeEqualTo(originalId)
        }

        @DisplayName("should add a snapshot when a document is amended")
        @Test
        fun shouldAddSnapshot() {
            val document: Document = documentWithFullData()

            val amendedDocument: Document = document.amend(
                documentSnapshot = documentSnapshotWithFullData()
            )

            amendedDocument.snapshots.shouldHaveSize(2)
        }
    }

    @DisplayName("delete")
    @Nested
    inner class DeleteTest {
        @DisplayName("should be soft deleted")
        @Test
        fun shouldGetTheLatestSnapshot() {
            val document: Document = documentWithFullData()

            val deletedDocument: Document = document.delete()

            deletedDocument.status.shouldBeEqualTo(DocumentStatus.DELETED)
        }
    }

    @DisplayName("getLatestSnapshot")
    @Nested
    inner class GetLatestSnapshotTest {
        @DisplayName("should get the latest snapshot")
        @Test
        fun shouldGetTheLatestSnapshot() {
            // given
            val document: Document = documentWithFullData().amend(
                documentSnapshot = documentSnapshot(
                    title = faker.heroesOfTheStorm.heroes(),
                    content = faker.heroesOfTheStorm.quotes(),
                )
            )

            // when
            val latestSnapshot: DocumentSnapshot = document.getLatestSnapshot()

            // then
            latestSnapshot.title.shouldBeEqualTo(document.snapshots.last().title)
            latestSnapshot.content.shouldBeEqualTo(document.snapshots.last().content)
            latestSnapshot.title.shouldNotBeEqualTo(document.snapshots.first().title)
            latestSnapshot.content.shouldNotBeEqualTo(document.snapshots.first().content)
        }
    }
}
