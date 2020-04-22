package ci.ashamaz.hashtagsubscriber

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class HashtagSubscriberApplication {
    init {
        ApiContextInitializer.init()
    }
}


fun main(args: Array<String>) {
    runApplication<HashtagSubscriberApplication>(*args)
}
