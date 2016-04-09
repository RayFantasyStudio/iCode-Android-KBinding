
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

package com.rayfantasy.icode.ui.layout.activity

import android.text.Editable
import android.text.InputType
import android.widget.EditText
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.appBarLayout
import com.rayfantasy.icode.extension.collapseModePin
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.theme.colorPrimary
import com.rayfantasy.icode.theme.colorPrimaryDark
import com.rayfantasy.icode.theme.observe
import com.rayfantasy.icode.ui.activity.LoginActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.textInputLayout


class LoginActivityUI : ViewBinderComponent<LoginActivity> {
    override fun builder(): AnkoContext<*>.() -> Unit =  {
        coordinatorLayout {
            appBarLayout(R.style.AppTheme_AppBarOverlay){
                //observe(colorPrimary){backgroundColor = it}
                collapsingToolbarLayout{
                    //observe(colorPrimary){ setContentScrimColor(it) }
                    //observe(colorPrimaryDark){ setStatusBarScrimResource(it) }
                    toolbar {
                        collapseModePin()
                    }.lparams(matchParent,R.attr.actionBarSize)
                    fitsSystemWindows = true
                }.lparams(matchParent,matchParent){  }
                fitsSystemWindows = true
            }.lparams(matchParent,R.dimen.app_bar_height){  }


            //原来的include
            verticalLayout {
                setPadding(R.dimen.activity_horizontal_margin,R.dimen.activity_vertical_margin,R.dimen.activity_horizontal_margin,R.dimen.activity_vertical_margin)
                textView {
                    padding = dip(5)
                    text = resources.getText(R.string.info_login)
                }.lparams(matchParent,wrapContent)
                textInputLayout {
                    editText {
                        inputType = InputType.TYPE_CLASS_TEXT
                        maxLines = 1
                        singleLine = true
                    }.lparams(matchParent,wrapContent)
                }.lparams(matchParent,wrapContent)
                textInputLayout {
                    editText {
                        hint = resources.getText(R.string.prompt_password)
                        maxLines = 1
                        singleLine = true

                    }.lparams(matchParent,wrapContent) {  }
                }.lparams(matchParent,wrapContent) {  }
                button{
                    padding = dip(12)
                    text = resources.getText(R.string.login)
                }.lparams(matchParent,wrapContent){topMargin = dip(10)}
                button {
                    padding = dip(12)
                    text = resources.getText(R.string.register)
                }.lparams(matchParent,wrapContent){topMargin = dip(5)}
            }.lparams(matchParent,matchParent)
        }.lparams(matchParent,matchParent)
    }
}