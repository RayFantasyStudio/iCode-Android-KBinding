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
import android.app.DownloadManager
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import com.benny.library.kbinding.annotation.Command
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.view.setContentView
import com.rayfantasy.icode.BuildConfig
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.rx.alertObservable
import com.rayfantasy.icode.extension.rx.filterSingle
import com.rayfantasy.icode.postutil.PostUtil
import com.rayfantasy.icode.postutil.bean.UpdateInfo
import com.rayfantasy.icode.postutil.service.ICodeService
import com.rayfantasy.icode.theme.ThemeModel
import com.rayfantasy.icode.ui.fragment.AboutFragment
import com.rayfantasy.icode.ui.fragment.CodeListFragment
import com.rayfantasy.icode.ui.fragment.FavoriteFragment
import com.rayfantasy.icode.ui.layout.activity.MainActivityUI
import com.rayfantasy.icode.util.DownloadsUtil
import org.jetbrains.anko.configuration
import org.jetbrains.anko.startActivity
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.observable
import rx.schedulers.Schedulers
import java.io.File
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

class MainActivity : ActivityBase() {
    companion object {
        const val TAG_CHECK_UPDATE = "tag_check_update"
    }

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
            R.id.nav_update -> checkAndUpdate()
            R.id.nav_homepage -> OpenWeb()
        }
        drawerOpen = false
    }

    @Command
    fun toAccount(){
        if (PostUtil.user == null){
            startActivity<LoginActivity>()
        }
    }


    private fun checkAndUpdate() {
        ICodeService.updateInfo()
                .filterSingle {
                    it.versionCode > BuildConfig.VERSION_CODE
                }
                .flatMap { info ->
                    alertObservable<UpdateInfo> (getString(R.string.msg_new_version, info.info, info.size),
                            getString(R.string.title_new_version, info.versionName)) {
                        neutralButton(android.R.string.cancel)
                        positiveButton(R.string.positive_new_version) { it.onNext(info) }
                        show()
                    }
                }
                .flatMap {
                    val title = getString(R.string.title_new_version, it.versionName)
                    val downloadInfo = DownloadsUtil.add(applicationContext, title, it.url, null)
                    val contextRef = WeakReference<Context>(applicationContext)
                    observable<DownloadsUtil.DownloadInfo> {
                        var info: DownloadsUtil.DownloadInfo
                        do {
                            Thread.sleep(500)
                            info = DownloadsUtil.getById(contextRef.get(), downloadInfo.id)
                            if (isFinishing) break
                        } while ((info.status and (DownloadManager.STATUS_PENDING or
                                DownloadManager.STATUS_PAUSED or DownloadManager.STATUS_RUNNING)) != 0)
                        it.onNext(info)
                    }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                }
                .subscribe {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.fromFile(File(it.localFilename)), DownloadsUtil.MIME_TYPE_APK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    applicationContext.startActivity(intent)
                }
    }

    @Command
    fun OpenWeb() {
        var uri = Uri.parse("http://www.rayfantasy.com")
        var intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
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