package com.rayfantasy.icode.model

import com.benny.library.kbinding.adapterview.viewmodel.ItemViewModel
import com.benny.library.kbinding.annotation.ExtractProperty
import com.rayfantasy.icode.postutil.bean.Reply
import kotlin.properties.Delegates

class ReplyViewModel : ItemViewModel<Reply>(){
    @delegate:ExtractProperty("content","username","goodId","createat","id",hasPrefix = false)
    var reply by Delegates.property<Reply>()
    
    override fun onDataChange(data: Reply?) {
        reply = data
    }
}