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


package guide.basic.example01

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.Random

fun main(args: Array<String>) {
    runBlocking(CommonPool) {
        (20..30)
                .map { i ->
                    async(context) {
                        workHard(i, "Test $i")
                    }
                }
                .map { it.await() }

    }
}

suspend fun workHard(repeat: Int, text: String) {
    val rand = Random().nextLong() % 20
    val delay = Math.abs(100 * rand)
    delay(delay)
    println("$text > $delay")
}
