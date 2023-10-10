package com.rootcode.loginregfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rootcode.loginregfirebase.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mGoogleApiClint:GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.exchenge.setOnClickListener {
            startActivity(Intent(this@Login,MainActivity::class.java))
            finish()
        }

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleApiClint = GoogleApiClient.Builder(this)
            .enableAutoManage(this){
                connectionResult->
                Toast.makeText(this@Login,"Google Service Error",Toast.LENGTH_SHORT).show()
            }
            .addApi(Auth.GOOGLE_SIGN_IN_API)
            .build()

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEtxt.text.toString()
            val password = binding.passEtxt.text.toString()
            signWithEmailAndPassword(email,password)
        }
    }

    private fun signWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                taxk->
                if (taxk.isSuccessful){
                    startActivity(Intent(this@Login,HomeActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this@Login,"Something Wrong",Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser!=null){
            startActivity(Intent(this@Login,HomeActivity::class.java))
            finish()
        }
    }
}