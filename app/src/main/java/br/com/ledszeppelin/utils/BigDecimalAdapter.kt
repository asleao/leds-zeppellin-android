package br.com.ledszeppelin.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal


class BigDecimalAdapter {
    @ToJson
    internal fun toJson(number: BigDecimal): String {
        return number.toString()
    }

    @FromJson
    internal fun fromJson(number: String?): BigDecimal {
        return if (number == null || number.isEmpty())
            BigDecimal("")
        else
            BigDecimal(number)
    }
}