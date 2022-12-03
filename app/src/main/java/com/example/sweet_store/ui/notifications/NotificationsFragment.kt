package com.example.sweet_store.ui.notifications

import android.app.ActionBar
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweet_store.AboutUsActivity
import com.example.sweet_store.LoginActivity
import com.example.sweet_store.WebViewHelpActivity
import com.example.sweet_store.databinding.FragmentNotificationsBinding
import com.example.sweet_store.payments.PaymentMethod


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private var actionBar: ActionBar? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        val prefs = context?.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val name: String = prefs?.getString("userName", null) ?: ""
        val email: String = prefs?.getString("userEmail", null) ?: ""
        var uuid = prefs?.getString("userId", "") ?: ""

        print(name)
        _binding!!.nameUser.text = name
        _binding!!.emailUser.text = email
        print(name)
        val root: View = binding.root

        val btPayment = _binding!!.btPayment
        btPayment.setOnClickListener {
            val intent = Intent(activity, PaymentMethod::class.java)
            startActivity(intent)
        }

        val btAbout = _binding!!.btAbout
        btAbout.setOnClickListener {
            val intent = Intent(activity, AboutUsActivity::class.java)
            startActivity(intent)
        }

        val btLogout = _binding!!.btLogout
        btLogout.setOnClickListener {
            //      val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",context.MODE_PRIVATE)
            //    var editor = sharedPreference.edit()
//                editor.remove("username")
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        val btHelp = _binding!!.btHelp
        btHelp.setOnClickListener {
            val intent = Intent(activity, WebViewHelpActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNotificationsBinding.inflate(layoutInflater)

        val prefs = context?.getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val name: String = prefs?.getString("userName", null) ?: ""
        val email: String = prefs?.getString("userEmail", null) ?: ""
        var uuid = prefs?.getString("userId", "") ?: ""

        print(name)
        _binding!!.nameUser.text = name
        _binding!!.emailUser.text = email
    }


    //    private fun getUserIdFromSharedPrefs(): String? {
//        val sharedPref = (AppCompatActivity.MODE_PRIVATE)
//        val defaultValue = ""
//        return sharedPref.getString("userId", defaultValue)
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}