package br.com.ledszeppelin.data.typeconverters

import androidx.room.TypeConverter
import org.joda.time.LocalDate

object LocalDateConverter {
    @TypeConverter
    fun toLocalDate(date: String?): LocalDate? {
        return if (date == null) null else LocalDate.parse(date)
    }

    @TypeConverter
    fun toString(date: LocalDate?): String? {
        return date?.toString()
    }
}