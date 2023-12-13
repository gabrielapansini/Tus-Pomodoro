package com.example.tuspomodoro.ui

data class DaySchedule(
    val FirstClass: ClassDetails,
    val SecondClass: ClassDetails,
    val ThirdClass: ClassDetails,
    val FourthClass: ClassDetails,
    val FifthClass: ClassDetails
) {
    constructor(map: Map<String, Any>) : this(
        FirstClass = ClassDetails(map["FirstClass"] as HashMap<String, Any>),
        SecondClass = ClassDetails(map["SecondClass"] as HashMap<String, Any>),
        ThirdClass = ClassDetails(map["ThirdClass"] as HashMap<String, Any>),
        FourthClass = ClassDetails(map["FourthClass"] as HashMap<String, Any>),
        FifthClass = ClassDetails(map["FifthClass"] as HashMap<String, Any>)
    )
}

