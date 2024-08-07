package ru.otus.otuskotlin.marketplace.e2e.be.test.action.v1

import io.kotest.assertions.asClue
import io.kotest.assertions.withClue
import io.kotest.matchers.should
import io.kotest.matchers.shouldNotBe
import ru.otus.otuskotlin.marketplace.api.v1.models.AdReadObject
import ru.otus.otuskotlin.marketplace.api.v1.models.AdReadRequest
import ru.otus.otuskotlin.marketplace.api.v1.models.AdReadResponse
import ru.otus.otuskotlin.marketplace.api.v1.models.AdResponseObject
import ru.otus.otuskotlin.marketplace.e2e.be.test.action.beValidId
import ru.otus.otuskotlin.marketplace.e2e.be.fixture.client.Client

suspend fun Client.readAd(id: String?): AdResponseObject = readAd(id) {
    it should haveSuccessResult
    it.ad shouldNotBe null
    it.ad!!
}

suspend fun <T> Client.readAd(id: String?, block: (AdReadResponse) -> T): T =
    withClue("readAdV1: $id") {
        id should beValidId

        val response = sendAndReceive(
            "ad/read",
            AdReadRequest(
                requestType = "read",
                debug = debug,
                ad = AdReadObject(id = id)
            )
        ) as AdReadResponse

        response.asClue(block)
    }
