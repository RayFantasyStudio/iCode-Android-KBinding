
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

import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.appBarLayout
import com.rayfantasy.icode.extension.collapseModePin
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.theme.colorPrimary
import com.rayfantasy.icode.theme.colorPrimaryDark
import com.rayfantasy.icode.theme.observe
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout


class LoginActivityUI : ViewBinderComponent<LoginActivityUI> {
    companion object{
        val ID_USERNAME = generateViewId()
        val ID_PWD      = generateViewId()
        val ID_LOGIN    = generateViewId()
        val ID_SIGINUP  = generateViewId()
    }
    override fun builder(): AnkoContext<*>.() -> Unit =  {
        coordinatorLayout {
            appBarLayout(R.style.AppTheme_AppBarOverlay){
                observe(colorPrimary){backgroundColor}
                collapsingToolbarLayout{
                    observe(colorPrimary){ setContentScrimColor(it) }
                    observe(colorPrimaryDark){ setStatusBarScrimResource(it) }
                    toolbar {
                        popupTheme = R.style.AppTheme_PopupOverlay
                        collapseModePin()
                    }.lparams(matchParent,R.attr.actionBarSize)
                    fitsSystemWindows = true
                }.lparams(matchParent,matchParent){  }
                fitsSystemWindows = true
            }.lparams(matchParent,R.dimen.app_bar_height){  }


            //原来的include
            verticalLayout {
                setPadding(R.dimen.activity_horizontal_margin,R.dimen.activity_vertical_margin,R.dimen.activity_horizontal_margin,R.dimen.activity_vertical_margin)
            }.lparams(matchParent,matchParent)
        }.lparams(matchParent,matchParent)
    }
}