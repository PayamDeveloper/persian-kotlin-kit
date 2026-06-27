package io.github.payamdeveloper.persiankit.digits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PersianDigitsTest {

    @Test
    fun `toPersian converts plain english digits`() {
        assertEquals("۱۲۳۴۵۶۷۸۹۰", PersianDigits.toPersian("1234567890"))
    }

    @Test
    fun `toPersian leaves non-digit characters untouched`() {
        assertEquals("۱,۲۳۴.۵۶", PersianDigits.toPersian("1,234.56"))
    }

    @Test
    fun `toPersian converts arabic-indic digits too`() {
        assertEquals("۱۲۳", PersianDigits.toPersian("١٢٣"))
    }

    @Test
    fun `toEnglish converts persian digits`() {
        assertEquals("1234567890", PersianDigits.toEnglish("۱۲۳۴۵۶۷۸۹۰"))
    }

    @Test
    fun `toEnglish converts arabic-indic digits`() {
        assertEquals("123", PersianDigits.toEnglish("١٢٣"))
    }

    @Test
    fun `round trip conversion is stable`() {
        val original = "12345"
        assertEquals(original, PersianDigits.toEnglish(PersianDigits.toPersian(original)))
    }

    @Test
    fun `containsNonEnglishDigits detects persian digits`() {
        assertTrue(PersianDigits.containsNonEnglishDigits("قیمت ۱۵۰۰"))
        assertFalse(PersianDigits.containsNonEnglishDigits("price 1500"))
    }
}
