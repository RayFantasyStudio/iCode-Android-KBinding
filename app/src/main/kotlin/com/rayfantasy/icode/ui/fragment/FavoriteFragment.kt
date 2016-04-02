package com.rayfantasy.icode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rayfantasy.icode.ui.layout.fragment.FavoriteFragmentUI
import org.jetbrains.anko.UI

/**
 * Created by Allen on 2016/4/3.
 */
class FavoriteFragment : FragmentBase(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = UI {
        FavoriteFragmentUI().createView(this)
    }.view
}