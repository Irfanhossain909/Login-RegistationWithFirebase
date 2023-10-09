package com.rootcode.loginregfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rootcode.loginregfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.register.setOnClickListener {
            if (binding.emailEtxt.text.toString()==""||binding.passEtxt.text.toString()==""){
                Toast.makeText(this@MainActivity,"Feeling the gaps",Toast.LENGTH_SHORT).show()
            }
            else{
                Firebase.auth.createUserWithEmailAndPassword(binding.emailEtxt.text.toString(),binding.passEtxt.text.toString()).
                        addOnCompleteListener {
                            if (it.isSuccessful){
                                binding.emailEtxt.text?.clear()
                                binding.passEtxt.text?.clear()
                                Toast.makeText(this@MainActivity,"User Registation!!!...",Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this@MainActivity,it.exception?.localizedMessage,Toast.LENGTH_SHORT).show() 
                            }
                        }
            }
        }
    }
}