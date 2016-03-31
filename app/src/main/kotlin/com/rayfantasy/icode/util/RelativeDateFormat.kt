/*
 * Copyright 2015-2016 RayFantasy Studio
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rayfantasy.icode.util

import android.content.Context

import com.rayfantasy.icode.R

private val ONE_MINUTE = 60000L
private val ONE_HOUR = ONE_MINUTE * 60
private val ONE_DAY = ONE_HOUR * 24
private val ONE_WEEK = ONE_DAY * 7
private val ONE_MONTH = ONE_DAY * 30
private val ONE_YEAR = ONE_MONTH * 12

fun ms2RelativeDate(context: Context, time: Long): String {
    val delta = System.currentTimeMillis() - time
    if (delta < ONE_MINUTE) {
        val seconds = toSeconds(delta)
        return context.getString(R.string.one_second_ago, if (seconds <= 0) 1 else seconds)
    }
    if (delta < 45 * ONE_MINUTE) {
        val minutes = toMinutes(delta)
        return context.getString(R.string.one_minute_ago, if (minutes <= 0) 1 else minutes)
    }
    if (delta < ONE_DAY) {
        val hours = toHours(delta)
        return context.getString(R.string.one_hour_ago, if (hours <= 0) 1 else hours)
    }
    if (delta < 30 * ONE_DAY) {
        val days = toDays(delta)
        return context.getString(R.string.one_day_ago, if (days <= 0) 1 else days)
    }
    if (delta < ONE_YEAR) {
        val months = toMonths(delta)
        return context.getString(R.string.one_month_ago, if (months <= 0) 1 else months)
    } else {
        val years = toYears(delta)
        return context.getString(R.string.one_year_ago, if (years <= 0) 1 else years)
    }
}

private fun toSeconds(date: Long): Long {
    return date / 1000L
}

private fun toMinutes(date: Long): Long {
    return toSeconds(date) / 60L
}

private fun toHours(date: Long): Long {
    return toMinutes(date) / 60L
}

private fun toDays(date: Long): Long {
    return toHours(date) / 24L
}

private fun toMonths(date: Long): Long {
    return toDays(date) / 30L
}

private fun toYears(date: Long): Long {
    return toMonths(date) / 365L
}
