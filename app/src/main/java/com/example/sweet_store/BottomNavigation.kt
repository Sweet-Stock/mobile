package com.example.sweet_store

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sweet_store.confectionery.Confectionery
import com.example.sweet_store.databinding.ActivityBottomNavigationBinding

class BottomNavigation : AppCompatActivity() {
    private lateinit var binding : ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(HomeFragment())
                R.id.order -> replaceFragment(OrderFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.confectionery ->    startActivity(Intent(this, Confectionery::class.java))

                else ->{



                }

            }

            true

        }


    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }
}