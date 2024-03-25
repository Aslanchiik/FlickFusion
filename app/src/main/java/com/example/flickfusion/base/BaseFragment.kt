package com.example.flickfusion.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.viewbinding.ViewBinding
import com.example.domain.utils.NetworkError
import com.example.flickfusion.presentation.extension.launchAndCollectIn
import com.example.flickfusion.presentation.extension.showToastLong
import com.example.flickfusion.presentation.ui.state.UIState
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val binding: Binding
    protected abstract val viewModel: ViewModel

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        setupRequests()
        setupSubscribers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupSubscribers() {
    }

    protected open fun <T> StateFlow<UIState<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((UIState<T>) -> Unit)? = null,
        onError: ((error: NetworkError) -> Unit),
        onSuccess: ((data: T) -> Unit)
    ) {
        launchRepeatOnLifecycle(lifecycleState) {
            this@collectUIState.collect {
                state?.invoke(it)
                when (it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {}
                    is UIState.Error -> onError.invoke(it.error)
                    is UIState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    protected fun <T> UIState<T>.setupViewVisibility(
        group: Group, loader: CircularProgressIndicator, willShowViewIfSuccess: Boolean = true
    ) {
        fun showLoader(isVisible: Boolean) {
            group.isVisible = !isVisible
            loader.isVisible = isVisible
        }

        when (this) {
            is UIState.Idle -> {}
            is UIState.Loading -> showLoader(true)
            is UIState.Error -> showLoader(false)
            is UIState.Success -> showLoader(!willShowViewIfSuccess)
        }
    }

    protected fun NetworkError.setupApiErrors(vararg inputs: TextInputLayout) = when (this) {
        is NetworkError.Unexpected -> {
            showToastLong(this.error)
        }

        is NetworkError.Api -> {
            showToastLong(this.error)
        }
    }

    protected open fun <T> Flow<T>.collectSafely(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        collector: (T) -> Unit
    ) {
        launchRepeatOnLifecycle(state) {
            this@collectSafely.collect {
                collector(it)
            }
        }
    }

    protected fun <T : Any> Flow<PagingData<T>>.collectPaging(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (value: PagingData<T>) -> Unit
    ) = launchAndCollectIn(viewLifecycleOwner, lifecycleState) {
        action(it)
    }

    private fun launchRepeatOnLifecycle(
        state: Lifecycle.State,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                block()
            }
        }
    }
}