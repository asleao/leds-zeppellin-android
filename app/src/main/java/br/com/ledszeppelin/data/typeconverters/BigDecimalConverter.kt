package br.com.ledszeppelin.data.typeconverters

import androidx.room.TypeConverter
import java.math.BigDecimal


object BigDecimalConverter {
    @TypeConverter
    fun fromLong(number: Long?): BigDecimal? {
        return if (number == null) null else BigDecimal(number).movePointLeft(2).setScale(1, BigDecimal.ROUND_HALF_EVEN)
    }

    @TypeConverter
    fun toLong(number: BigDecimal?): Long? {
        return number?.multiply(BigDecimal(100))?.toLong()
    }
}