
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

import android.graphics.drawable.Drawable
import android.support.v4.view.GravityCompat
import android.support.v7.widget.CardView
import com.benny.library.kbinding.view.ViewBinderComponent
import com.like.LikeButton
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.likeButton
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.theme.observe
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class ItemCodeCard : ViewBinderComponent<CardView> {
    override fun builder(): AnkoContext<*>.() -> Unit =  {

        cardView  {

            setCardBackgroundColor(getResources().getColor(R.color.cardview_light_background))
            verticalLayout {
                weightSum = 1.toFloat()
                relativeLayout {
                    gravity = GravityCompat.START
                    imageView {
                        imageResource = R.mipmap.ic_account_box_black
                    }.lparams(resources.getDimension(R.dimen.profile_pic_size).toInt(), resources.getDimension(R.dimen.profile_pic_size).toInt()){ centerVertically() }
                    textView {
                        text = "UserName"

                        textSize    = dip(6).toFloat()
                    }.lparams(wrapContent, wrapContent){
                        leftMargin  = dip(76)
                        //topMargin   = dip(16)
                        rightMargin = dip(100)
                        centerVertically()
                    }
                    textView {
                        text        = "Time"
                    }.lparams(wrapContent, wrapContent){
                        rightMargin = dip(16)
                        marginEnd   = dip(16)
                        alignParentEnd()
                        alignParentRight()
                        centerVertically()

                    }

                }.lparams(matchParent, wrapContent){ bottomMargin = dip(4) }
                linearLayout {
                    backgroundColor = resources.getColor(R.color.line)
                }.lparams(matchParent,dip(1))
                verticalLayout{
                    textView {
                        text = "Title"
                        textSize = dip(7).toFloat()
                        textColor = resources.getColor(R.color.black_overlay)
                    }.lparams(matchParent, wrapContent)
                    textView {
                        text = "Subtitle"
                        textSize = dip(5).toFloat()
                    }.lparams(matchParent, wrapContent)
                }.lparams(matchParent,matchParent){bottomMargin = dip(3) }
                linearLayout {
                    backgroundColor = resources.getColor(R.color.line)
                }.lparams(matchParent,dip(1))
                relativeLayout {
                    likeButton {
                        setIconSizeDp(16)
                    }.lparams(wrapContent, wrapContent) {
                        leftMargin = dip(16)
                        marginStart = dip(15)

                    }
                    textView {
                        text = "999K"
                    }.lparams(wrapContent,wrapContent){
                        centerVertically()
                        leftMargin = dip(45)

                    }
                    textView {
                        text = "999K"
                    }.lparams(wrapContent,wrapContent){
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
        }.lparams(matchParent, wrapContent) {  }
    }

}