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
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.view.setContentView
import com.rayfantasy.icode.R
import com.rayfantasy.icode.theme.ThemeModel
import com.rayfantasy.icode.ui.fragment.CodeListFragment
import com.rayfantasy.icode.ui.layout.MainActivityUI
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

    @delegate:Property
    var fragment by Delegates.property<Fragment>(codeListFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this).bindTo(this)
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