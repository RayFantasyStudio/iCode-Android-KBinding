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

import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.benny.library.kbinding.bind.PropertyBinding
import com.benny.library.kbinding.bind.commandBinding
import com.jakewharton.rxbinding.internal.MainThreadSubscription
import com.jakewharton.rxbinding.internal.Preconditions
import rx.Observable
import rx.Subscriber
import rx.functions.Action1

fun NavigationView.itemSelected(path: String): PropertyBinding = commandBinding(path, itemSelected(), Action1 {})

fun NavigationView.itemSelected(): Observable<MenuItem> {
    return Observable.create(NavigationViewItemSelectedOnSubscribe(this))
}

class NavigationViewItemSelectedOnSubscribe(val view: NavigationView) : Observable.OnSubscribe<MenuItem> {
    override fun call(subscriber: Subscriber<in MenuItem>) {
        Preconditions.checkUiThread()
        val listener = NavigationView.OnNavigationItemSelectedListener {
            if (!subscriber.isUnsubscribed) {
                subscriber.onNext(it)
            }
            true
        }

        view.setNavigationItemSelectedListener(listener)
        subscriber.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                view.setNavigationItemSelectedListener(null)
            }
        })
    }
}