package ru.otus.otuskotlin.marketplace.e2e.be.test.action.v2

import io.kotest.assertions.asClue
import io.kotest.assertions.withClue
import io.kotest.matchers.should
import io.kotest.matchers.shouldNotBe
import ru.otus.otuskotlin.marketplace.api.v2.models.*
import ru.otus.otuskotlin.marketplace.blackbox.test.action.beValidId
import ru.otus.otuskotlin.marketplace.e2e.be.fixture.client.Client

suspend fun Client.readAd(id: String?, debug: AdDebug = debugStubV2): AdResponseObject = readAd(id, debug = debug) {
    it should haveSuccessResult
    it.ad shouldNotBe null
    it.ad!!
}

suspend fun <T> Client.readAd(id: String?, debug: AdDebug = debugStubV2, block: (AdReadResponse) -> T): T =
    withClue("readAdV1: $id") {
        id should beValidId

        val response = sendAndReceive(
            "ad/read",
            AdReadRequest(
                debug = debug,
                ad = AdReadObject(id = id)
            )
        ) as AdReadResponse

        response.asClue(block)
    }
