package com.example.konnekt.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.konnekt.R
import com.example.konnekt.databinding.FragmentLoginBinding
import com.example.konnekt.model.AuthViewModel


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            buttonLogin.setOnClickListener {
                authViewModel.onLogin(tvEmail.text.toString(), tvPassword.text.toString())
            }
        }
        authViewModel.loginSuccess.observe(viewLifecycleOwner, Observer {success ->
            if(success == true) {
                val shaerdPref: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
                with(shaerdPref.edit()) {
                    putString(getString(R.string.jwt_token), authViewModel.loginResponse.value?.token)
                    putString(getString(R.string.username), authViewModel.loginResponse.value?.username)
                    putString(getString(R.string.userID), authViewModel.loginResponse.value?.userId)
                    apply()
                }
                findNavController().navigateUp()
            }
            else
                Toast.makeText(context, authViewModel.errorMessage, Toast.LENGTH_SHORT).show()
        })

    }

}