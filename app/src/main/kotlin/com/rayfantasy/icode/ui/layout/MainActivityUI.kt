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

package com.rayfantasy.icode.ui.layout

import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import com.benny.library.kbinding.dsl.TwoWay
import com.benny.library.kbinding.dsl.bind
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.*
import com.rayfantasy.icode.kbinding.drawerOpen
import com.rayfantasy.icode.kbinding.fragment
import com.rayfantasy.icode.kbinding.itemSelected
import com.rayfantasy.icode.theme.colorPrimary
import com.rayfantasy.icode.theme.colorPrimaryDark
import com.rayfantasy.icode.theme.observe
import com.rayfantasy.icode.ui.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView

class MainActivityUI : ViewBinderComponent<MainActivity> {
    companion object {
        val ID_CONTAINER = generateViewId()
        const val NAVIGATION_VIEW_GRAVITY = GravityCompat.START
    }

    override fun builder(): AnkoContext<*>.() -> Unit = {
        val activity = owner as MainActivity
        fittedDrawerLayout {
            val drawer = this
            configuration(sdk = Build.VERSION_CODES.KITKAT) { fitsSystemWindows = false }
            observe(colorPrimaryDark) { setStatusBarBackgroundColor(it) }
            bind { drawerOpen(NAVIGATION_VIEW_GRAVITY, "drawerOpen", mode = TwoWay) }

            coordinatorLayout {

                appBarLayout(R.style.AppTheme_AppBarOverlay) {

                    toolbar {
                        fitsSystemWindows = true
                        popupTheme = R.style.AppTheme_PopupOverlay
                        minimumHeight = dimenAttr(R.attr.actionBarSize)
                        observe(colorPrimary) { backgroundColor = it }
                        activity.setSupportActionBar(this)
                        val toggle = ActionBarDrawerToggle(
                                activity, drawer, this, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
                        drawer.addDrawerListener(toggle)
                        toggle.syncState()
                    }.lparams(matchParent, wrapContent)

                }.lparams(matchParent, wrapContent)

                frameLayout {
                    id = ID_CONTAINER
                    bind { fragment("fragment") }
                }.lparams(matchParent, matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }

            }.lparams(matchParent, matchParent)

            navigationView {
                fitsSystemWindows = true
                inflateMenu(R.menu.nv_menu)
                bind { itemSelected("changeFragment") }
            }.lparams(wrapContent, matchParent) {
                gravity = NAVIGATION_VIEW_GRAVITY
            }
        }.lparams(matchParent, matchParent)
    }
}