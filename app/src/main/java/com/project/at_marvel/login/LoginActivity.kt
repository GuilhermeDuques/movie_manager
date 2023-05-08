package com.project.at_marvel.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.project.at_marvel.MainActivity
import com.project.at_marvel.R
import com.project.at_marvel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogar.setOnClickListener {
            val email = binding.edEmailLogin.text.toString()
            val senha = binding.edSenhaLogin.text.toString()

            if(email.isNotEmpty() && senha.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent= Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }


            }else{
                Toast.makeText(this, "Complete todas as colunas", Toast.LENGTH_SHORT).show()}
        }
    }

}