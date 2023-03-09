package com.clearlife.toppingservice

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.concurrent.ThreadLocalRandom

@SpringBootApplication
class ToppingServiceApplication

fun main(args: Array<String>) {
    runApplication<ToppingServiceApplication>(*args)
}

@RestController
class RestController (val toppinMetricService: ToppingMetricService){
    @GetMapping(value = ["/metrics"],
            produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun metrics() = toppinMetricService.generateMetrics()

}

@Service
class ToppingMetricService {
    fun generateMetrics(): Flux<ToppingMetrics> {
        return Flux
                .interval(
                        Duration.ofSeconds(1))
                .map{ToppingMetrics(randomCount(1000), randomCount(10), randomStrList(), randomStrList())}
    }

    private fun randomStrList(): List<String> {
        val ret = ArrayList<String>()
        val ct = ThreadLocalRandom
                .current().nextInt(2)
        for (i in 0..ct){
            ret.add(RandomStringUtils.randomAlphabetic(6).uppercase())
        }
        return ret
    }

    private fun randomCount(bound:Int): Int {
        return ThreadLocalRandom.current().nextInt(bound)
    }

}

data class ToppingMetrics (val totalCountPerTopping: Int,
                           val uniqueCountPerTopping: Int,
                           val mostPopularToppings: List<String>,
                           val leastPopularToppings: List<String>
)
