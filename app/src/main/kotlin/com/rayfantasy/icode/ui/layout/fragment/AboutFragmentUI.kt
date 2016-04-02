package com.rayfantasy.icode.ui.layout.fragment

import android.app.Activity
import android.app.Fragment
import android.view.Gravity
import com.benny.library.kbinding.view.ViewBinderComponent
import com.rayfantasy.icode.R
import com.rayfantasy.icode.extension.lparams
import org.jetbrains.anko.*

/**
 * Created by Allen on 2016/4/3.
 */
class AboutFragmentUI() : ViewBinderComponent<Fragment> {
    override fun builder(): AnkoContext<*>.() -> Unit =  {
        verticalLayout {
            imageView {
                setImageResource(R.mipmap.ic_launcher)
                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP

            }.lparams(dip(185),dip(185)){
                topMargin = dip(30)

            }
            textView {
                text = "NULL"
                gravity = Gravity.CENTER
                textSize = sp(16).toFloat()
                //getStringResources(activity,R.string.app_name_cn)
            }.lparams(matchParent, wrapContent)
            textView {
                text = "NULL"
                gravity = Gravity.CENTER
                textSize = sp(12).toFloat()
                setTextAppearance(android.R.attr.textAppearanceMedium)
                //getStringResources(activity,R.string.app_name_cn)
            }.lparams(matchParent, wrapContent){ topMargin = dip(50) }
        }.lparams(matchParent, matchParent)
    }
}