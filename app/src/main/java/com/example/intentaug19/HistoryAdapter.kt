package com.example.intentaug19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.intentaug19.databinding.HistoryViewRowPrototypeBinding

class HistoryAdapter(var list:ArrayList<StudentData>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return "hello"
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, viewgroup: ViewGroup?): View {
//        var myview=LayoutInflater.from(viewgroup!!.context).inflate(R.layout.history_view_row_prototype,viewgroup,false)
//        var binding=HistoryViewRowPrototypeBinding.bind(myview)

        var binding=HistoryViewRowPrototypeBinding.inflate(LayoutInflater.from(viewgroup!!.context))


        var data=list[position]
        binding.resName.text="Name:${data.name}"
        binding.resPhone.text= "Phoneno:${data.phoneno}"
        binding.resMail.text="Gmail:${data.emailid}"
        binding.dob.text="Dob: ${data.dob}"
        binding.resGender.text="Gender:${data.gender}"
        binding.resSkill.text="Skills :${data.skill}"
        return binding.root
//        return myview
    }
}