package com.example.sweet_store.ui.notifications

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweet_store.AboutUsActivity
import com.example.sweet_store.LoginActivity
import com.example.sweet_store.SingUpActivity
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
        val root: View = binding.root

        _binding!!.btPayment.setOnClickListener{
            val btPayment = _binding!!.btPayment
            btPayment.setOnClickListener {
                val intent = Intent(activity,PaymentMethod::class.java)
                startActivity(intent)
            }
        }
        _binding!!.btAbout.setOnClickListener {
            val btAbout = _binding!!.btAbout
            btAbout.setOnClickListener {
                val intent = Intent(activity, AboutUsActivity::class.java)
                startActivity(intent)
            }
        }
        _binding!!.btLogout.setOnClickListener{
            val btLogout = _binding!!.btLogout
            btLogout.setOnClickListener {
                val intent = Intent(activity,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        _binding!!.btHelp.setOnClickListener{
            val btHelp = _binding!!.btHelp
            btHelp.setOnClickListener {
                val intent = Intent(activity,WebViewHelpActivity::class.java)
                startActivity(intent)
            }
        }
        return root
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