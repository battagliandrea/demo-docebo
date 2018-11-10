package com.demodocebo.test.ui.utils

import org.apache.commons.lang3.time.DateParser
import org.apache.commons.lang3.time.FastDateFormat
import java.util.*


fun Date.format(formatter: FastDateFormat) = formatter.format(this)


fun Date.format(pattern: String) = format(FastDateFormat.getInstance(pattern))


fun String.toDate(dateParser: DateParser) = dateParser.parse(this)


fun String.toDate(pattern: String) = toDate(FastDateFormat.getInstance(pattern))


fun String.toCalendar(dateParser: DateParser): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = dateParser.parse(this)
    return calendar
}

fun String.toCalendar(pattern: String) = toCalendar(FastDateFormat.getInstance(pattern)) 