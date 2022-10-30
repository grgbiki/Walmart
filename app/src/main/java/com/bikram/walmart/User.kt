package com.bikram.walmart

class User(fName:String, lName:String, email:String, pass:String) : java.io.Serializable {
    val firstname:String
    val lastname:String
    val username:String
    val password:String

    init {
        firstname = fName
        lastname = lName
        username = email
        password = pass
    }


}