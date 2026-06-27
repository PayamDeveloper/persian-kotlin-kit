package io.github.payamdeveloper.persiankit.number

import kotlin.test.Test
import kotlin.test.assertEquals

class PersianNumberFormatterTest {

    @Test
    fun `format groups thousands with persian digits`() {
        assertEquals("۱,۲۳۴,۵۶۷", PersianNumberFormatter.format(1234567))
    }

    @Test
    fun `format without grouping`() {
        assertEquals("۱۲۳۴۵۶۷", PersianNumberFormatter.format(1234567, useGrouping = false))
    }

    @Test
    fun `format without persian digits`() {
        assertEquals("1,234,567", PersianNumberFormatter.format(1234567, useEasternDigits = false))
    }

    @Test
    fun `format handles negative numbers`() {
        assertEquals("-۱,۲۳۴", PersianNumberFormatter.format(-1234))
    }

    @Test
    fun `format handles small numbers without separators`() {
        assertEquals("۹۹۹", PersianNumberFormatter.format(999))
    }

    @Test
    fun `formatCompact handles millions`() {
        assertEquals("۱.۲ میلیون", PersianNumberFormatter.formatCompact(1_200_000))
    }

    @Test
    fun `formatCompact handles billions`() {
        assertEquals("۲.۵ میلیارد", PersianNumberFormatter.formatCompact(2_500_000_000))
    }

    @Test
    fun `formatCompact drops decimal when whole number`() {
        assertEquals("۲ میلیون", PersianNumberFormatter.formatCompact(2_000_000))
    }

    @Test
    fun `formatCompact falls back to plain format under one thousand`() {
        assertEquals("۹۵۰", PersianNumberFormatter.formatCompact(950))
    }
}
