package com.krupa.employeelistapp.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


/**
 * Extension on [Flow] to launch a coroutine at the specified [Lifecycle.State]
 * [repeatOnLifecycle] collect from the upstream Flow when the lifecycle is at least at minActiveState state.
 * The flow will automatically start and cancel collecting from this upstream flow as the lifecycle
 * moves in and out of the target state.
 *
 * @param owner an object of the desired [LifecycleOwner]
 * @param minActiveState an optional [Lifecycle.State] which defaults to [Lifecycle.State.STARTED]
 * @param action a [suspend] function that performs actions
 * @return a [Job] encapsulating a coroutine started with [launch]
 *
 * Warning: Lifecycle.State.INITIALIZED is not allowed in this API. Passing it as a parameter will
 * throw an IllegalArgumentException.
 */
inline fun <reified T> Flow<T>.observeWithLifecycle(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline action: suspend (T) -> Unit
): Job = owner.lifecycleScope.launch {
    owner.repeatOnLifecycle(minActiveState) { collect { action(it) } }
}