package com.rayfantasy.icode.ui.layout.fragment

import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.extension.generateViewId
import com.rayfantasy.icode.extension.lparams
import com.rayfantasy.icode.ui.fragment.FavoriteFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by Allen on 2016/4/3.
 */
class FavoriteFragmentUI : ViewBinderComponent<FavoriteFragment> {
    companion object {
        val ID_SWIPE = generateViewId()
        val ID_RECYCLER = generateViewId()
    }

    override fun builder(): AnkoContext<*>.() -> Unit = {
        swipeRefreshLayout {
            id = ID_SWIPE
            recyclerView {
                id = ID_RECYCLER
            }.lparams(matchParent, matchParent)
        }.lparams(matchParent, matchParent)
    }
}