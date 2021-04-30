package com.example.konnekt.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.konnekt.R
import com.example.konnekt.adapter.ConversationAdapter
import com.example.konnekt.databinding.FragmentChatBinding
import com.example.konnekt.model.AuthViewModel
import com.example.konnekt.model.MessageViewModel

class ChatFragment : Fragment() {
    private val messageViewModel: MessageViewModel by viewModels()

    private var binding: FragmentChatBinding? = null
    private lateinit var currentUser: String
    private lateinit var username: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        username = arguments?.getString("displayName").toString()
        binding = FragmentChatBinding.inflate(inflater, container, false)

        val pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        currentUser = pref.all[getString(R.string.username)].toString()
        val token = pref.all[getString(R.string.jwt_token)]

        if(token == null) {
            findNavController().navigate(R.id.loginFragment)
        } else {
            messageViewModel.getConversation(token as String, username)
        }
        binding!!.lifecycleOwner = this
        binding!!.viewModel = messageViewModel
        binding!!.rvConversation.adapter = ConversationAdapter(currentUser)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}