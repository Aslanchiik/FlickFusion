package com.example.flickfusion.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.domain.utils.Either
import com.example.domain.utils.NetworkError
import com.example.flickfusion.presentation.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    @Suppress("FunctionName")
    protected open fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    /**
     * Reset [MutableUIStateFlow] to [UIState.Idle]
     */
    protected fun <T> MutableStateFlow<UIState<T>>.reset() {
        value = UIState.Idle()
    }

    /**
     * Collect network request result without mapping
     *
     * @receiver [NetworkError] or [T] in [Flow] with [Either]
     *
     * @param T domain layer model
     * @param state [MutableStateFlow] with [UIState] depending request result
     *
     * @see viewModelScope
     * @see launch
     * @see [Flow.collect]
     */
    protected open fun <T> Flow<Either<NetworkError, T>>.collectNetworkRequest(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectNetworkRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = UIState.Success(it.value)
                }
            }
        }
    }

    /**
     * Collect network request result with mapping
     *
     * @receiver [NetworkError] or [T] in [Flow] with [Either]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param state [MutableStateFlow] with [UIState] depending request result
     * @param mapToUI high-order function for setup mapper function
     *
     * @see viewModelScope
     * @see launch
     * @see [Flow.collect]
     */
    protected fun <T, S> Flow<Either<NetworkError, T>>.collectNetworkRequest(
        state: MutableStateFlow<UIState<S>>,
        mapToUI: (T) -> S
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectNetworkRequest.collect {
                when (it) {
                    is Either.Left -> state.value = UIState.Error(it.value)
                    is Either.Right -> state.value = UIState.Success(mapToUI(it.value))
                }
            }
        }
    }

    /**
     * Collect local request to database with mapping
     *
     * @receiver [T] with [Flow]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param mapToUI high-order function for setup mapper functions
     */
    protected fun <T, S> Flow<T>.collectLocalRequest(
        mapToUI: (T) -> S
    ): Flow<S> = map { value: T ->
        mapToUI(value)
    }

    /**
     * Collect local request to database with mapping with [List]
     *
     * @receiver [T] in [List] with [Flow]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param mapToUI high-order function for setup mapper functions
     */
    protected fun <T, S> Flow<List<T>>.collectLocalRequestForList(
        mapToUI: (T) -> S
    ): Flow<List<S>> = map { value: List<T> ->
        value.map { data: T ->
            mapToUI(data)
        }
    }

    /**
     * Collect paging request with mapping
     *
     * @receiver [PagingData] with [T] in [Flow]
     *
     * @param T domain layer model
     * @param S presentation layer model
     * @param mapToUI high-order function for setup mapper function
     *
     * @see cachedIn
     * @see viewModelScope
     */
    protected fun <T : Any, S : Any> Flow<PagingData<T>>.collectPagingRequest(
        mapToUI: (T) -> S
    ): Flow<PagingData<S>> = map { value: PagingData<T> ->
        value.map { data: T ->
            mapToUI(data)
        }
    }.cachedIn(viewModelScope)
}