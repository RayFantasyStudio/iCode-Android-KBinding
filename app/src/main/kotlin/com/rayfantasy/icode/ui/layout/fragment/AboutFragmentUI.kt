
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

package com.rayfantasy.icode.ui.layout.fragment

import android.support.v4.widget.TextViewCompat
import android.view.Gravity
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.ui.fragment.AboutFragment
import org.jetbrains.anko.*


class AboutFragmentUI() : ViewBinderComponent<AboutFragment> {
    override fun builder(): AnkoContext<*>.() -> Unit = {
        verticalLayout {
            imageView {
                setImageResource(R.mipmap.ic_launcher)
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP

            }.lparams(dip(185), dip(185)) {
                topMargin = dip(30)

            }
            textView {
                text = resources.getText(R.string.app_name_cn)
                gravity = Gravity.CENTER
                textSize = sp(16).toFloat()
            }.lparams(matchParent, wrapContent)
            textView {
                text = resources.getText(R.string.version)
                gravity = Gravity.CENTER
                textSize = sp(12).toFloat()
                TextViewCompat.setTextAppearance(this, android.R.attr.textAppearanceMedium)
                //getStringResources(activity,R.string.app_name_cn)
            }.lparams(matchParent, wrapContent) { topMargin = dip(50) }
        }.lparams(matchParent, matchParent)
    }
}