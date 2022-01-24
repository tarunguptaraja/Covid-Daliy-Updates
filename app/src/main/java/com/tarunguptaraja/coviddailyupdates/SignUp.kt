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

class SignUp : AppCompatActivity() {

    private lateinit var mauth: FirebaseAuth
    private lateinit var mUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mauth = Firebase.auth
        setContentView(R.layout.activity_sign_up)
    }


    fun already(v: View){
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun signUp(v: View){
        performAuth()
    }
    fun performAuth(){
        val inputEmail = findViewById<EditText>(R.id.email2)
        val inputPassword = findViewById<EditText>(R.id.password2)
        val inputConfirm = findViewById<EditText>(R.id.confirm)

        val email=inputEmail.getText().toString()
        val password = inputPassword.getText().toString()
        val confirm = inputConfirm.getText().toString()

        if(email.isEmpty()){
            inputEmail.setError("E-mail is empty")
        }

        else if(password.length<6){
            inputPassword.setError("Password less then 6 characters")
        }

        else if(!password.equals(confirm) ){
            inputConfirm.setError("Password is not matched")
        }
        else{
            mauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    mUser = mauth.currentUser!!
                    updateUI(mUser)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }



    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this,HomeActivity::class.java))

    }

}