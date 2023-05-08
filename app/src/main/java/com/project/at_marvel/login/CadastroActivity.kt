package com.project.at_marvel.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.project.at_marvel.R
import com.project.at_marvel.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnCadastrar.setOnClickListener{
            val email = binding.edEmailCadastro.text.toString()
            val senha = binding.edSenhaCadastro.text.toString()
            val ConfirmPass = binding.edConfimacao.text.toString()
            if(email.isNotEmpty() && senha.isNotEmpty() && ConfirmPass.isNotEmpty()){
                if (senha==ConfirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent= Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "senha não coincide", Toast.LENGTH_SHORT).show()}
            }else{
                Toast.makeText(this, "Complete todas as colunas", Toast.LENGTH_SHORT).show()}
        }
    }
}