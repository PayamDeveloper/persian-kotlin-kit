package io.github.payamdeveloper.persiankit.currency

import io.github.payamdeveloper.persiankit.number.PersianNumberFormatter

/**
 * Formats amounts as Persian currency strings (Toman / Rial).
 *
 * Iranian apps almost always need to juggle both units since prices are
 * commonly quoted in Toman while official/banking records use Rial
 * (1 Toman = 10 Rial).
 */
object PersianCurrency {

    /** Converts an amount in Rial to Toman. */
    fun rialToToman(rial: Long): Long = rial / 10

    /** Converts an amount in Toman to Rial. */
    fun tomanToRial(toman: Long): Long = toman * 10

    /**
     * Formats [amount] (already in Toman) as a currency string.
     *
     * Example: formatToman(150000) -> "۱۵۰,۰۰۰ تومان"
     * Example: formatToman(1_200_000, compact = true) -> "۱.۲ میلیون تومان"
     */
    fun formatToman(amount: Long, compact: Boolean = false): String =
        "${formatNumber(amount, compact)} تومان"

    /**
     * Formats [amount] (already in Rial) as a currency string.
     *
     * Example: formatRial(1_500_000) -> "۱,۵۰۰,۰۰۰ ریال"
     */
    fun formatRial(amount: Long, compact: Boolean = false): String =
        "${formatNumber(amount, compact)} ریال"

    private fun formatNumber(amount: Long, compact: Boolean): String =
        if (compact) {
            PersianNumberFormatter.formatCompact(amount)
        } else {
            PersianNumberFormatter.format(amount)
        }
}
