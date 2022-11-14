package app.dokt.test

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Boolean?.shouldBeTrue() {
    shouldNotBeNull()
    shouldBeTrue()
}

inline infix fun <reified T> T.jsonShouldBe(expected: String) = Json.encodeToString(this) shouldBe expected