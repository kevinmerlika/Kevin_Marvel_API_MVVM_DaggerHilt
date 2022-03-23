package com.example.kevin_marvel_mvvm_clean_architecture.Util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object{
        const val BASE_URL = "https://gateway.marvel.com"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "a50309c3f21dbbc9d6a921f874b1468f"
        const val PRIVATE_KEY = "076b24161e6b9f1720047c2c116b6c8fa0920eaf"
        const val limit = "1"
        fun hash():String{
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}