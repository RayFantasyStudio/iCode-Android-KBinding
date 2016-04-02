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

package com.rayfantasy.icode.kbinding

import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.View
import com.benny.library.kbinding.bind.OneWay
import com.benny.library.kbinding.bind.TwoWay
import com.benny.library.kbinding.bind.oneWayPropertyBinding
import com.benny.library.kbinding.bind.twoWayPropertyBinding
import com.benny.library.kbinding.converter.EmptyOneWayConverter1
import com.benny.library.kbinding.converter.EmptyTwoWayConverter
import com.benny.library.kbinding.converter.OneWayConverter
import com.benny.library.kbinding.converter.TwoWayConverter
import com.jakewharton.rxbinding.internal.MainThreadSubscription
import com.jakewharton.rxbinding.internal.Preconditions
import rx.Observable
import rx.Subscriber
import rx.functions.Action1

fun DrawerLayout.openedDrawer(vararg paths: String, mode: OneWay = OneWay(), converter: OneWayConverter<*, Int> = EmptyOneWayConverter1())
        = oneWayPropertyBinding(paths, openedDrawer(this), false, converter)

fun DrawerLayout.openedDrawer(path: String, mode: TwoWay, converter: TwoWayConverter<Int, Any?> = EmptyTwoWayConverter())
        = twoWayPropertyBinding(path, openedDrawerChanges(), openedDrawer(this), converter)

fun DrawerLayout.openedDrawerChanges() = Observable.create(DrawerLayoutOpenedDrawerOnSubscribe(this))

class DrawerLayoutOpenedDrawerOnSubscribe(val drawer: DrawerLayout) : Observable.OnSubscribe<Int> {
    override fun call(subscriber: Subscriber<in Int>?) {
        Preconditions.checkUiThread()

        val drawerListener = object : DrawerLayout.DrawerListener {
            override fun onDrawerClosed(drawerView: View) {
                subscriber?.onNext(Gravity.NO_GRAVITY)
            }

            override fun onDrawerOpened(drawerView: View) {
                val lparams = drawerView.layoutParams as DrawerLayout.LayoutParams
                subscriber?.onNext(lparams.gravity)
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

            override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
            }
        }

        drawer.addDrawerListener(drawerListener)

        subscriber?.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                drawer.removeDrawerListener(drawerListener)
            }
        })
    }
}

fun openedDrawer(drawer: DrawerLayout) = Action1<Int> {
    if (it == Gravity.NO_GRAVITY)
        drawer.closeDrawers()
    else
        drawer.openDrawer(it)
}