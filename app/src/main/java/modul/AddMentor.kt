package modul

class AddMentor {
    var mentorId:String? = null
    var mentorLastname:String? = null
    var mentorName:String? = null
    var mentorFathersName:String? = null
    var mentorTitle:String? = null

    constructor(
        mentorId: String?,
        mentorLastname: String?,
        mentorName: String?,
        mentorFathersName: String?,
        mentorTitle: String?,
    ) {
        this.mentorId = mentorId
        this.mentorLastname = mentorLastname
        this.mentorName = mentorName
        this.mentorFathersName = mentorFathersName
        this.mentorTitle = mentorTitle
    }

    constructor()


}