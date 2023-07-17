package contoller

import domain.Product
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import producer.Producer

@Controller("/start")
class StartController(
    private val producer: Producer
) {
    private val logger: Logger = LoggerFactory.getLogger(StartController::class.java)
    companion object {
        val charPool : List<Char> =  ('a'..'z') + ('A'..'Z') + ('0'..'9')
    }

    @Get
    fun start() {
        logger.info("Producing messages")
        for (value in 1..10000) {
            producer.sendRecords(Product((1.. 10).map { charPool.random() }.joinToString("")))
        }
    }
}