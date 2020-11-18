package com.kleckus.ui

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