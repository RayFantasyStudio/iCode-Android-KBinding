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

package com.rayfantasy.icode.kbinding

import android.app.Activity
import android.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.benny.library.kbinding.bind.oneWayPropertyBinding
import com.benny.library.kbinding.converter.EmptyOneWayConverter1
import com.benny.library.kbinding.converter.OneWayConverter
import com.rayfantasy.icode.extension.generateViewId
import rx.functions.Action1

fun ViewGroup.fragment(vararg paths: String, converter: OneWayConverter<*, Fragment> = EmptyOneWayConverter1())
        = oneWayPropertyBinding(paths, fragment(this), false, converter)

private fun fragment(container: ViewGroup) = Action1<Fragment> {
    val ctx = container.context
    if (ctx is Activity) {
        if (container.id == View.NO_ID)
            container.id = generateViewId()
        val transaction = ctx.fragmentManager.beginTransaction()
        transaction.replace(container.id, it).commit()
    }
}