package com.example.konnekt.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.konnekt.R
import com.example.konnekt.adapter.ConversationAdapter
import com.example.konnekt.adapter.InboxAdapter
import com.example.konnekt.databinding.FragmentInboxBinding
import com.example.konnekt.model.MessageViewModel


class InboxFragment : Fragment() {
    private var binding: FragmentInboxBinding? = null
    private val messageViewModel: MessageViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentInboxBinding.inflate(inflater, container, false)
        binding!!.lifecycleOwner = this
        binding!!.viewModel = messageViewModel
        binding!!.chatList.adapter = InboxAdapter(findNavController())

        val token = requireActivity().getPreferences(Context.MODE_PRIVATE).all[getString(R.string.jwt_token)]
        if(token == null) {
            findNavController().navigate(R.id.loginFragment)
        } else {
            messageViewModel.getInbox(token.toString())
        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}