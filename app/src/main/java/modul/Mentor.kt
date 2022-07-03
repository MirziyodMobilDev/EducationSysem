package modul

class Mentor {
    var mentorId:Int?=null
    var mentorName:String?=null
    var mentorLastname:String?=null
    var mentorsFather:String?=null
    var courseName:String? = null



    constructor(
        mentorId: Int?,
        mentorName: String?,
        mentorLastname: String?,
        mentorsFather: String?,
        courseName:String?
    ) {
        this.mentorId = mentorId
        this.mentorName = mentorName
        this.mentorLastname = mentorLastname
        this.mentorsFather = mentorsFather
        this.courseName = courseName
    }

    constructor(mentorName: String?, mentorLastname: String?, mentorsFather: String?,courseName: String?) {
        this.mentorName = mentorName
        this.mentorLastname = mentorLastname
        this.mentorsFather = mentorsFather
        this.courseName = courseName
    }


    constructor()

}