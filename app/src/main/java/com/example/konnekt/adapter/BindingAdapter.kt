package com.example.konnekt.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.konnekt.model.Message
import com.example.konnekt.model.Status

@BindingAdapter("inbox")
fun inboxItemsBinder(recyclerView: RecyclerView, messages: List<Message>?) {
    val adapter = recyclerView.adapter as InboxAdapter
    adapter.submitList(messages)
}
@BindingAdapter("conversation")
fun conversationItemsBinder(recyclerView: RecyclerView, messages: List<Message>?) {
    val adapter = recyclerView.adapter as ConversationAdapter
    adapter.submitList(messages)
}
@BindingAdapter("apiStatus")
fun bindStatus(loadingText: TextView,
               status: Status?) {
    when(status) {
        Status.LOADING -> {
            loadingText.visibility = View.VISIBLE
            loadingText.text = "Loading..."
        }
        Status.ERROR -> {
            loadingText.visibility = View.VISIBLE
            loadingText.text = "ERROR"
        }
        Status.SUCCESS -> {
            loadingText.visibility = View.GONE
        }
    }

}