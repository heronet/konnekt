package com.example.konnekt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.example.konnekt.R
import com.example.konnekt.databinding.InboxItemBinding
import com.example.konnekt.model.Message

class InboxAdapter(val navController: NavController): ListAdapter<Message, InboxAdapter.InboxViewHolder>(diffCallback) {
    companion object diffCallback: DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.displayName == newItem.displayName
        }

    }
    inner class InboxViewHolder(private val binding: InboxItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.message = message
            binding.executePendingBindings()
            binding.itemView.setOnClickListener {
                navController.navigate(R.id.chatFragment, bundleOf("displayName" to message.displayName))
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        val binding = InboxItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InboxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        val message = getItem(position)
        holder.bind(message)


    }
}