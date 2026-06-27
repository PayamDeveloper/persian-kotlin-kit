package io.github.payamdeveloper.persiankit.words

import kotlin.math.abs

/**
 * Converts integers into their full Persian word form ("عدد به حروف").
 *
 * Supports the full practical range for currency/counting use cases —
 * up to roughly 999 quadrillion. Numbers larger than that (a vanishingly
 * rare case for any real app) throw [IllegalArgumentException].
 */
object PersianNumberWords {

    private val ones = arrayOf(
        "صفر", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه"
    )
    private val teens = arrayOf(
        "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده",
        "شانزده", "هفده", "هجده", "نوزده"
    )
    private val tensWords = arrayOf(
        "", "", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"
    )
    private val hundredsWords = arrayOf(
        "", "صد", "دویست", "سیصد", "چهارصد", "پانصد",
        "ششصد", "هفتصد", "هشتصد", "نهصد"
    )
    private val scales = arrayOf(
        "", "هزار", "میلیون", "میلیارد", "تریلیون", "کوادریلیون"
    )

    /**
     * Converts [number] into Persian words.
     *
     * Example: toWords(1234)    -> "یک هزار و دویست و سی و چهار"
     * Example: toWords(1000000) -> "یک میلیون"
     * Example: toWords(0)       -> "صفر"
     * Example: toWords(-5)      -> "منفی پنج"
     */
    fun toWords(number: Long): String {
        if (number == 0L) return ones[0]

        require(number != Long.MIN_VALUE) { "Number out of supported range" }
        val negative = number < 0
        val n = abs(number)

        val groups = mutableListOf<Int>()
        var remaining = n
        while (remaining > 0) {
            groups.add((remaining % 1000).toInt())
            remaining /= 1000
        }
        require(groups.size <= scales.size) { "Number out of supported range" }

        val parts = mutableListOf<String>()
        for (i in groups.indices.reversed()) {
            val groupValue = groups[i]
            if (groupValue == 0) continue
            val groupWords = threeDigitsToWords(groupValue)
            val scale = scales[i]
            parts.add(if (scale.isEmpty()) groupWords else "$groupWords $scale")
        }

        val result = parts.joinToString(" و ")
        return if (negative) "منفی $result" else result
    }

    private fun threeDigitsToWords(value: Int): String {
        val hundredsDigit = value / 100
        val rest = value % 100
        val segments = mutableListOf<String>()

        if (hundredsDigit > 0) segments.add(hundredsWords[hundredsDigit])

        when {
            rest == 0 -> {}
            rest < 10 -> segments.add(ones[rest])
            rest < 20 -> segments.add(teens[rest - 10])
            else -> {
                val tensDigit = rest / 10
                val onesDigit = rest % 10
                segments.add(tensWords[tensDigit])
                if (onesDigit > 0) segments.add(ones[onesDigit])
            }
        }

        return segments.joinToString(" و ")
    }
}
