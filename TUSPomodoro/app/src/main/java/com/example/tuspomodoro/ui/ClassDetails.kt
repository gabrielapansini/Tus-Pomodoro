package com.example.tuspomodoro.ui

data class ClassDetails(
    val Subject: String,
    val LectureHall: String,
    val LectureType: String,
    val Lecturer: String
) {
    constructor(map: Map<String, Any>) : this(
        Subject = map["Subject"] as String,
        LectureHall = map["LectureHall"] as String,
        LectureType = map["LectureType"] as String,
        Lecturer = map["Lecturer"] as String
    )
}
