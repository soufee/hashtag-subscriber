package ci.ashamaz.hashtagsubscriber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
@EnableScheduling
class HashtagSubscriberApplication


fun main(args: Array<String>) {
    ApiContextInitializer.init()
    runApplication<HashtagSubscriberApplication>(*args)

}
