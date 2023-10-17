package com.example.intentaug19

import MyDb
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.intentaug19.databinding.ActivityHistoryDataPageBinding
import com.example.intentaug19.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import java.util.Calendar

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    lateinit var fname:TextInputLayout
    lateinit var sname:TextInputLayout
    lateinit var lname:TextInputLayout
    lateinit var phoneno:TextInputLayout
    lateinit var mailid:TextInputLayout
    lateinit var rdogroup:RadioGroup
    lateinit var submitbtn:Button
    lateinit var gentertxt:TextView
    lateinit var list: MutableList<String>
    lateinit var calendar: Calendar
    lateinit var binding:ActivityMainBinding
    var date:Int=0
    var month:Int=0
    var year:Int=0

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list= mutableListOf()


//        var toolbar=binding.customToolbar
//        setSupportActionBar(toolbar)
        calendar=Calendar.getInstance()
        date=calendar.get(Calendar.DATE)
        month=calendar.get(Calendar.MONTH)
        year=calendar.get(Calendar.YEAR)





        fname=binding.firstName
        sname=binding.secondName
        lname=binding.lastName
        phoneno=binding.phoneNumber
        mailid=binding.mailId
        rdogroup=binding.radioGroup
        submitbtn=binding.submitBtn
        gentertxt=binding.selectGenterText

        val myDb=MyDb(this)






        binding.android.setOnCheckedChangeListener(this)
        binding.react.setOnCheckedChangeListener(this)
        binding.nodejs.setOnCheckedChangeListener(this)
        binding.python.setOnCheckedChangeListener(this)

        fname.editText!!.addTextChangedListener {
            fname.error=null
        }
        sname.editText!!.addTextChangedListener {
            sname.error=null
        }
        lname.editText!!.addTextChangedListener {
            lname.error=null
        }
        phoneno.editText!!.addTextChangedListener {
            phoneno.error=null
        }
        mailid.editText!!.addTextChangedListener {
            mailid.error=null
        }
        binding.dob.editText!!.addTextChangedListener {
            binding.dob.error=null
        }

        lateinit var stringData:String
        binding.dob.setEndIconOnClickListener {
            var datePickerDialog=DatePickerDialog(this)
                datePickerDialog.setOnDateSetListener { datePicker, i, i2, i3 ->
                    date=i3
                    month=i2
                    year=i
                    binding.dobText.setText("${date}/${month+1}/$year")
                    stringData="${date}/${month+1}/$year"
                }
            datePickerDialog.show()
        }




        var preferance=getSharedPreferences("_login", MODE_PRIVATE)
        var temp=preferance.getString(KeysIntent.userName,"Guest User")
        var logincheck=preferance.getBoolean(KeysIntent.flag,false)
        if(logincheck){
            binding.guestUser.text="Welcome:$temp"
        }
        else{
            binding.guestUser.text="Welcome:Guest User"
        }
        binding.logout.setOnClickListener {
            var editor=preferance.edit()
            editor.putBoolean(KeysIntent.flag,false)
            editor.commit()
            var intent =Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
        }



        submitbtn.setOnClickListener {
            var f_name=fname.editText?.text.toString()
            var s_name=sname.editText?.text.toString()
            var l_name=lname.editText?.text.toString()
            var phone_no=phoneno.editText?.text.toString()
            var mail_id=mailid.editText?.text.toString()
            var selected_id=rdogroup.checkedRadioButtonId
            var cbtn=findViewById<RadioButton>(selected_id)

//            phoneno.editText?.addTextChangedListener {
//                phoneno.error="phone size less than 10"
//            }



            var mailvalid=regexmail(mail_id)
            var phonevalid=regexphoneno(phone_no)

            if(f_name.isEmpty()){
                fname.error="required"
            }
            if(f_name.isNotEmpty()){
                fname.error=null
            }

            if(l_name.isEmpty()){
                lname.error="required"
            }
            if(l_name.isNotEmpty()){
                lname.error=null
            }
            if(phone_no.isEmpty()){
                phoneno.error="required and start with 9876"
            }

            else if(phone_no.length<10){
                phoneno.error="phone size less than 10"
            }
            else if(phonevalid==false){
                phoneno.error="start with 9876"
            }
            else if(phone_no.length==10 && phonevalid==true){
                phoneno.error=null
            }
            if(mail_id.isEmpty()){
                mailid.error="required"
            }
            else if(mailvalid==false){
                mailid.error="Enter valid mail"
            }
            else if(mailvalid==true){
                mailid.error=null
            }
            if(selected_id==-1){
                gentertxt.error=""
                gentertxt.setTextColor(Color.RED)
            }
            if(selected_id!=-1){
                gentertxt.error=null
                gentertxt.setTextColor(Color.BLACK)
            }
            if(list.size<1){
                binding.selectHobbies.error=""
                binding.selectHobbies.text="Select minimam one skills"
                binding.selectHobbies.setTextColor(Color.RED)
            }
            if(list.size>=1){
                binding.selectHobbies.error=null
//                binding.selectHobbies.text="Select minimam one skills"
                binding.selectHobbies.setTextColor(Color.BLACK)
            }
            var tx=regexdob(binding.dob.editText!!.text.toString())
            if( tx ==false){
                binding.dob.error="Envalid DOB"
            }
            if(tx==true){
                binding.dob.error=null
            }


            if(f_name.isNotEmpty() && l_name.isNotEmpty() && phone_no.isNotEmpty() && mail_id.isNotEmpty() && selected_id!=-1 && list.size>=1 &&phone_no.length==10 && mailvalid==true && phonevalid==true && tx==true){
                var intent=Intent(this,MainActivity2::class.java)

//                data send as a throught of intent explicit intent

//                intent.putExtra(KeysIntent.fname,f_name)
//                intent.putExtra(KeysIntent.sname,s_name)
//                intent.putExtra(KeysIntent.lname,l_name)
//                intent.putExtra(KeysIntent.phone,phone_no)
//                intent.putExtra(KeysIntent.mail,mail_id)

                var skilldata=""
                for (i in list){
                    skilldata+="${i} ,"
                }

                myDb.addData(f_name,s_name,l_name,phone_no,mail_id,stringData,cbtn.text.toString(),skilldata)

                var data=DataClass(f_name,l_name,s_name,phone_no,mail_id,cbtn.text.toString(), list,stringData)
                intent.putExtra(KeysIntent.PERSON_DATA,Gson().toJson(data))




//                var bundle=Bundle()
//                bundle.putString(KeysIntent.fname,f_name)
//                bundle.putString(KeysIntent.lname,l_name)
//                intent.putExtra("temp",bundle)

//                imoplicit intent

//                action_view
//                var intent=Intent(Intent.ACTION_VIEW,Uri.parse("https://www.geeksforgeeks.org/kotlin-data-classes/"))


//                action_dial
//                var intent=Intent(Intent.ACTION_DIAL,Uri.parse("tel:7394961470"))
                startActivity(intent)

//                list.clear()



//                action call

//                if(ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1001)
//                }
//                else{
//                    var intent=Intent(Intent.ACTION_CALL,Uri.parse("tel:7394961470"))
//                    startActivity(intent)
//                }


            }

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.history_data->{
                var intent=Intent(this@MainActivity,HistoryDataPage::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1001) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                var intent=Intent(Intent.ACTION_CALL,Uri.parse("tel:7394961470"))
//                startActivity(intent)
//            } else {
//                Toast.makeText(this,"no perrmisssion allowed",Toast.LENGTH_LONG).show()
//            }
//        }
//    }

    override fun onCheckedChanged(view: CompoundButton?, ischecked: Boolean) {
        when(view!!.id) {
            binding.android.id -> {
                if (ischecked) {
                    list.add(binding.android.text.toString())
                }

            }

            binding.react.id -> {
                if (ischecked) {
                    list.add(binding.react.text.toString())
                }

            }

            binding.nodejs.id -> {
                if (ischecked) {
                    list.add(binding.nodejs.text.toString())
                }

            }

            binding.python.id -> {
                if (ischecked) {
                    list.add(binding.python.text.toString())
                }

            }
        }

    }



//    fun isValidEmail(email: String): Boolean {
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
//    }
    fun regexmail(email: String):Boolean{
        var matchregex="^[a-z._0-9]{3,30}@gmail.com$"
        return email.matches(matchregex.toRegex())
    }

    fun regexphoneno(phone:String):Boolean{
        var matchregex="^[9876]{1}[0-9]{9}$"
        return phone.matches(matchregex.toRegex())
    }

    fun regexdob(dob:String):Boolean{
        var matchregex="^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$"
        return dob.matches(matchregex.toRegex())
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Are you sure you want to exit?")
        builder.setPositiveButton("Yes") { _, _ ->
            finish() // Close the activity and exit the app
        }
        builder.setNegativeButton("No", null) // Do nothing if "No" is clicked
        builder.show()
    }

}