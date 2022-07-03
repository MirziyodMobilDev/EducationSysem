package db

import Kusrsconstant.Constant
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import modul.*

class CourseDatabaseHelper(context: Context) : SQLiteOpenHelper(context,
    Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION), CoursDataBaseSerrvis {
    override fun onCreate(p0: SQLiteDatabase?) {


        var courseQueryTable = "create table ${Constant.COURSE_TABLE} " +
                "(${Constant.COURSE_ID} integer not null primary key autoincrement unique," +
                "${Constant.COURSE_NAME} text not null," +
                "${Constant.ABOUT_COURSE} text not null)"
        var mentorQueryTable = "create table ${Constant.MENTORS_TABLE} " +
                "(${Constant.MENTOR_ID} integer not null primary key autoincrement unique," +
                "${Constant.MENTOR_NAME} text not null," +
                "${Constant.MENTOR_SURNAME} text not null," +
                "${Constant.MENTOR_MIDDLENAME} text not null,"+
                "${Constant.MENTOR_COURSE_NAME} text not null)"
        var guruhQueryTable = "create table ${Constant.Guruh_TABLE} " +
                "(${Constant.GURUH_ID} integer not null primary key autoincrement unique," +
                "${Constant.Guruh_NAME} text not null," +
                "${Constant.CHOIS_MENTOR} text not null," +
                "${Constant.TIMES} text not null," +
                "${Constant.DAYS} text not null," +
                "${Constant.THRUTHFALSE} text not null," +
                "${Constant.COURSE_GURUH} text not null)"
        var newStudentQueryTable = "create table ${Constant.NEW_STUDENT_TABLE} " +

                "(${Constant.NEW_STUDENT_ID} integer not null primary key autoincrement unique," +
                "${Constant.NEW_STUDENT_NAME} text not null," +
                "${Constant.NEW_STUDENT_LASTNAME} text not null," +
                "${Constant.NEW_STUDENT_FATHER} text not null," +
                "${Constant.NEW_DAY} text not null,"+
                "${Constant.GRUHNAME_NEW_STUDENT} text not null)"
        p0?.execSQL(courseQueryTable)
        p0?.execSQL(mentorQueryTable)
        p0?.execSQL(guruhQueryTable)
        p0?.execSQL(newStudentQueryTable)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }



    override  fun InsertCourse(course: Course) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.COURSE_NAME, course.courseName)
        contentValues.put(Constant.ABOUT_COURSE, course.coursText)
        database.insert(Constant.COURSE_TABLE, null, contentValues)
        database.close()
    }





    @SuppressLint("Recycle")
    override fun getAllCourse(): ArrayList<Course> {
        val list = ArrayList<Course>()
        val query = "select * from ${Constant.COURSE_TABLE}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val course = Course()
                course.coursId = cursor.getInt(0)
                course.courseName = cursor.getString(1)
                course.coursText = cursor.getString(2)
                list.add(course)

            } while (cursor.moveToNext())
        }
        return list
    }


    override fun deleteCourse(course: Course) {
        TODO("Not yet implemented")

    }


    override fun updateCourse(course: Course): Int {
        TODO("Not yet implemented")
    }



    @SuppressLint("Recycle")
    override fun getByIdCourse(id: Int): Course {
        val database = this.readableDatabase
        val cursor = database.query(Constant.COURSE_TABLE,
            arrayOf(Constant.COURSE_ID, Constant.COURSE_NAME, Constant.ABOUT_COURSE),
            "${Constant.COURSE_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null)
        cursor?.moveToFirst()
        val course = Course()
        course.coursId = cursor.getInt(0)
        course.courseName = cursor.getString(1)
        course.coursText = cursor.getString(2)

        return course
    }
    override fun InsertMentor(mentors: Mentor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.MENTOR_SURNAME,mentors.mentorLastname)
        contentValues.put(Constant.MENTOR_NAME, mentors.mentorName)
        contentValues.put(Constant.MENTOR_MIDDLENAME, mentors.mentorsFather)
        contentValues.put(Constant.MENTOR_COURSE_NAME,mentors.courseName)
        database.insert(Constant.MENTORS_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllMentor(): ArrayList<Mentor> {
        val list = ArrayList<Mentor>()
        val query = "select * from ${Constant.MENTORS_TABLE}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                var mentors = Mentor()
                mentors.mentorId= cursor.getInt(0)
                mentors.mentorLastname = cursor.getString(1)
                mentors.mentorName = cursor.getString(2)
                mentors.mentorsFather = cursor.getString(3)
                mentors.courseName = cursor.getString(4)
                list.add(mentors)

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun deleteMentor(mentors: Mentor) {
        var database = this.writableDatabase
        database.delete(Constant.MENTORS_TABLE,"${Constant.MENTOR_ID} = ?", arrayOf("${mentors.mentorId}"))
        database.close()

    }

    override fun updateMentor(mentors: Mentor): Int {
        var database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(Constant.MENTOR_ID, mentors.mentorId)
        contentValues.put(Constant.MENTOR_NAME, mentors.mentorName)
        contentValues.put(Constant.MENTOR_SURNAME, mentors.mentorLastname)
        contentValues.put(Constant.MENTOR_MIDDLENAME, mentors.mentorsFather)
        contentValues.put(Constant.MENTOR_COURSE_NAME,mentors.courseName)

        return database.update(Constant.MENTORS_TABLE, contentValues, "${Constant.MENTOR_ID} = ?", arrayOf(mentors.mentorId.toString()))
    }

    override fun getBtIdMentor(id: Int): Mentor {
        var database = this.readableDatabase
        var cursor = database.query(Constant.MENTORS_TABLE, arrayOf(Constant.MENTOR_ID, Constant.MENTOR_SURNAME, Constant.MENTOR_NAME, Constant.MENTOR_MIDDLENAME,Constant.MENTOR_COURSE_NAME),
            "${Constant.MENTOR_ID} = ?", arrayOf("$id"),null,null,null)
        cursor?.moveToFirst()
        return Mentor(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4))
    }
    override fun Insertguruh(gruhList: GruhList) {
        var database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(Constant.GURUH_ID,gruhList.guruhId)
        contentValues.put(Constant.Guruh_NAME,gruhList.gruhName)
        contentValues.put(Constant.CHOIS_MENTOR,gruhList.chois_mentor)
        contentValues.put(Constant.TIMES,gruhList.time)
        contentValues.put(Constant.DAYS,gruhList.day)
        contentValues.put(Constant.THRUTHFALSE,gruhList.truthFalse)
        contentValues.put(Constant.COURSE_GURUH,gruhList.course)
        database.insert(Constant.Guruh_TABLE,null,contentValues)
        database.close()
    }

    override fun getAllGuruh(): ArrayList<GruhList> {
        val list = ArrayList<GruhList>()
        val query = "select * from ${Constant.Guruh_TABLE}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val gruhList = GruhList()
                gruhList.guruhId = cursor.getInt(0)
                gruhList.gruhName = cursor.getString(1)
                gruhList.chois_mentor = cursor.getString(2)
                gruhList.time = cursor.getString(3)
                gruhList.day = cursor.getString(4)
                gruhList.truthFalse = cursor.getString(5)
                gruhList.course = cursor.getString(6)
                list.add(gruhList)

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun update(gruhList: GruhList): Int {
        var database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(Constant.GURUH_ID, gruhList.guruhId)
        contentValues.put(Constant.Guruh_NAME, gruhList.gruhName)
        contentValues.put(Constant.CHOIS_MENTOR, gruhList.chois_mentor)
        contentValues.put(Constant.TIMES, gruhList.time)
        contentValues.put(Constant.DAYS, gruhList.day)
        contentValues.put(Constant.THRUTHFALSE,gruhList.truthFalse)
        contentValues.put(Constant.COURSE_GURUH, gruhList.course)
        return database.update(Constant.Guruh_TABLE, contentValues, "${Constant.GURUH_ID} = ?", arrayOf(gruhList.guruhId.toString()))


    }

    override fun getGuruhById(id: Int): GruhList {
        var database = this.readableDatabase
        var cursor = database.query(Constant.Guruh_TABLE, arrayOf(Constant.GURUH_ID,Constant.Guruh_NAME,Constant.CHOIS_MENTOR,Constant.TIMES,Constant.DAYS,Constant.THRUTHFALSE,Constant.COURSE_GURUH),
            "${Constant.GURUH_ID} = ?", arrayOf("$id"),null,null,null)
        cursor?.moveToFirst()
        return GruhList(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6))

    }

    override fun newGuruhDelete(gruhList: GruhList) {
        var database = this.writableDatabase
        database.delete(Constant.Guruh_TABLE,"${Constant.GURUH_ID} = ?", arrayOf("${gruhList.guruhId}"))
        database.close()
    }
    override fun InsertNewStudent(newStudentAdd: NewStudentAdd) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constant.NEW_STUDENT_NAME,newStudentAdd.new_add_ism)
        contentValues.put(Constant.NEW_STUDENT_LASTNAME,newStudentAdd.new_add_familya)
        contentValues.put(Constant.NEW_STUDENT_FATHER,newStudentAdd.new_add_otasi)
        contentValues.put(Constant.NEW_DAY,newStudentAdd.new_add_date)
        contentValues.put(Constant.GRUHNAME_NEW_STUDENT,newStudentAdd.intent_groupname)
        database.insert(Constant.NEW_STUDENT_TABLE, null, contentValues)
        database.close()
    }

    override fun getAllNewStudent(): ArrayList<NewStudentAdd> {
        val list = ArrayList<NewStudentAdd>()
        val query = "select * from ${Constant.NEW_STUDENT_TABLE}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                var newStudentAdd = NewStudentAdd()
                newStudentAdd.new_add_id = cursor.getInt(0)
                newStudentAdd.new_add_familya = cursor.getString(1)
                newStudentAdd.new_add_ism= cursor.getString(2)
                newStudentAdd.new_add_otasi =cursor.getString(3)
                newStudentAdd.new_add_date=cursor.getString(4)
                newStudentAdd.intent_groupname=cursor.getString(5)
                list.add(newStudentAdd)

            } while (cursor.moveToNext())
        }
        return list
    }

    override fun updateNewStudent(newStudentAdd: NewStudentAdd): Int {
        var database = this.writableDatabase
        var contentValues = ContentValues()
        contentValues.put(Constant.NEW_STUDENT_ID,newStudentAdd.new_add_id)
        contentValues.put(Constant.NEW_STUDENT_LASTNAME,newStudentAdd.new_add_familya)
        contentValues.put(Constant.NEW_STUDENT_NAME,newStudentAdd.new_add_ism)
        contentValues.put(Constant.NEW_STUDENT_FATHER,newStudentAdd.new_add_otasi)
        contentValues.put(Constant.NEW_DAY,newStudentAdd.new_add_date)
        contentValues.put(Constant.GRUHNAME_NEW_STUDENT,newStudentAdd.intent_groupname)
        return database.update(Constant.NEW_STUDENT_TABLE, contentValues, "${Constant.NEW_STUDENT_ID} = ?", arrayOf(newStudentAdd.new_add_id.toString()))
    }

    override fun getnewStudentById(id: Int): NewStudentAdd {
        var database = this.readableDatabase
        var cursor = database.query(Constant.NEW_STUDENT_TABLE, arrayOf(Constant.NEW_STUDENT_ID,Constant.NEW_STUDENT_LASTNAME,Constant.NEW_STUDENT_NAME,Constant.NEW_STUDENT_FATHER,Constant.NEW_DAY,Constant.GRUHNAME_NEW_STUDENT),
            "${Constant.NEW_STUDENT_ID} = ?", arrayOf("$id"),null,null,null)
        cursor?.moveToFirst()
        return NewStudentAdd(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5))


    }

    override fun newStudentDelete(newStudentAdd: NewStudentAdd) {
        var database = this.writableDatabase
        database.delete(Constant.NEW_STUDENT_TABLE,"${Constant.NEW_STUDENT_ID} = ?", arrayOf("${newStudentAdd.new_add_id}"))
        database.close()
    }
}