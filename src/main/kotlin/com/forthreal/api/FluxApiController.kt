package com.forthreal.api

import com.alibaba.fastjson.JSONObject
import com.forthreal.classes.ListFiller
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

@RestController
class FluxApiController {
    @GetMapping(
        produces = [MediaType.APPLICATION_STREAM_JSON_VALUE],
        value = ["/clientEventStream/{authorisation}"]
    )
    fun clientEventStream(@PathVariable("authorisation") authorisation: String): Flux<Any> {
        val jsonObject = JSONObject()

        if(authorisation.contentEquals("123")) {
            val longFlux = Flux.interval(Duration.ofSeconds(1))
            val dataFlux = Flux.fromStream<JSONObject> {
                                    Stream.generate { toJson(ListFiller.list.pop()) }
                                }

            return Flux.zip(dataFlux, longFlux).map{ x -> x.t1 }
        } else {
            jsonObject["status"] = 255
        }

        return Flux.just(jsonObject)
    }

    private fun toJson(int: Int?)
        = int?.let { JSONObject(mapOf(Pair("value", it))) }
    
}