package com.tarunguptaraja.coviddailyupdates

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var mauth: FirebaseAuth
    lateinit var mUser:FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mauth = Firebase.auth
        setContentView(R.layout.activity_main)
    }

    fun create(v: View){
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

    fun login(v: View){
        performAuth()
    }

    private fun performAuth() {
        val inputEmail = findViewById<EditText>(R.id.email)
        val inputPassword = findViewById<EditText>(R.id.password)

        val email=inputEmail.getText().toString()
        val password = inputPassword.getText().toString()

        if(email.isEmpty()){
            inputEmail.setError("E-mail is empty")
        }

        else if(password.length<6){
            inputPassword.setError("Password less then 6 characters")
        }
        else{
            mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){ task->
                if(task.isSuccessful){
                    mUser= mauth.currentUser!!
                    updateUI(mUser)
                }else{
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }


            }
        }
    }

    private fun updateUI(mUser: FirebaseUser) {
        startActivity(Intent(this, HomeActivity::class.java))
    }


}