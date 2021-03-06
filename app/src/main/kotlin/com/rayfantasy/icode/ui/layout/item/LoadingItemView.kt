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

package com.rayfantasy.icode.ui.layout.item

import android.view.Gravity
import com.benny.library.kbinding.adapterview.viewcreator.ItemViewBinderComponent
import org.jetbrains.anko.*

class LoadingItemView : ItemViewBinderComponent {
    override fun builder(): AnkoContext<*>.() -> Unit = {
        relativeLayout {
            frameLayout {
                progressBar {
                    isIndeterminate = true
                }.lparams(width = dip(24), height = dip(24)) { gravity = Gravity.CENTER }
            }.lparams(matchParent, wrapContent){
                topMargin = dip(4)
                bottomMargin = dip(4)
            }
        }
    }
}