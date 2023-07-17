package producer

import domain.Product
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.messaging.annotation.MessageBody

@KafkaClient(id="simulator")
interface Producer {
    @Topic("\${kafka.topic}")
    fun sendRecords(@MessageBody product: Product)
}