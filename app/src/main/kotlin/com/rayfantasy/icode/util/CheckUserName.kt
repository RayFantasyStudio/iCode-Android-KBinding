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

/**
 * Created by qweas on 2016/2/4 0004.
 */
fun checkName(username: String): Boolean {
    var flag: Int = 1
    for (i in username) {
        if (isChinese(i)) {
            continue
        } else {
            if (isEnglish(i)) {
                continue
            } else {
                if (isNumber(i)) {
                    continue
                } else {
                    if (isOther(i)) {
                        continue
                    } else {
                        flag = 0; break
                    }
                }
            }
        }

    }
    if (flag == 0) return false else return true
}

fun isChinese(c: Char): Boolean {
    val ub: Character.UnicodeBlock = Character.UnicodeBlock.of(c)
    if (       ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
        return true;
    }
    return false;
}

fun isEnglish(c: Char): Boolean {
    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) return true
    return false
}

fun isNumber(c: Char): Boolean {
    if (c <= '9' && c >= '0') return true
    return false
}

fun isOther(c: Char): Boolean {
    if ( c == '_') return true
    return false
}