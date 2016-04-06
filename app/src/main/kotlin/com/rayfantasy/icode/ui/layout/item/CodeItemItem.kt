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

import android.support.v4.view.GravityCompat
import com.benny.library.kbinding.adapterview.viewcreator.ItemViewBinderComponent
import com.benny.library.kbinding.common.bindings.text
import com.benny.library.kbinding.common.bindings.textColorResource
import com.benny.library.kbinding.dsl.bind
import com.like.LikeButton
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.*
import com.rayfantasy.icode.kbinding.time
import org.jetbrains.anko.*

class CodeItemItem : ItemViewBinderComponent {
    override fun builder(): AnkoContext<*>.() -> Unit = {

        cardView(R.style.FullWidthCardView) {
            setCardBackgroundColor(color(R.color.cardview_light_background))
            verticalLayout {
                weightSum = 1.toFloat()
                dividerDrawable = drawable(R.drawable.divider_line)
                relativeLayout {
                    gravity = GravityCompat.START
                    imageView {
                        imageResource = R.mipmap.ic_account_box_black
                    }.lparams(dimen(R.dimen.profile_pic_size), dimen(R.dimen.profile_pic_size)) { centerVertically() }

                    // username
                    textView {
                        bind { text("username") }
                        textSize = sp(6).toFloat()
                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(40)
                        //topMargin   = dip(16)
                        rightMargin = dip(100)
                        centerVertically()
                    }
                    // time
                    textView {
                        bind { time("updateAt") }
                    }.lparams(wrapContent, wrapContent) {
                       // rightMargin = dip(16)
                        marginEnd = dip(16)
                        alignParentEnd()
                        alignParentRight()
                        centerVertically()
                    }

                }.lparams(matchParent, wrapContent) { bottomMargin = dip(4) }
                linearLayout { backgroundColor = resources.getColor(R.color.line) }.lparams(matchParent,dip(1))
                verticalLayout {
                    // Title
                    textView {
                        bind { text("title") }
                        textSize = sp(7).toFloat()
                        textColorResource = R.color.black_overlay
                    }.lparams(matchParent, wrapContent)
                    // Sub Title
                    textView {
                        bind { text("subtitle") }
                        textSize = dip(5).toFloat()
                    }.lparams(matchParent, wrapContent)
                }.lparams(matchParent, matchParent) { bottomMargin = dip(3) }
                linearLayout { backgroundColor = resources.getColor(R.color.line) }.lparams(matchParent,dip(1))
                relativeLayout {
                    likeButton {
                        setIconSizePx(40)


                    }.lparams(wrapContent, wrapContent) {
                       // leftMargin = dip(16)
                       // marginStart = dip(15)
                        centerVertically()


                    }
                    // Favorite Count
                    textView {
                        bind { text("favorite") }
                    }.lparams(wrapContent, wrapContent) {
                        centerVertically()
                        leftMargin = dip(45)

                    }
                    // Reply Count
                    textView {
                        bind { text("reply") }
                    }.lparams(wrapContent, wrapContent) {
                        centerVertically()
                        alignParentRight()

                    }
                }

            }.lparams(matchParent, wrapContent) {
                topMargin = dip(10)
                bottomMargin = dip(10)
                leftMargin = dip(16)
                rightMargin = dip(16)
            }
        }.lparams(matchParent, wrapContent) { bottomMargin = dip(4) }
    }

}