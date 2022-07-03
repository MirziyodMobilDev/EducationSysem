package db

import modul.*

interface CoursDataBaseSerrvis{
    fun InsertCourse(course: Course)
    fun getAllCourse():List<Course>
    fun getByIdCourse(id:Int):Course
    fun deleteCourse(course: Course)
    fun updateCourse(course: Course):Int

    fun InsertMentor(mentors: Mentor)
    fun getAllMentor():List<Mentor>
    fun deleteMentor(mentors: Mentor)
    fun updateMentor(mentors: Mentor):Int
    fun getBtIdMentor(id:Int): Mentor

    fun Insertguruh(gruhList: GruhList)
    fun getAllGuruh():List<GruhList>
    fun update(gruhList: GruhList):Int
    fun getGuruhById(id:Int): GruhList
    fun newGuruhDelete(gruhList: GruhList)

    fun InsertNewStudent(newStudentAdd: NewStudentAdd)
    fun getAllNewStudent():List<NewStudentAdd>
    fun updateNewStudent(newStudentAdd: NewStudentAdd):Int
    fun getnewStudentById(id:Int): NewStudentAdd
    fun newStudentDelete(newStudentAdd: NewStudentAdd)

}