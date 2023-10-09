package com.rootcode.loginregfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rootcode.loginregfirebase.databinding.ActivityHomeBinding
import com.rootcode.loginregfirebase.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.logBtn.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this@HomeActivity,MainActivity::class.java))
            finish()
        }
    }
}