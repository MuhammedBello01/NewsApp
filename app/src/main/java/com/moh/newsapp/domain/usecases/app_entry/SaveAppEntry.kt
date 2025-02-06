package com.moh.newsapp.domain.usecases.app_entry

import com.moh.newsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    // operator ==> We can call method with class name

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}