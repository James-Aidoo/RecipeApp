/**
 * Copyright (C) 2020 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.questdev.domain.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure(val message: String = String(), val throwable: Throwable? = null) {

    constructor(message: String) : this(message, null)
    constructor(throwable: Throwable?) : this(String(), throwable)

    class NetworkConnection(message: String = "") : Failure(message)
    class ServerError(throwable: Throwable? = null) : Failure(throwable)

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
