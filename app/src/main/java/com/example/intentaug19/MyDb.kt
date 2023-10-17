import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.intentaug19.StudentData

class MyDb(var context: Context) {


        private  val DB_NAME = "details"
        private  val DB_VERSION = 2
        private  val TABLE_NAME = "student_details"
        private  val ID = "_id"
        private  val FNAME = "fname"
        private  val MNAME = "mname"
        private  val LNAME = "lname"
        private  val PHONENO = "phone_no"
        private  val EMAIL = "email_id"
        private  val GENDER = "gender"
        private  val SKILL = "skill"
        private  val DOB = "dob"


    private val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $FNAME TEXT, $MNAME TEXT, $LNAME TEXT, $PHONENO TEXT, $EMAIL TEXT, $DOB TEXT, $GENDER TEXT, $SKILL TEXT)"

     var myopenhelper=MyOpenHelper(context)
    var sqlitedatabase=myopenhelper.writableDatabase

    inner class MyOpenHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(sqldb: SQLiteDatabase?) {
            sqldb?.execSQL(CREATE_TABLE)
        }

        override fun onUpgrade(sqldb: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            // Handle database upgrades here if needed
             sqldb?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
             onCreate(sqldb)
        }
    }

    fun addData(
        fname: String,
        mname: String,
        lname: String,
        phoneno: String,
        emailid: String,
        dob: String,
        gender: String,
        skill: String
    ) {
            val values = ContentValues()
            values.put(FNAME, fname)
            values.put(MNAME, mname)
            values.put(LNAME, lname)
            values.put(PHONENO, phoneno)
            values.put(EMAIL, emailid)
            values.put(DOB, dob)
            values.put(GENDER, gender)
            values.put(SKILL, skill)
            sqlitedatabase.insert(TABLE_NAME, null, values)

    }

    fun getData(): ArrayList<StudentData> {
        val list = ArrayList<StudentData>()
        val cursor= sqlitedatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor.moveToFirst()){
                do {
                    val id = cursor.getInt(0)
                    val fName = cursor.getString(1)
                    val mName = cursor.getString(2)
                    val lName = cursor.getString(3) 
                    val phoneNo = cursor.getString(4)
                    val email = cursor.getString(5)
                    val dob = cursor.getString(6)
                    val gender = cursor.getString(7)
                    val skill = cursor.getString(8)

                    list.add(StudentData(id, "$fName $mName $lName", phoneNo, email, dob, gender, skill))
                } while (cursor.moveToNext())
            }

        return list
    }

}
