package com.example.konnekt.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.konnekt.R
import com.example.konnekt.databinding.ConversationBubbleBinding
import com.example.konnekt.model.Message

class ConversationAdapter(val username: String): ListAdapter<Message, ConversationAdapter.ConversationViewHolder>(diffCallback) {
    companion object diffCallback: DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.displayName == newItem.displayName
        }

    }
    inner class ConversationViewHolder(private val binding: ConversationBubbleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {

            Log.w("OOOOOO", (username ?: "User null"))
            if(message.sender.username == username) {
                binding.itemView.gravity = 5
            }
            binding.message = message
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val binding = ConversationBubbleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConversationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)


    }
}