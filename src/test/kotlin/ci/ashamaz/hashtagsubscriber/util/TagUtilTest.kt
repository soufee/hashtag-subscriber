package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.HashtagSubscriberApplication
import ci.ashamaz.hashtagsubscriber.util.tag.TagUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import kotlin.streams.toList

@ActiveProfiles("test")
@ContextConfiguration
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [HashtagSubscriberApplication::class])
class TagUtilTest {
    val incomeMessage = """
        <div class="tgme_widget_message force_userpic js-widget_message" data-post="xCareers/1491" data-view="eyJjIjotMTE3OTkzMzcwNCwicCI6MTQ5MSwidCI6MTU4ODU3NjcxOSwiaCI6ImUyNTk2YmM0MDUxYjI2ZDljMyJ9"> 
         <div class="tgme_widget_message_user">
          <a href="https://t.me/xCareers"><i class="tgme_widget_message_user_photo bgcolor5" data-content="x"><img src="https://cdn4.telesco.pe/file/JEtzTVC-UAOUiyWaFtxxT2dJEnVGwLloiKiLosAnvy56qmU8f3NYyhlpmquODLV2sGQ9RoecG9odZP7C_cXYs0DraEq-eaBlEznS_GcsKvyw-ICcC9ImJHm5wKQNlWqOA-54St4lKwXm6PDNqX2Rox1yn0abNKvBVVkSigw96DEU2k4wqpDMzAUWkg5hrO9pwzrHsiSHtmGBfz9wPmS4uCMe8CPZnsE35m85kLsVgu0h3YMw9kzFPL87tXNwuvv2JFh9AKQDokDPDlIOfPmsztNDAbevt3crthha2Hhqp7SKNKeOEa8sdIZy8BWcTNmoqq4dL86xN9rJcvtNduGu8g.jpg"></i></a>
         </div> 
         <div class="tgme_widget_message_bubble"> <i class="tgme_widget_message_bubble_tail"> 
           <svg class="bubble_icon" width="9px" height="20px" viewbox="0 0 9 20"> <g fill="none"> 
             <path class="background" fill="#ffffff" d="M8,1 L9,1 L9,20 L8,20 L8,18 C7.807,15.161 7.124,12.233 5.950,9.218 C5.046,6.893 3.504,4.733 1.325,2.738 L1.325,2.738 C0.917,2.365 0.89,1.732 1.263,1.325 C1.452,1.118 1.72,1 2,1 L8,1 Z"></path> 
             <path class="border_1x" fill="#d7e3ec" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0 L9,0 L9,20 L7,20 L7,20 L7.002,18.068 C6.816,15.333 6.156,12.504 5.018,9.58 C4.172,7.406 2.72,5.371 0.649,3.475 C-0.165,2.729 -0.221,1.464 0.525,0.649 C0.904,0.236 1.439,0 2,0 Z"></path> 
             <path class="border_2x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.5 L9,0.5 L9,20 L7.5,20 L7.5,20 L7.501,18.034 C7.312,15.247 6.64,12.369 5.484,9.399 C4.609,7.15 3.112,5.052 0.987,3.106 C0.376,2.547 0.334,1.598 0.894,0.987 C1.178,0.677 1.579,0.5 2,0.5 Z"></path> 
             <path class="border_3x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.667 L9,0.667 L9,20 L7.667,20 L7.667,20 L7.668,18.023 C7.477,15.218 6.802,12.324 5.64,9.338 C4.755,7.064 3.243,4.946 1.1,2.983 C0.557,2.486 0.52,1.643 1.017,1.1 C1.269,0.824 1.626,0.667 2,0.667 Z"></path> 
            </g> 
           </svg> </i> 
          <div class="tgme_widget_message_author">
           <a class="tgme_widget_message_owner_name" href="https://t.me/xCareers"><span dir="auto">xCareers: Digital Jobs</span></a>
          </div> 
          <div class="tgme_widget_message_text js-message_text" dir="auto">
           <b>РАЗРАБОТЧИК IOS <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94A5.png')"><b>🔥</b></i></b>
           <br>
           <br>Формат: <a href="?q=%23%D1%83%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%BA%D0%B0">#удаленка</a> (на время карантина) потом <a href="?q=%23%D0%BE%D1%84%D0%B8%D1%81">#офис</a>
           <br>Локация: <a href="?q=%23%D0%BC%D0%BE%D1%81%D0%BA%D0%B2%D0%B0">#москва</a>, <a href="?q=%23%D1%81%D0%B0%D0%BD%D0%BA%D1%82_%D0%BF%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3">#санкт_петербург</a>, <a href="?q=%23%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F">#россия</a>
           <br>Занятость: Полная
           <br>Опыт: от 3 до 6 лет
           <br>Зарплата: от 170 000 рублей в месяц
           <br>Отрасль: Банковские услуги
           <br>
           <br>Российский банк топ-10
           <br>
           <br><b>Требования:</b>
           <br>
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Опыт разработки мобильных приложений от двух лет
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Отличные знания платформы iOS и Swift
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Опыт применения архитектурных паттернов: MV*, VIPER
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Опыт разработки клиент-серверных приложений (HTTP(S), XML, JSON)
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Опыт написания unit-тестов
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Опыт работы с GIT
           <br>
           <br><b>Обязанности:</b>
           <br>
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Разработка мобильного приложения для платформы iOS
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Два проекта на выбор: мобильный банк для физических лиц и для юридических лиц
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Разработка unit-тестов, рефакторинг, code review
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Формирование принципов командной разработки
           <br>
           <br><b>Условия:</b>
           <br>
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Гибкое начало и окончание рабочего дня, свободный дресс-код
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Релокационный пакет (компенсация расходов на покупку билетов и аренду жилья в течение первых трех месяцев)
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Официальное оформление в соответствии с ТК РФ
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Конкурентный уровень дохода: оклад + премии
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Медицинская страховка, страховка для выезжающих за границу
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Доплата к отпускному и больничному листу
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Льготное кредитование для сотрудников
           <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94B8.png')"><b>🔸</b></i> Обучение в корпоративном университете банка
           <br>
           <br><b>Информация:</b>
           <br>
           <br>Отличные знания ООП, алгоритмов и структур данных
           <br>Знание Objective-C
           <br>
           <br>Откликнуться: <a href="https://t.me/Levelller" target="_blank">@Levelller</a>
           <br>
           <br><a href="?q=%23%D1%80%D0%B0%D0%B7%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%BA%D0%B0">#разработка</a> <a href="?q=%23ios">#ios</a>
          </div> 
          <div class="tgme_widget_message_footer compact js-message_footer"> 
           <div class="tgme_widget_message_info short js-message_info"> <span class="tgme_widget_message_views">4.4K</span><span class="copyonly"> views</span><span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/xCareers/1491"><time datetime="2020-04-30T17:00:04+00:00">17:00</time></a></span> 
           </div> 
          </div> 
         </div> 
        </div>
    """.trimIndent()

    @Autowired
    val tagUtil: TagUtil?=null

    @Test
    fun getTagsFromText() {
        val tags = tagUtil?.getTagsFromText (incomeMessage)
        assertNotNull(tags)
        tags!!
        assertNotEquals(0, tags.size)
        val tagValues = tags.stream().map { it.tag }.toList()
        assertEquals(tags.size, tagValues.size)
        assertTrue(tagValues.contains("#удаленка"))
        assertTrue(tagValues.contains("#москва"))
        assertTrue(tagValues.contains("#офис"))
        assertTrue(tagValues.contains("#санкт_петербург"))
        assertTrue(tagValues.contains("#россия"))
        assertTrue(tagValues.contains("#ios"))
        assertTrue(tagValues.contains("#разработка"))
    }
}