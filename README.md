# Persian Kotlin Kit

[![Build and Test](https://github.com/PayamDeveloper/persian-kotlin-kit/actions/workflows/build.yml/badge.svg)](https://github.com/PayamDeveloper/persian-kotlin-kit/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![JitPack](https://jitpack.io/v/PayamDeveloper/persian-kotlin-kit.svg)](https://jitpack.io/#PayamDeveloper/persian-kotlin-kit)

A small, dependency-free Kotlin library for everyday Persian text and number
formatting: digit conversion, thousands separators, Toman/Rial currency
strings, and human-friendly relative time — all in one MIT-licensed package.

Works in any JVM project: Android apps, Ktor/Spring backends, or plain
Kotlin scripts.

## Why this exists

Iranian developers building Kotlin apps end up rewriting the same few
utilities in every project (digit conversion, currency formatting, relative
timestamps), or copy-pasting them from old Stack Overflow answers. The
existing options on GitHub are either single-purpose, unmaintained, or
GPL-licensed in a way that complicates commercial use. This library bundles
the common ones under a permissive MIT license with test coverage.

## Install

Persian Kotlin Kit is published via [JitPack](https://jitpack.io).

Add the JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

Then add the dependency to your module's `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.github.PayamDeveloper:persian-kotlin-kit:0.2.0")
}
```

> Make sure to tag a release (e.g. `0.2.0`) on GitHub once the repo is
> created, so JitPack has something to build from.

## Usage

### Digit conversion

```kotlin
PersianDigits.toPersian("1,234")   // "۱,۲۳۴"
PersianDigits.toEnglish("۱۲۳۴")    // "1234"
PersianDigits.toEnglish("١٢٣")     // "123" (Arabic-Indic digits too)
```

### Number formatting

```kotlin
PersianNumberFormatter.format(1234567)          // "۱,۲۳۴,۵۶۷"
PersianNumberFormatter.formatCompact(1_200_000)  // "۱.۲ میلیون"
```

### Currency (Toman / Rial)

```kotlin
PersianCurrency.formatToman(150_000)                  // "۱۵۰,۰۰۰ تومان"
PersianCurrency.formatToman(1_200_000, compact = true) // "۱.۲ میلیون تومان"
PersianCurrency.formatRial(1_500_000)                 // "۱,۵۰۰,۰۰۰ ریال"
PersianCurrency.rialToToman(150_000)                  // 15000
```

### Relative time

```kotlin
PersianRelativeTime.format(messageTimestamp)
// "چند لحظه پیش" / "۵ دقیقه پیش" / "دیروز" / "۳ روز پیش" / "۲ سال پیش"
```

### Number-to-words (عدد به حروف)

```kotlin
PersianNumberWords.toWords(1234)      // "یک هزار و دویست و سی و چهار"
PersianNumberWords.toWords(1_000_000) // "یک میلیون"
PersianNumberWords.toWords(-5)        // "منفی پنج"
```

## Roadmap

- [ ] Ordinal numbers (اول، دوم، سوم...)
- [ ] Persian-locale fake data helpers (names, addresses) for testing
- [ ] Kotlin Multiplatform targets (iOS, JS) once the JVM API stabilizes

## Contributing

Issues and pull requests are welcome. If you're fixing a bug, please add a
test that fails before your fix and passes after.

## License

[MIT](LICENSE) — use it in commercial and closed-source projects without
restriction.
