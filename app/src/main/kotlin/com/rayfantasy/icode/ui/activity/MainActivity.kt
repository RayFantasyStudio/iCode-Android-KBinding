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

package com.rayfantasy.icode.ui.activity

import android.app.ActivityManager
import android.app.Fragment
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import com.benny.library.kbinding.annotation.Command
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.view.setContentView
import com.rayfantasy.icode.R
import com.rayfantasy.icode.theme.ThemeModel
import com.rayfantasy.icode.ui.fragment.AboutFragment
import com.rayfantasy.icode.ui.fragment.CodeListFragment
import com.rayfantasy.icode.ui.fragment.FavoriteFragment
import com.rayfantasy.icode.ui.layout.activity.MainActivityUI
import org.jetbrains.anko.configuration
import kotlin.properties.Delegates

class MainActivity : ActivityBase() {
    private val icon by lazy {
        BitmapFactory.decodeResource(resources, R.mipmap.ic_task_desc)
    }

    private val appName by lazy {
        getString(R.string.app_name)
    }

    private val colorPrimaryDarkCallback = { color: Int ->
        val tDesc = ActivityManager.TaskDescription(appName, icon, color)
        setTaskDescription(tDesc)
    }

    val codeListFragment by lazy { CodeListFragment() }
    val aboutFragment by lazy { AboutFragment() }
    val favoriteFragment by lazy { FavoriteFragment() }


    @delegate:Property
    var fragment by Delegates.property<Fragment>(codeListFragment)

    @delegate:Property
    var drawerOpen by Delegates.property(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this).bindTo(this)
    }

    @Command
    fun changeFragment(item: MenuItem) {
        when (item.itemId) {
            R.id.nav_home -> fragment = codeListFragment
            R.id.nav_about -> fragment = aboutFragment
            R.id.nav_favo -> fragment = favoriteFragment
        }
        drawerOpen = false
    }

    override fun onBackPressed() {
        if (drawerOpen == true)
            drawerOpen = false
        else
            super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        configuration(fromSdk = Build.VERSION_CODES.LOLLIPOP) {
            colorPrimaryDarkCallback(ThemeModel.colorPrimaryDark.value)
            ThemeModel.colorPrimaryDark.listeners.add(colorPrimaryDarkCallback)
        }
    }

    override fun onPause() {
        super.onPause()
        configuration(fromSdk = Build.VERSION_CODES.LOLLIPOP) {
            ThemeModel.colorPrimaryDark.listeners.remove(colorPrimaryDarkCallback)
        }
    }

}