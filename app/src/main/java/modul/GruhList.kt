package modul

import java.io.Serializable

class GruhList:Serializable {
    var guruhId:Int?=null
    var gruhName:String?=null
    var chois_mentor:String?=null
    var time:String?=null
    var day:String?=null
    var truthFalse:String?=null
    var course:String?=null

    constructor(
        guruhId: Int?,
        gruhName: String?,
        chois_mentor: String?,
        time: String?,
        day: String?,
        truthFalse: String?,
        course: String?
    ) {
        this.guruhId = guruhId
        this.gruhName = gruhName
        this.chois_mentor = chois_mentor
        this.time = time
        this.day = day
        this.truthFalse = truthFalse
        this.course = course
    }

    constructor(
        gruhName: String?,
        chois_mentor: String?,
        time: String?,
        day: String?,
        truthFalse: String?,
        course: String?
    ) {
        this.gruhName = gruhName
        this.chois_mentor = chois_mentor
        this.time = time
        this.day = day
        this.truthFalse = truthFalse
        this.course = course
    }

    constructor()
    constructor(gruhName: String?, chois_mentor: String?, time: String?) {
        this.gruhName = gruhName
        this.chois_mentor = chois_mentor
        this.time = time
    }

    constructor(truthFalse: String?) {
        this.truthFalse = truthFalse
    }


}