package io.github.payamdeveloper.persiankit.number

import io.github.payamdeveloper.persiankit.digits.PersianDigits
import java.util.Locale
import kotlin.math.abs

/**
 * Formats plain numbers the way a Persian-speaking user expects to read them:
 * Persian digits, comma-grouped thousands, and a compact form for big numbers.
 */
object PersianNumberFormatter {

    /**
     * Formats [number] with thousands separators.
     *
     * Example: format(1234567) -> "۱,۲۳۴,۵۶۷"
     */
    fun format(
        number: Long,
        useGrouping: Boolean = true,
        useEasternDigits: Boolean = true
    ): String {
        val raw = if (useGrouping) groupThousands(number) else number.toString()
        return if (useEasternDigits) PersianDigits.toPersian(raw) else raw
    }

    /**
     * Formats large numbers in a short, human-readable Persian form.
     *
     * Example: formatCompact(1_200_000) -> "۱.۲ میلیون"
     * Example: formatCompact(950)       -> "۹۵۰"
     */
    fun formatCompact(number: Long, useEasternDigits: Boolean = true): String {
        val sign = if (number < 0) "-" else ""
        val n = abs(number)

        val (value, unit) = when {
            n >= 1_000_000_000_000L -> n / 1_000_000_000_000.0 to "تریلیون"
            n >= 1_000_000_000L -> n / 1_000_000_000.0 to "میلیارد"
            n >= 1_000_000L -> n / 1_000_000.0 to "میلیون"
            n >= 1_000L -> n / 1_000.0 to "هزار"
            else -> return format(number, useGrouping = false, useEasternDigits = useEasternDigits)
        }

        val rounded = String.format(Locale.ROOT, "%.1f", value).removeSuffix(".0")
        val text = "$sign$rounded $unit"
        return if (useEasternDigits) PersianDigits.toPersian(text) else text
    }

    private fun groupThousands(number: Long): String {
        val negative = number < 0
        val digits = abs(number).toString()
        val grouped = digits.reversed().chunked(3).joinToString(",").reversed()
        return (if (negative) "-" else "") + grouped
    }
}
