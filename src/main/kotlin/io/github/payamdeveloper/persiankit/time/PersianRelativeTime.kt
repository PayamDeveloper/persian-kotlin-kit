package io.github.payamdeveloper.persiankit.time

import io.github.payamdeveloper.persiankit.digits.PersianDigits
import kotlin.math.abs

/**
 * Formats a timestamp as a Persian relative-time string, the way a chat
 * or social app shows "5 minutes ago" / "yesterday" etc.
 */
object PersianRelativeTime {

    private const val MINUTE_MS = 60_000L
    private const val HOUR_MS = 60 * MINUTE_MS
    private const val DAY_MS = 24 * HOUR_MS
    private const val WEEK_MS = 7 * DAY_MS
    private const val MONTH_MS = 30 * DAY_MS
    private const val YEAR_MS = 365 * DAY_MS

    /**
     * Formats the difference between [timestampMillis] and [nowMillis].
     *
     * Examples: "چند لحظه پیش", "۵ دقیقه پیش", "دیروز", "۳ روز پیش", "۲ سال پیش"
     */
    fun format(timestampMillis: Long, nowMillis: Long = System.currentTimeMillis()): String {
        val diff = nowMillis - timestampMillis
        val isFuture = diff < 0
        val absDiff = abs(diff)
        val suffix = if (isFuture) "دیگر" else "پیش"

        if (absDiff < MINUTE_MS) {
            return if (isFuture) "چند لحظه دیگر" else "چند لحظه پیش"
        }
        if (absDiff < 2 * DAY_MS && absDiff >= DAY_MS) {
            return if (isFuture) "فردا" else "دیروز"
        }

        val (value, unit) = when {
            absDiff < HOUR_MS -> (absDiff / MINUTE_MS) to "دقیقه"
            absDiff < DAY_MS -> (absDiff / HOUR_MS) to "ساعت"
            absDiff < WEEK_MS -> (absDiff / DAY_MS) to "روز"
            absDiff < MONTH_MS -> (absDiff / WEEK_MS) to "هفته"
            absDiff < YEAR_MS -> (absDiff / MONTH_MS) to "ماه"
            else -> (absDiff / YEAR_MS) to "سال"
        }

        val persianValue = PersianDigits.toPersian(value.toString())
        return "$persianValue $unit $suffix"
    }
}
