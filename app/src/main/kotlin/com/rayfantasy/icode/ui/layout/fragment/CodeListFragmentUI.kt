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

package com.rayfantasy.icode.ui.layout.fragment

import android.support.v7.widget.LinearLayoutManager
import com.benny.library.kbinding.adapterview.bindings.adapter
import com.benny.library.kbinding.adapterview.bindings.itemClick
import com.benny.library.kbinding.adapterview.bindings.paging
import com.benny.library.kbinding.adapterview.converter.ListToRecyclerPagingAdapterConverter
import com.benny.library.kbinding.adapterview.viewcreator.pagingViewCreator
import com.benny.library.kbinding.dsl.bind
import com.benny.library.kbinding.support.v4.bindings.refresh
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.model.CodeGoodViewModel
import com.rayfantasy.icode.ui.fragment.CodeListFragment
import com.rayfantasy.icode.ui.layout.item.CodeItemItem
import com.rayfantasy.icode.ui.layout.item.LoadingItemView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class CodeListFragmentUI : ViewBinderComponent<CodeListFragment> {
    override fun builder(): AnkoContext<*>.() -> Unit = {
        val owner = owner as CodeListFragment
        swipeRefreshLayout {
            bind { refresh("reloadCodeGoods") }
            recyclerView {
                layoutManager = LinearLayoutManager(ctx)
                bind { paging("loadMoreCodeGood") }
                bind {
                    adapter("codeGoods", converter = ListToRecyclerPagingAdapterConverter(owner.
                            pagingViewCreator(LoadingItemView(), CodeItemItem(), ::CodeGoodViewModel)))
                }
                bind { itemClick("codeGoodDetail") }
            }.lparams(matchParent, matchParent)
        }.lparams(matchParent, matchParent)
    }
}