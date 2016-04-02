package com.rayfantasy.icode.ui.layout.item

import android.support.v4.view.GravityCompat
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.likeButton
import com.rayfantasy.icode.extension.lparams
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by Allen on 2016/4/3.
 */
class ItemCodeCard : ViewBinderComponent<ItemCodeCard> {
    companion object {
        val ID_USERICON   = generateViewId()
        val ID_USERNAME   = generateViewId()
        val ID_TIME       = generateViewId()
        val ID_TITLE      = generateViewId()
        val ID_SUBTITLE   = generateViewId()
        val ID_LIKE       = generateViewId()
        val ID_LIKECOUNT  = generateViewId()
        val ID_REPLYCOUNT = generateViewId()
    }
    override fun builder(): AnkoContext<*>.() -> Unit =  {

        cardView  {
            verticalLayout {
                relativeLayout {
                    imageView {
                        imageResource = R.mipmap.ic_account_box_black
                        id = ID_USERICON
                    }.lparams(R.dimen.profile_pic_size, R.dimen.profile_pic_size){ }
                    textView {
                        text = "UserName"
                        textSize    = sp(16).toFloat()
                    }.lparams(wrapContent, wrapContent){
                        leftMargin  = dip(56)
                    }
                    textView {
                        text        = "Time"
                        id          = ID_TIME
                    }.lparams(wrapContent, wrapContent){
                        rightMargin = dip(16)
                        marginEnd   = dip(16)
                        alignParentEnd()
                        alignParentRight()

                    }

                }.lparams(matchParent, wrapContent){
                    gravity = GravityCompat.START

                }
                linearLayout {
                    backgroundColor = R.color.primary_dark_material_dark
                }.lparams(matchParent,dip(1))
                verticalLayout{
                    textView {
                        id = ID_TITLE
                        text = "Title"
                    }.lparams(matchParent, wrapContent)
                    textView {
                        id = ID_SUBTITLE
                        text = "Subtitle"
                    }.lparams(matchParent, wrapContent)
                }
                linearLayout {
                    backgroundColor = R.color.primary_dark_material_dark
                }.lparams(matchParent,dip(1))
                relativeLayout {
                    likeButton {

                    }.lparams(wrapContent, wrapContent) {

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