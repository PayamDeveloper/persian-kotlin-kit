package io.github.payamdeveloper.persiankit.words

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PersianNumberWordsTest {

    @Test
    fun `zero`() {
        assertEquals("صفر", PersianNumberWords.toWords(0))
    }

    @Test
    fun `single digits`() {
        assertEquals("پنج", PersianNumberWords.toWords(5))
    }

    @Test
    fun `teens`() {
        assertEquals("ده", PersianNumberWords.toWords(10))
        assertEquals("پانزده", PersianNumberWords.toWords(15))
        assertEquals("نوزده", PersianNumberWords.toWords(19))
    }

    @Test
    fun `round tens`() {
        assertEquals("بیست", PersianNumberWords.toWords(20))
        assertEquals("نود", PersianNumberWords.toWords(90))
    }

    @Test
    fun `tens and ones combined`() {
        assertEquals("سی و چهار", PersianNumberWords.toWords(34))
    }

    @Test
    fun `round hundred`() {
        assertEquals("صد", PersianNumberWords.toWords(100))
    }

    @Test
    fun `hundred with teen remainder`() {
        assertEquals("سیصد و پانزده", PersianNumberWords.toWords(315))
    }

    @Test
    fun `full three digit group`() {
        assertEquals("پانصد و شصت و هفت", PersianNumberWords.toWords(567))
    }

    @Test
    fun `thousand boundary`() {
        assertEquals("یک هزار", PersianNumberWords.toWords(1000))
    }

    @Test
    fun `thousands with remainder`() {
        assertEquals(
            "یک هزار و دویست و سی و چهار",
            PersianNumberWords.toWords(1234)
        )
    }

    @Test
    fun `million boundary`() {
        assertEquals("یک میلیون", PersianNumberWords.toWords(1_000_000))
    }

    @Test
    fun `million with skipped zero groups`() {
        // Regression test: middle group (thousands) is zero and must be skipped
        // without leaving a stray "و" in the output.
        assertEquals("یک میلیون و پانصد", PersianNumberWords.toWords(1_000_500))
    }

    @Test
    fun `full multi-group number`() {
        assertEquals(
            "یک میلیون و دویست و سی و چهار هزار و پانصد و شصت و هفت",
            PersianNumberWords.toWords(1_234_567)
        )
    }

    @Test
    fun `negative number`() {
        assertEquals("منفی پنج", PersianNumberWords.toWords(-5))
    }

    @Test
    fun `negative multi-digit number`() {
        assertEquals("منفی صد و یک", PersianNumberWords.toWords(-101))
    }

    @Test
    fun `throws for Long MIN_VALUE`() {
        assertFailsWith<IllegalArgumentException> {
            PersianNumberWords.toWords(Long.MIN_VALUE)
        }
    }
}
