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

package com.rayfantasy.icode

import android.app.Application
import android.content.pm.PackageManager
import com.raizlabs.android.dbflow.config.FlowManager
import com.rayfantasy.icode.postutil.PostUtil
import com.rayfantasy.icode.postutil.extension.v
import com.rayfantasy.icode.theme.ThemeModel
import com.tencent.bugly.crashreport.CrashReport

class BaseApplication : Application() {

    companion object {
        lateinit var instence: BaseApplication

        @JvmStatic
        fun getInstance() = instence
    }

    override fun onCreate() {
        super.onCreate()

        instence = this

        //初始化PostUtil
        PostUtil.init(this)

        val appInfo = this.packageManager
                .getApplicationInfo(packageName,
                        PackageManager.GET_META_DATA)
        val BUGLY_APP_ID = appInfo.metaData.getInt("BUGLY_APP_ID").toString()

        CrashReport.initCrashReport(this, BUGLY_APP_ID, BuildConfig.DEBUG);
        v("BUGLY_APP_ID = $BUGLY_APP_ID")

        FlowManager.init(this)

        ThemeModel.init(this)

        loadFavorite()
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }

    fun loadFavorite() {
        /*  PostUtil.findFavorite({
          Toast.makeText(this,"同步收藏中", Toast.LENGTH_SHORT).show()
          it.forEach {
              it.save()
          }
          Toast.makeText(this,"同步收藏完成", Toast.LENGTH_SHORT).show()
      },{t,rc -> Toast.makeText(this,"取消收藏失败,$rc", Toast.LENGTH_SHORT).show()})*/
    }
}
