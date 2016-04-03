
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

package com.rayfantasy.icode.ui.layout.extra


import android.support.design.widget.NavigationView
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.theme.colorPrimary
import com.rayfantasy.icode.theme.observe
import org.jetbrains.anko.*


class MainNavigationHearderUI : ViewBinderComponent<NavigationView> {
    companion object {
        val ID_USERICON = generateViewId()
        val ID_NV_BG = generateViewId()
        val ID_USERNAME = generateViewId()
    }

    override fun builder(): AnkoContext<*>.() -> Unit = {
        verticalLayout {
            id = ID_NV_BG
            leftPadding = dip(20)
            observe(colorPrimary) { backgroundColor = it }


            imageView {
                id = ID_USERICON
                imageResource = R.mipmap.ic_nv_user
            }.lparams(dip(72), dip(72)) { topMargin = dip(20) }
            textView {
                id = ID_USERNAME
                textSize = sp(25).toFloat()
                setTextColor(R.color.nv_username)
                text = "未登录"
            }.lparams(matchParent, wrapContent) { topMargin = dip(20) }
        }.lparams(matchParent, dip(211))
    }
}