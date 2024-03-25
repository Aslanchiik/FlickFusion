package com.example.flickfusion.presentation.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun LifecycleOwner.launchWithRepeatOnLifecycle(
    state: Lifecycle.State,
    crossinline block: suspend CoroutineScope.() -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            block()
        }
    }
}

inline fun <T> Flow<T>.launchAndCollectIn(
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline collector: suspend CoroutineScope.(T) -> Unit
) = viewLifecycleOwner.launchWithRepeatOnLifecycle(state) {
    collect { collector(it) }
}

inline fun <T> Flow<T>.launchAndCollectLatestIn(
    viewLifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline collector: suspend CoroutineScope.(T) -> Unit
) = viewLifecycleOwner.launchWithRepeatOnLifecycle(state) {
    collectLatest { collector(it) }
}