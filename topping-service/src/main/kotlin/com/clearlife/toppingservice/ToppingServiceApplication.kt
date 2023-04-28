package com.clearlife.toppingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@SpringBootApplication
class ToppingServiceApplication

fun main(args: Array<String>) {
    runApplication<ToppingServiceApplication>(*args)
}

@RestController
class RestController(val toppingMetricService: ToppingMetricService) {
    @GetMapping("/home")
    fun home():String{
        return "This is home page; public to all"
    }

    @GetMapping("/admin")
    fun admin():String{
        return "This is admin page."
    }

    @GetMapping(value = ["/metrics/{toppingName}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun metrics(@PathVariable toppingName: String) = toppingMetricService.generateMetrics(toppingName)
}

@Controller
class RSocketController(val toppingMetricService: ToppingMetricService) {
    @MessageMapping("toppingmetrics")
    fun metrics(@PathVariable toppingName: String) = toppingMetricService.generateMetrics(toppingName)

}

@Service
class ToppingMetricService {
    val dataProcessorHost = System.getenv("DATA_PROCESSOR_HOST")?.takeIf { it.isNotEmpty() } ?: "data-process-redis"
    val dataProcessorPortStr = System.getenv("DATA_PROCESSOR_PORT")
    val dataProcessorPort = dataProcessorPortStr?.takeIf { it.isNotEmpty() }?.toInt() ?: 6060

    private val webClient = WebClient.create("http://$dataProcessorHost:$dataProcessorPort")
    fun generateMetrics(toppingNme: String): Flux<ToppingMetrics> {
        return Flux.interval(Duration.ofSeconds(10)).flatMap { getToppingMetricsFromApi(toppingNme) }
    }

    private fun getToppingMetricsFromApi(toppingName: String): Mono<ToppingMetrics> {
        return Mono.zip(
                webClient.get().uri("/redisapi/getTotalCount/{toppingName}", toppingName).retrieve().bodyToMono(Int::class.java),
                webClient.get().uri("/redisapi/uniqueUserCount/{toppingName}", toppingName).retrieve().bodyToMono(Int::class.java),
                webClient.get().uri("/redisapi/mostPopularToppings").retrieve().bodyToMono(object : ParameterizedTypeReference<List<String>>() {}),
                webClient.get().uri("/redisapi/leastPopularToppings").retrieve().bodyToMono(object : ParameterizedTypeReference<List<String>>() {})).map { tuple ->
            ToppingMetrics(
                    tuple.t1,
                    tuple.t2,
                    tuple.t3,
                    tuple.t4)
        }
    }
}

data class ToppingMetrics(
        val totalCountPerTopping: Int,
        val uniqueUserCountPerTopping: Int,
        val mostPopularToppings: List<String>,
        val leastPopularToppings: List<String>)