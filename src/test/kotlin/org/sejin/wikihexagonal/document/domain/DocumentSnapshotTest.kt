package org.sejin.wikihexagonal.document.domain

import org.amshove.kluent.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.sejin.wikihexagonal.faker
import java.time.LocalDateTime

@DisplayName("DocumentSnapshot")
internal class DocumentSnapshotTest {
    @DisplayName("write")
    @Nested
    inner class WriteTest {
        @DisplayName("should be written when valid data are given")
        @Test
        fun shouldBeWrittenWithValidData() {
            val fakeTitle = faker.onePiece.characters()
            val fakeContent = faker.onePiece.quotes()

            val documentSnapshot: DocumentSnapshot = DocumentSnapshot.write(
                title = fakeTitle,
                content = fakeContent,
            )

            documentSnapshot.shouldNotBeNull()
            documentSnapshot.id.shouldBeNull()
            documentSnapshot.title.shouldBeEqualTo(fakeTitle)
            documentSnapshot.content.shouldBeEqualTo(fakeContent)
        }
    }

    @DisplayName("amend")
    @Nested
    inner class AmendTest {
        @DisplayName("should be updated when valid data are given")
        @Test
        fun shouldBeUpdatedWithValidData() {
            val documentSnapshot: DocumentSnapshot = documentSnapshotWithFullData()
            val originalId = documentSnapshot.id

            assertDoesNotThrow {
                documentSnapshot.amend(
                    content = faker.onePiece.quotes(),
                )
            }
            documentSnapshot.id.shouldBeEqualTo(originalId)
        }

        @DisplayName("should update only with given data")
        @Test
        fun shouldBeUpdatedOnlyWithGivenData() {
            val documentSnapshot: DocumentSnapshot = DocumentSnapshot.write(
                title = faker.onePiece.characters(),
                content = faker.onePiece.quotes(),
            )

            val amendedVersionSnapshot = documentSnapshot.amend(
                content = faker.heroesOfTheStorm.quotes(),
            )
            amendedVersionSnapshot.title.shouldBeEqualTo(documentSnapshot.title)
            amendedVersionSnapshot.content.shouldNotBeEqualTo(documentSnapshot.content)
        }
    }

    @DisplayName("render")
    @Nested
    inner class RenderTest {
        @DisplayName("should be rendered well")
        @Test
        fun shouldBeRendered() {
            val markdownContent = "# Heading1"
            assertDoesNotThrow {
                documentSnapshot(content = markdownContent)
            }
        }

        @DisplayName("should be rendered in HTML")
        @Test
        fun shouldBeRenderedInHtml() {
            val markdownContent = """
                # Heading1
                **서론** 같은 곳을 보고 있다고 생각했지만
                ## Heading2
                ***본론***부터 말해서 그들은 사실
                ### Heading3
                *세부내용*은 다른 것을 보고 있었다
                #### Heading4
                이를 테면
                - 각자의 꿈이나
                - 미래의 설계 같은 부분은
                
                서로 충분히 논의하고 방향성을 맞춰두었지만,
                ##### Heading5
                1. 계획이 꼬였을 때 어떻게 대처하겠다는 계획이나
                2. 싸웠을 때 서로 필요한 부분들
                
                같은 곳에서는 맞춰지지 않은 내용이 많았다.
                ###### Heading6
                계획은 항상 뜻대로 이뤄지지 않기에 그들의 미래는 결국 정해진 것이나 다름없었다.
                > 행복한 가정은 모두 비슷한 이유로 행복하지만 불행한 가정은 저마다의 이유로 불행하다.
            """.trimIndent()

            val snapshot = documentSnapshot(content = markdownContent)

            val renderedPage: String = snapshot.renderContent()
            renderedPage.shouldContain("<h1>Heading1</h1>")
            renderedPage.shouldContain("<h2>Heading2</h2>")
            renderedPage.shouldContain("<h3>Heading3</h3>")
            renderedPage.shouldContain("<h4>Heading4</h4>")
            renderedPage.shouldContain("<h5>Heading5</h5>")
            renderedPage.shouldContain("<h6>Heading6</h6>")
            renderedPage.shouldContain("<p><strong>서론</strong> 같은 곳을 보고 있다고 생각했지만</p>")
            renderedPage.shouldContain("<p><em><strong>본론</strong></em>부터 말해서 그들은 사실</p>")
            renderedPage.shouldContain("<p><em>세부내용</em>은 다른 것을 보고 있었다</p>")
            renderedPage.shouldContain("<p>이를 테면</p>")
            renderedPage.shouldContain("<ul><li>각자의 꿈이나</li><li>미래의 설계 같은 부분은</li></ul>")
            renderedPage.shouldContain("<p>서로 충분히 논의하고 방향성을 맞춰두었지만,</p>")
            renderedPage.shouldContain("<ol><li>계획이 꼬였을 때 어떻게 대처하겠다는 계획이나</li><li>싸웠을 때 서로 필요한 부분들</li></ol>")
            renderedPage.shouldContain("<p>같은 곳에서는 맞춰지지 않은 내용이 많았다.</p>")
            renderedPage.shouldContain("<p>계획은 항상 뜻대로 이뤄지지 않기에 그들의 미래는 결국 정해진 것이나 다름없었다.</p>")
            renderedPage.shouldContain("<blockquote><p>행복한 가정은 모두 비슷한 이유로 행복하지만 불행한 가정은 저마다의 이유로 불행하다.</p></blockquote>")
        }
    }
}
