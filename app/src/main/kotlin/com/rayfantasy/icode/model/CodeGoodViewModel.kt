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

package com.rayfantasy.icode.model

import com.benny.library.kbinding.adapterview.viewmodel.ItemViewModel
import com.benny.library.kbinding.annotation.DependencyProperty
import com.benny.library.kbinding.annotation.ExtractProperty
import com.rayfantasy.icode.postutil.bean.CodeGood
import kotlin.properties.Delegates

class CodeGoodViewModel : ItemViewModel<CodeGood>() {
    @delegate:ExtractProperty("title", "subtitle", "username", "content", "createAt", "updateAt",
            "id", hasPrefix = false)
    var codeGood by Delegates.property<CodeGood>()

    @delegate:DependencyProperty("codeGood")
    val favorite by Delegates.property { codeGood?.favorite.toString() }

    @delegate:DependencyProperty("codeGood")
    val reply by Delegates.property { codeGood?.reply.toString() }

    override fun onDataChange(data: CodeGood?) {
        codeGood = data
    }
}