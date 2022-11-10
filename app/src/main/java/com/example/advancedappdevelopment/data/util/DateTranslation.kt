package com.example.bkskjold.data.util

import android.icu.util.Calendar

/**
 * To have more control of how our date is displayed, a translation from googles Timestamp value to strings is being done here.
 */

//11/1
fun getDayMonth(timestamp: com.google.firebase.Timestamp): String {
	val c: Calendar = Calendar.getInstance()
	c.time = timestamp.toDate()
	return "${c.get(Calendar.DAY_OF_MONTH)}/${c.get(Calendar.MONTH) + 1}"
}

//1 (month number)
fun getMonthNum(timestamp: com.google.firebase.Timestamp): Int {
	val c: Calendar = Calendar.getInstance()
	c.time = timestamp.toDate()
	return c.get(Calendar.MONTH) + 1
}

//11 (day in month)
fun getDay(timestamp: com.google.firebase.Timestamp): Int {
	val c: Calendar = Calendar.getInstance()
	c.time = timestamp.toDate()
	return c.get(Calendar.DAY_OF_MONTH)
}

//13:30
fun getTime(timestamp: com.google.firebase.Timestamp): String {
	var hour = timestamp.toDate().hours.toString()
	var minute = timestamp.toDate().minutes.toString()
	if (minute.length == 1) {
		minute += 0
	}
	if (hour.length == 1) {
		hour = "0$hour"
	}
	return "${hour}:${minute}"
}

//mandag
fun getWeekDay(timestamp: com.google.firebase.Timestamp): String {
	val c: Calendar = Calendar.getInstance()
	c.time = timestamp.toDate()
	val dayOfWeekNum: Int = c.get(Calendar.DAY_OF_WEEK)
	return getWeekFromNum(dayOfWeekNum - 1)
}

//januar
fun getMonthString(timestamp: com.google.firebase.Timestamp): String {
	val c: Calendar = Calendar.getInstance()
	c.time = timestamp.toDate()
	val monthNum: Int = c.get(Calendar.MONTH)
	return getMonthFromNum(monthNum + 1)
}

//2022
fun getYear(timestamp: com.google.firebase.Timestamp): Int {
	val c: Calendar = Calendar.getInstance()
	c.time = timestamp.toDate()
	return c.get(Calendar.YEAR)
}

fun getWeekFromNum(num: Int): String {
	val dayOfWeek = when (num) {
		1 -> "monday"
		2 -> "tuesday"
		3 -> "wednesday"
		4 -> "thursday"
		5 -> "friday"
		6 -> "saturday"
		7 -> "sunday"
		else -> "sunday"
	}
	return dayOfWeek
}

fun getMonthFromNum(num: Int): String {
	val month = when (num) {
		1 -> "january"
		2 -> "february"
		3 -> "marts"
		4 -> "april"
		5 -> "may"
		6 -> "june"
		7 -> "july"
		8 -> "august"
		9 -> "september"
		10 -> "october"
		11 -> "november"
		12 -> "december"
		else -> "null"
	}
	return month
}