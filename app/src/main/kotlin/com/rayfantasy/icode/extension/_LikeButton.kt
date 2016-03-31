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

package com.rayfantasy.icode.extension

import com.like.LikeButton
import com.like.OnLikeListener

/**
 * Created by qweas on 2016/2/15 0015.
 */
class _OnLikeListener() : OnLikeListener {
    private var _liked: ((LikeButton) -> Unit)? = null
    private var _unLiked: ((LikeButton) -> Unit)? = null

    override fun liked(p0: LikeButton) {
        _liked?.invoke(p0)
    }

    override fun unLiked(p0: LikeButton) {
        _unLiked?.invoke(p0)
    }

    fun liked(listener: (LikeButton) -> Unit) {
        _liked = listener
    }

    fun unLiked(listener: (LikeButton) -> Unit) {
        _unLiked = listener
    }
}

fun LikeButton.onLike(init: _OnLikeListener.() -> Unit) {
    setOnLikeListener(_OnLikeListener().apply(init))
}