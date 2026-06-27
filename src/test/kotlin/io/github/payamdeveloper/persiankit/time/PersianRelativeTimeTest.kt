package io.github.payamdeveloper.persiankit.time

import kotlin.test.Test
import kotlin.test.assertEquals

class PersianRelativeTimeTest {

    private val now = 1_000_000_000_000L // arbitrary fixed "now" for deterministic tests

    @Test
    fun `just now`() {
        assertEquals("چند لحظه پیش", PersianRelativeTime.format(now - 10_000, now))
    }

    @Test
    fun `minutes ago`() {
        assertEquals("۵ دقیقه پیش", PersianRelativeTime.format(now - 5 * 60_000, now))
    }

    @Test
    fun `hours ago`() {
        assertEquals("۳ ساعت پیش", PersianRelativeTime.format(now - 3 * 60 * 60_000L, now))
    }

    @Test
    fun `yesterday`() {
        val oneDayMs = 24 * 60 * 60_000L
        assertEquals("دیروز", PersianRelativeTime.format(now - oneDayMs - 1000, now))
    }

    @Test
    fun `days ago`() {
        val threeDaysMs = 3 * 24 * 60 * 60_000L
        assertEquals("۳ روز پیش", PersianRelativeTime.format(now - threeDaysMs, now))
    }

    @Test
    fun `future time`() {
        assertEquals("۵ دقیقه دیگر", PersianRelativeTime.format(now + 5 * 60_000, now))
    }
}
