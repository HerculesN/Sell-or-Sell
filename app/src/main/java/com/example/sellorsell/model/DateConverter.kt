package com.example.sellorsell.model

import android.content.Context
import androidx.room.*
import java.util.*

// model-> kotlin class -> file

class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?):  Date? {
        return if(dateLong != null) Date(dateLong) else null
    }
    @TypeConverter
    fun fromDate(date: Date?) : Long? {
        return date?.time
    }
}