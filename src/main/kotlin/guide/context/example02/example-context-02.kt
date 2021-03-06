/*
 * Copyright 2016-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package guide.context.example02

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking<Unit> {
    val jobs = arrayListOf<Job>()
    jobs += launch(Unconfined) {
        // not confined -- will work with main thread
        println(" 'Unconfined': I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println(" 'Unconfined': After delay in thread ${Thread.currentThread().name}")
    }
    jobs += launch(context) {
        // context of the parent, runBlocking coroutine
        println("    'context': I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("    'context': After delay in thread ${Thread.currentThread().name}")
    }
    jobs.forEach { it.join() }
}
