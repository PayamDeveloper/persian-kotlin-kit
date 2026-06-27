package io.github.payamdeveloper.persiankit.digits

/**
 * Converts numeric characters between English (0-9), Persian (۰-۹),
 * and Arabic-Indic (٠-٩) digit scripts.
 *
 * This is the foundation almost every other utility in this library is
 * built on top of, since Persian apps constantly need to display
 * Persian digits while doing math in plain English digits.
 */
object PersianDigits {

    private val persian = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
    private val arabic = charArrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
    private val english = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    /**
     * Converts any English or Arabic-Indic digits found in [input] to Persian digits.
     * Non-digit characters (including separators like ',' or '.') are left untouched.
     *
     * Example: toPersian("1,234") -> "۱,۲۳۴"
     */
    fun toPersian(input: String): String = convert(input, target = persian)

    /**
     * Converts any Persian or Arabic-Indic digits found in [input] to English digits.
     * Useful right before doing math/parsing on user-entered text.
     *
     * Example: toEnglish("۱۲۳۴") -> "1234"
     */
    fun toEnglish(input: String): String = convert(input, target = english)

    private fun convert(input: String, target: CharArray): String {
        val sb = StringBuilder(input.length)
        for (c in input) {
            val index = persian.indexOf(c).takeIf { it >= 0 }
                ?: arabic.indexOf(c).takeIf { it >= 0 }
                ?: english.indexOf(c).takeIf { it >= 0 }
            sb.append(if (index != null) target[index] else c)
        }
        return sb.toString()
    }

    /** True if [input] contains at least one Persian or Arabic-Indic digit. */
    fun containsNonEnglishDigits(input: String): Boolean =
        input.any { it in persian || it in arabic }
}
