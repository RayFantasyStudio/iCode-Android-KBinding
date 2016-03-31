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
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.benny.library.kbinding.dsl.bind
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.appBarLayout
import com.rayfantasy.icode.extension.dimenAttr
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.kbinding.fragment
import com.rayfantasy.icode.theme.colorPrimary
import com.rayfantasy.icode.theme.colorPrimaryDark
import com.rayfantasy.icode.theme.observe
import com.rayfantasy.icode.ui.activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout

class MainActivityUI : ViewBinderComponent<MainActivity> {
    companion object {
        val ID_CONTAINER = generateViewId()
    }

    override fun builder(): AnkoContext<*>.() -> Unit = {
        val activity = owner as MainActivity
        drawerLayout {
            val drawer = this
            fitsSystemWindows = true
            configuration(sdk = Build.VERSION_CODES.KITKAT) { fitsSystemWindows = false }
            configuration(fromSdk = Build.VERSION_CODES.LOLLIPOP) {
                observe(colorPrimaryDark) { setStatusBarBackgroundColor(it) }
                systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                setOnApplyWindowInsetsListener({ view, insets ->
                    val draw = insets.systemWindowInsetTop > 0
                    if (view is DrawerLayout) {
                        view.setChildInsets(insets, draw)
                    }
                    return@setOnApplyWindowInsetsListener insets.consumeSystemWindowInsets()
                })
            }

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
            }.lparams(wrapContent, matchParent) {
                gravity = GravityCompat.START
            }
        }.lparams(matchParent, matchParent)
    }
}