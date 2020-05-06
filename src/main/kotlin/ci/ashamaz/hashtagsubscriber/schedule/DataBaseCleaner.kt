package ci.ashamaz.hashtagsubscriber.schedule

import ci.ashamaz.hashtagsubscriber.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime


@Component
class DataBaseCleaner {
    val logger = LoggerFactory.getLogger(DataBaseCleaner::class.java)

    @Autowired
    val messageService: MessageService?=null

    @Scheduled(cron = "0 0 * * * ?")
    fun reportCurrentData() {
        logger.info("Scheduler working: " + LocalDateTime.now())
        messageService?.deleteAllOld(2)
    }
}