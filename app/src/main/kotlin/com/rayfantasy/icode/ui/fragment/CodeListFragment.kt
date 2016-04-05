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

package com.rayfantasy.icode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.benny.library.kbinding.annotation.Command
import com.benny.library.kbinding.annotation.Property
import com.raizlabs.android.dbflow.sql.language.Select
import com.rayfantasy.icode.extra.UpdateAbleList
import com.rayfantasy.icode.postutil.PostUtil
import com.rayfantasy.icode.postutil.bean.CodeGood
import com.rayfantasy.icode.postutil.bean.CodeGood_Table
import com.rayfantasy.icode.ui.layout.fragment.CodeListFragmentUI
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast
import kotlin.properties.Delegates

class CodeListFragment : FragmentBase() {
    private var request: Request<*>? = null

    private var cachedData: List<CodeGood>
        get() = Select()
                .from(CodeGood::class.java)
                .orderBy(CodeGood_Table.updateat, false)
                .queryList()
        set(value) {
            value.forEach {
                it.loadContentFromCache()
                it.save()
            }
        }

    @delegate:Property
    var codeGoods by Delegates.property<MutableList<CodeGood>>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = CodeListFragmentUI().createViewBinder(ctx, this).bindTo(this)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = cachedData
        if (list.isNotEmpty()) codeGoods = UpdateAbleList(list)
        loadCodeGoods(true, {})
    }

    private fun loadCodeGoods(refresh: Boolean, canExecute: (Boolean) -> Unit) {
        //如果正在刷新，则不再发起新的刷新请求
        if (request != null) {
            canExecute(true)
            return
        }

        val condition = "${if (!refresh && codeGoods?.isNotEmpty() ?: true) "WHERE updateat < ${codeGoods?.last()?.updateAt} " else ""}" +
                "ORDER BY updateat DESC LIMIT 0, 10"

        request = PostUtil.selectCodeGood(condition) {
            onSuccess {
                if (isDetached) return@onSuccess
                request = null
                if (codeGoods == null)
                    codeGoods = UpdateAbleList(it)
                else
                    (codeGoods as MutableList<CodeGood>).addAll(it)
                cachedData = it
                canExecute(true)
            }
            onFailed { t, rc ->
                request = null
                if (isDetached || view == null) return@onFailed
                canExecute(true)
            }
        }
    }

    @Command
    fun codeGoodDetail(params: Int) {
        toast("not implements yet")
    }

    @Command
    fun reloadCodeGoods(canExecute: (Boolean) -> Unit) {
        loadCodeGoods(true, canExecute)
    }

    @Command
    fun loadMoreCodeGood(canExecute: (Boolean) -> Unit) {
        loadCodeGoods(false, canExecute)
    }
}