package com.example.intentaug19

object temp{
    lateinit var a:String
    lateinit var b:String

}

data class tt(var a:String,var b:String)
//


fun main(){

    var obj1=tt("ram","mohan")

    var obj2=tt("sjsjj","wkwkskkw")

    var t1=temp
    t1.a="owwwwwwwwwwww"
    var t2=temp
    t2.a="wkkkkkkkkkkkkkkkkkkkkkk"

    print(t1.a)
    print(t2.a)




}


fun regexmail(email: String):Boolean{
    var matchregex="^[a-z._0-9]{3,30}@gmail.com$"
    return email.matches(matchregex.toRegex())
}

fun regexphoneno(phone:String):Boolean{
    var matchregex="^9876[0-9]{6}$"
    return phone.matches(matchregex.toRegex())
}