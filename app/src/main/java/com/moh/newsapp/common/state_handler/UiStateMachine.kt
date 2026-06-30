package com.moh.newsapp.common.state_handler

import android.os.Parcelable
import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadOnlyProperty

interface UiStateMachine<UI_STATE : Parcelable> :
    ReadOnlyProperty<Any?, StateFlow<UI_STATE>> {

    val isStateRestored: Boolean

    fun update(block: UI_STATE.() -> UI_STATE)
}