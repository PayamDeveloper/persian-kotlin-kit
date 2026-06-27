package io.github.payamdeveloper.persiankit.currency

import kotlin.test.Test
import kotlin.test.assertEquals

class PersianCurrencyTest {

    @Test
    fun `rialToToman divides by ten`() {
        assertEquals(15_000, PersianCurrency.rialToToman(150_000))
    }

    @Test
    fun `tomanToRial multiplies by ten`() {
        assertEquals(150_000, PersianCurrency.tomanToRial(15_000))
    }

    @Test
    fun `formatToman appends unit with grouped digits`() {
        assertEquals("۱۵۰,۰۰۰ تومان", PersianCurrency.formatToman(150_000))
    }

    @Test
    fun `formatToman compact form`() {
        assertEquals("۱.۲ میلیون تومان", PersianCurrency.formatToman(1_200_000, compact = true))
    }

    @Test
    fun `formatRial appends unit with grouped digits`() {
        assertEquals("۱,۵۰۰,۰۰۰ ریال", PersianCurrency.formatRial(1_500_000))
    }
}
