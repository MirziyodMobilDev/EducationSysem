package modul

import java.io.Serializable

class Course:Serializable {
    var coursId:Int?=null
    var courseName:String?=null
    var coursText:String?= null

    constructor(coursId: Int?, courseName: String?, coursText: String?) {
        this.coursId = coursId
        this.courseName = courseName
        this.coursText = coursText
    }


    constructor()
    constructor(courseName: String?, coursText: String?) {
        this.courseName = courseName
        this.coursText = coursText
    }
}