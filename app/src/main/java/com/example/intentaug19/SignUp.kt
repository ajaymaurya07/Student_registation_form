package com.example.intentaug19

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.intentaug19.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity(), View.OnClickListener {
    lateinit var binding:ActivitySignUpBinding
    lateinit var preferance:SharedPreferences
    lateinit var editor:Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferance=getSharedPreferences("_login", MODE_PRIVATE)

        editor=preferance.edit()
        binding.signupBtn.setOnClickListener(this)
        binding.loginText.setOnClickListener {
            var intent=Intent(this@SignUp,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.loginSkip.setOnClickListener {
            var intent= Intent(this@SignUp,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.regsName.editText?.addTextChangedListener {
            binding.regsName.error=null
        }
        binding.regsMail.editText?.addTextChangedListener {
            binding.regsMail.error=null
        }
        binding.regsPassword.editText?.addTextChangedListener {
            binding.regsPassword.error=null
        }
        binding.regsConfirmPassword.editText?.addTextChangedListener {
            binding.regsConfirmPassword.error=null
        }
    }

    override fun onClick(p0: View?) {
        editor.putString(KeysIntent.signup_name,binding.regsName.editText?.text.toString())
        editor.putString(KeysIntent.signup_mail,binding.regsMail.editText?.text.toString())
        editor.putString(KeysIntent.signup_password,binding.regsPassword.editText?.text.toString())
        editor.putString(KeysIntent.signup_confirm_password,binding.regsConfirmPassword.editText?.text.toString())

        if(binding.regsName.editText?.text.toString().isEmpty()){
            binding.regsName.error="Mendatory feild"
        }
        if(binding.regsMail.editText?.text.toString().isEmpty()){
            binding.regsMail.error="Mendatory feild"
        }
        if(binding.regsPassword.editText?.text.toString().isEmpty()){
            binding.regsPassword.error="Mendatory feild"
        }
        if(binding.regsConfirmPassword.editText?.text.toString().isEmpty()){
            binding.regsConfirmPassword.error="Mendatory feild"
        }
        if(binding.regsPassword.editText?.text.toString()!=binding.regsConfirmPassword.editText?.text.toString()){
            binding.regsConfirmPassword.error="password missmatch"
        }
        if(regexmail(binding.regsMail.editText?.text.toString())==false){
            binding.regsMail.error="Enter valid mail"
        }

        if(binding.regsPassword.editText?.text.toString()==binding.regsConfirmPassword.editText?.text.toString() &&
            binding.regsName.editText?.text.toString()!=null &&
            binding.regsMail.editText?.text.toString()!=null &&
            binding.regsPassword.editText?.text.toString()!=null &&
            binding.regsConfirmPassword.editText?.text.toString()!=null &&
            regexmail(binding.regsMail.editText?.text.toString())==true
        ){
            editor.putBoolean(KeysIntent.checkflagsignUp,true)
            editor.putString(KeysIntent.userName,binding.regsName.editText?.text.toString())
            editor.commit()
            var intent=Intent(this@SignUp,LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"successsfully signup",Toast.LENGTH_SHORT).show()
            finish()
        }

//        finish()


    }

    fun regexmail(email: String):Boolean{
        var matchregex="^[a-z._0-9]{3,30}@gmail.com$"
        return email.matches(matchregex.toRegex())
    }

}