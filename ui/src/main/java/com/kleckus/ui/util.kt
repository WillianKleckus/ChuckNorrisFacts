package com.kleckus.ui

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun doAsync(
    beforeAction : () -> Unit,
    afterAction : () -> Unit,
    action : suspend () -> Unit,
){
    onIOThread{
        onMainThread(beforeAction)
        action()
        onMainThread(afterAction)
    }
}

fun onMainThread(action: () -> Unit){
    CoroutineScope(Dispatchers.Main).launch { action() }
}

fun onIOThread(action: suspend () -> Unit){
    CoroutineScope(Dispatchers.IO).launch { action() }
}

fun toastMsg(context: Context, message : String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}