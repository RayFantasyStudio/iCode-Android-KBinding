package com.rayfantasy.icode.ui.activity

import android.os.Bundle
import com.benny.library.kbinding.bind.BindingDelegate
import com.benny.library.kbinding.view.BindingDisposerGenerator
import com.benny.library.kbinding.view.setContentView
import com.rayfantasy.icode.ui.layout.activity.LoginActivityUI

class LoginActivity : ActivityBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginActivityUI().setContentView(this).bindTo(this)
    }

}