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

import android.support.v7.widget.CardView
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R

import com.rayfantasy.icode.extension.lparams
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class ItemReplyCard : ViewBinderComponent<CardView>{
    override fun builder(): AnkoContext<*>.() -> Unit =  {
        cardView {
            isClickable = true
            foreground = resources.getDrawable(R.attr.selectableItemBackground)
            verticalLayout {
                relativeLayout {
                    imageView {
                        imageResource = R.mipmap.ic_user_black
                    }.lparams(resources.getDimension(R.dimen.profile_pic_size).toInt(), resources.getDimension(R.dimen.profile_pic_size).toInt()){ leftMargin = dip(16);topMargin = dip(10) }
                }.lparams(matchParent,wrapContent)
            }.lparams(matchParent,wrapContent)
        }.lparams(matchParent,wrapContent)
    }
}