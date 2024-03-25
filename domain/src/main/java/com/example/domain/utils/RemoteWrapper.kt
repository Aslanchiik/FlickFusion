package com.example.domain.utils

import kotlinx.coroutines.flow.Flow

/**
 * Simple wrapper for convenience of network requests in repositories
 *
 * @see Flow
 * @see Either
 * @see NetworkError
 */
internal typealias RemoteWrapper<T> = Flow<Either<NetworkError, T>>