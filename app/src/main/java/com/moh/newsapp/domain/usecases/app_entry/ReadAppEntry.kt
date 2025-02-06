package com.moh.newsapp.domain.usecases.app_entry

import com.moh.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    // operator ==> so we can call the method with class name

     operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}