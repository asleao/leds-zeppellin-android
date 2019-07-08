package br.com.ledszeppelin.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.LocalDate


class DateAdapter {

    @ToJson
    internal fun toJson(date: LocalDate): String {
        return date.toString()
    }

    @FromJson
    internal fun fromJson(date: String): LocalDate? {
        return if (date.isEmpty()) null else LocalDate.parse(date)
    }
}