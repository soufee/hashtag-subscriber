package ci.ashamaz.hashtagsubscriber.util

import org.jsoup.Jsoup
import org.springframework.test.web.reactive.server.JsonPathAssertions

class Literals {
    companion object {
        val xCareerHtml = """
    <!doctype html>
    <html>
     <head> 
      <meta charset="utf-8"> 
      <title>xCareers: Digital Jobs – Telegram</title> 
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
      <meta name="format-detection" content="telephone=no"> 
      <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
      <meta name="MobileOptimized" content="176"> 
      <meta name="HandheldFriendly" content="True"> 
      <meta property="og:title" content="xCareers: Digital Jobs"> 
      <meta property="og:image" content="https://cdn4.telesco.pe/file/N1x91UaagzAHvSwNHSFTs7N1GLpHqjGDDQGgxE6ZacSXF10zqCyiTiQGW_ejOZP8i0JXz3YDYrC4ivyX5LKWKYgrqkJDDjkI9sflWAUTHa6RiWdS3b5FkmCKb9nGQgQKlMg5DYb7hEmSeeewIrvlRmvQVEHhSZgpyIjB4ne7Cz60_GFDCC1rYiNbXE17OZrPwbL6y76rqZwi62GE1fCxSl87wpku4RCbczUoagaBWAXPYv9mM0-6Bw-9eECM3DrJ6egbG81Yf8gKUx9vKLlHq-s_Ez4BZySrHGcpdcxvVAxEp8w9PM9zD_o3OQqAqjTbabLFnjtuXz02pLuApcKV8g.jpg"> 
      <meta property="og:site_name" content="Telegram"> 
      <meta property="og:description" content="Денежные вакансии с зарплатой до 350 000р Заработать смогут все!

    Digital, IT, Креатив: #удаленка, #офис, #фриланс

    Размещение вакансий: xcareers.me/job/
    Информация: xcareers.me/info/

    Реклама: @xCareersAdBot
    Связь: @xCareersBot"> 
      <meta property="twitter:title" content="xCareers: Digital Jobs"> 
      <meta property="twitter:image" content="https://cdn4.telesco.pe/file/N1x91UaagzAHvSwNHSFTs7N1GLpHqjGDDQGgxE6ZacSXF10zqCyiTiQGW_ejOZP8i0JXz3YDYrC4ivyX5LKWKYgrqkJDDjkI9sflWAUTHa6RiWdS3b5FkmCKb9nGQgQKlMg5DYb7hEmSeeewIrvlRmvQVEHhSZgpyIjB4ne7Cz60_GFDCC1rYiNbXE17OZrPwbL6y76rqZwi62GE1fCxSl87wpku4RCbczUoagaBWAXPYv9mM0-6Bw-9eECM3DrJ6egbG81Yf8gKUx9vKLlHq-s_Ez4BZySrHGcpdcxvVAxEp8w9PM9zD_o3OQqAqjTbabLFnjtuXz02pLuApcKV8g.jpg"> 
      <meta property="twitter:site" content="@Telegram"> 
      <meta property="al:ios:app_store_id" content="686449807"> 
      <meta property="al:ios:app_name" content="Telegram Messenger"> 
      <meta property="al:ios:url" content="tg://resolve?domain=xCareers"> 
      <meta property="al:android:url" content="tg://resolve?domain=xCareers"> 
      <meta property="al:android:app_name" content="Telegram"> 
      <meta property="al:android:package" content="org.telegram.messenger"> 
      <meta name="twitter:card" content="summary"> 
      <meta name="twitter:site" content="@Telegram"> 
      <meta name="twitter:description" content="Денежные вакансии с зарплатой до 350 000р Заработать смогут все!

    Digital, IT, Креатив: #удаленка, #офис, #фриланс

    Размещение вакансий: xcareers.me/job/
    Информация: xcareers.me/info/

    Реклама: @xCareersAdBot
    Связь: @xCareersBot
    "> 
      <link rel="prev" href="/s/xCareers?before=1491"> 
      <link rel="canonical" href="/s/xCareers?before=1512"> 
      <link rel="shortcut icon" href="//telegram.org/favicon.ico?3" type="image/x-icon"> 
      <link href="https://fonts.googleapis.com/css?family=Roboto:400,500" rel="stylesheet" type="text/css"> 
      <link href="//telegram.org/css/widget-frame.css?27" rel="stylesheet" media="screen"> 
      <link href="//telegram.org/css/telegram-web.css?12" rel="stylesheet" media="screen"> 
     </head> 
     <body class="widget_frame_base tgme_widget emoji_image thin_box_shadow no_transitions"> 
      <header class="tgme_header search_collapsed"> 
       <div class="tgme_container"> 
        <div class="tgme_header_search"> 
         <form class="tgme_header_search_form" action="/s/xCareers"> 
          <svg class="tgme_header_search_form_icon" width="20px" height="20px" viewbox="0 0 20 20">
           <g fill="none" stroke="#788794" stroke-width="1.4">
            <circle cx="9" cy="9" r="6"></circle>
            <path d="M13.5,13.5 L17,17" stroke-linecap="round"></path>
           </g>
          </svg> 
          <input class="tgme_header_search_form_input js-header_search" placeholder="Search" name="q" autocomplete="off" value=""> 
         </form> 
        </div> 
        <div class="tgme_header_right_column"> 
         <section class="tgme_right_column"> 
          <div class="tgme_channel_info"> 
           <div class="tgme_channel_info_header"> <i class="tgme_page_photo_image bgcolor5" data-content="xJ"><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i> 
            <div class="tgme_channel_info_header_title">
             <span dir="auto">xCareers: Digital Jobs</span>
            </div> 
            <div class="tgme_channel_info_header_username">
             <a href="https://t.me/xCareers">@xCareers</a>
            </div> 
           </div> 
           <div class="tgme_channel_info_counters">
            <div class="tgme_channel_info_counter">
             <span class="counter_value">60K</span> <span class="counter_type">members</span>
            </div>
            <div class="tgme_channel_info_counter">
             <span class="counter_value">46</span> <span class="counter_type">photos</span>
            </div>
            <div class="tgme_channel_info_counter">
             <span class="counter_value">1</span> <span class="counter_type">video</span>
            </div>
            <div class="tgme_channel_info_counter">
             <span class="counter_value">479</span> <span class="counter_type">links</span>
            </div>
           </div> 
           <div class="tgme_channel_info_description">
            Денежные вакансии с зарплатой до 350 000р Заработать смогут все!
            <br>
            <br>Digital, IT, Креатив: <a href="?q=%23%D1%83%D0%B4%D0%B0%D0%BB%D0%B5%D0%BD%D0%BA%D0%B0">#удаленка</a>, <a href="?q=%23%D0%BE%D1%84%D0%B8%D1%81">#офис</a>, <a href="?q=%23%D1%84%D1%80%D0%B8%D0%BB%D0%B0%D0%BD%D1%81">#фриланс</a>
            <br>
            <br>Размещение вакансий: <a href="http://xcareers.me/job/" target="_blank" rel="noopener">xcareers.me/job/</a>
            <br>Информация: <a href="http://xcareers.me/info/" target="_blank" rel="noopener">xcareers.me/info/</a>
            <br>
            <br>Реклама: <a href="https://t.me/xCareersAdBot" target="_blank">@xCareersAdBot</a>
            <br>Связь: <a href="https://t.me/xCareersBot" target="_blank">@xCareersBot</a>
           </div> <a class="tgme_channel_download_telegram" href="//telegram.org/dl?tme=4be6fc1fbf2154dd36_2550545574089448236"> 
            <svg class="tgme_channel_download_telegram_icon" width="21px" height="18px" viewbox="0 0 21 18">
             <g fill="none">
              <path fill="#ffffff" d="M0.554,7.092 L19.117,0.078 C19.737,-0.156 20.429,0.156 20.663,0.776 C20.745,0.994 20.763,1.23 20.713,1.457 L17.513,16.059 C17.351,16.799 16.62,17.268 15.88,17.105 C15.696,17.065 15.523,16.987 15.37,16.877 L8.997,12.271 C8.614,11.994 8.527,11.458 8.805,11.074 C8.835,11.033 8.869,10.994 8.905,10.958 L15.458,4.661 C15.594,4.53 15.598,4.313 15.467,4.176 C15.354,4.059 15.174,4.037 15.036,4.125 L6.104,9.795 C5.575,10.131 4.922,10.207 4.329,10.002 L0.577,8.704 C0.13,8.55 -0.107,8.061 0.047,7.614 C0.131,7.374 0.316,7.182 0.554,7.092 Z"></path>
             </g>
            </svg>Download Telegram </a> 
           <div class="tgme_channel_download_telegram_bottom">
            to view and join the conversation
           </div> 
          </div> 
          <div class="tgme_footer"> 
           <div class="tgme_footer_column"> 
            <h5><a href="//telegram.org/faq">About</a></h5> 
           </div> 
           <div class="tgme_footer_column"> 
            <h5><a href="//telegram.org/blog">Blog</a></h5> 
           </div> 
           <div class="tgme_footer_column"> 
            <h5><a href="//telegram.org/apps">Apps</a></h5> 
           </div> 
           <div class="tgme_footer_column"> 
            <h5><a href="//core.telegram.org/">Platform</a></h5> 
           </div> 
          </div> 
         </section> 
        </div> 
        <div class="tgme_header_info"> <a class="tgme_channel_join_telegram" href="//telegram.org/dl?tme=4be6fc1fbf2154dd36_2550545574089448236"> 
          <svg class="tgme_channel_join_telegram_icon" width="19px" height="16px" viewbox="0 0 19 16">
           <g fill="none">
            <path fill="#ffffff" d="M0.465,6.638 L17.511,0.073 C18.078,-0.145 18.714,0.137 18.932,0.704 C19.009,0.903 19.026,1.121 18.981,1.33 L16.042,15.001 C15.896,15.679 15.228,16.111 14.549,15.965 C14.375,15.928 14.211,15.854 14.068,15.748 L8.223,11.443 C7.874,11.185 7.799,10.694 8.057,10.345 C8.082,10.311 8.109,10.279 8.139,10.249 L14.191,4.322 C14.315,4.201 14.317,4.002 14.195,3.878 C14.091,3.771 13.926,3.753 13.8,3.834 L5.602,9.138 C5.112,9.456 4.502,9.528 3.952,9.333 L0.486,8.112 C0.077,7.967 -0.138,7.519 0.007,7.11 C0.083,6.893 0.25,6.721 0.465,6.638 Z"></path>
           </g>
          </svg>Join </a> <a class="tgme_header_link" href="https://t.me/xCareers"> <i class="tgme_page_photo_image bgcolor5" data-content="xJ"><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i> 
          <div class="tgme_header_title">
           xCareers: Digital Jobs
          </div> 
          <div class="tgme_header_counter">
           60K members
          </div> </a> 
        </div> 
       </div> 
      </header> 
      <main class="tgme_main" data-url="/xCareers"> 
       <div class="tgme_container"> 
        <section class="tgme_channel_history js-message_history"> <a href="/s/xCareers?before=1491" class="tme_messages_more js-messages_more" data-before="1491"></a>
         <div class="tgme_widget_message_wrap js-widget_message_wrap">
          <div class="tgme_widget_message force_userpic js-widget_message" data-post="xCareers/1491" data-view="eyJjIjotMTE3OTkzMzcwNCwicCI6MTQ5MSwidCI6MTU4ODU4MTIyNywiaCI6IjNlNGQ4ZjQwOWJkYjZjZWQ2MyJ9"> 
           <div class="tgme_widget_message_user">
            <a href="https://t.me/xCareers"><i class="tgme_widget_message_user_photo bgcolor5" data-content="x"><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a>
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
         </div>
         
         <div class="tgme_widget_message_wrap js-widget_message_wrap">
          <div class="tgme_widget_message force_userpic js-widget_message" data-post="xCareers/1510" data-view="eyJjIjotMTE3OTkzMzcwNCwicCI6MTUxMCwidCI6MTU4ODU4MTIyNywiaCI6IjZlNzdjMzU0MzRiNzBkNjgxNyJ9"> 
           <div class="tgme_widget_message_user">
            <a href="https://t.me/xCareers"><i class="tgme_widget_message_user_photo bgcolor5" data-content="x"><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a>
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
             <b>NODE.JS DEVELOPER</b>
             <br>
             <br>Формат: <a href="?q=%23%D0%BE%D1%84%D0%B8%D1%81">#офис</a>
             <br>Локация: <a href="?q=%23%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C">#казань</a>, <a href="?q=%23%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F">#россия</a>
             <br>Занятость: Полная
             <br>Опыт: от 3 лет
             <br>Зарплата: От 150 000 до 220 000 рублей
             <br>Компания: Stream (ПАО МТС)
             <br>Отрасль: разработка ПО
             <br>
             <br>Мы создаём приложения для компаний группы МТС, постоянно развиваем текущие проекты и запускаем новые. Нас уже больше 500 человек, и это только начало! Ищем в команду Node.js разработчика на проект МТС Коннект. МТС Коннект - это коммуникационный сервис (голосовые звонки, чаты) с использованием коммутации на базе IMS-ядра сети МТС (VoIP).
             <br>
             <br><b>Требования:</b>
             <br>
             <br>- опыт разработки серверных приложений с использованием платформы Node.JS от 2 лет;
             <br>- понимание структур данных, основ ООП и шаблонов проектирования, применимых в рамках Node.js;
             <br>- понимание Event Loop, V8 и модулей Node.JS;
             <br>- опыт работы с микросервисной архитектурой;
             <br>- умение работать NoSQL БД: Mongo, Redis;
             <br>- опыт работы с HighLoad проектами
             <br>
             <br><b>Обязанности:</b>
             <br>
             <br>- разработка серверной части приложения;
             <br>- участие в проектировании архитектуры серверных компонентов;
             <br>- code review
             <br>
             <br><b>Условия:</b>
             <br>
             <br>- оформление по ТК РФ, гибкий график работы
             <br>- Белую" заработную плату + полугодовые бонусы
             <br>- ДМС со стоматологией, страхование жизни после испытательного срока
             <br>- обучение и посещение профильных конференций
             <br>- корпоративную мобильную связь
             <br>- просторный офис в центре Казани.
             <br>
             <br>Откликнуться: <a href="https://t.me/askamasaska" target="_blank">@askamasaska</a>
             <br>
             <br><a href="?q=%23nodejs">#nodejs</a>
             <br>
             <br>--------
             <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/E29AA0.png')"><b>⚠️</b></i> Вакансии закрываются очень быстро. Заглядывайте в <a href="https://t.me/xCareers" target="_blank">@xCareers</a> ежедневно, чтобы успеть откликаться!
            </div> 
            <div class="tgme_widget_message_footer compact js-message_footer"> 
             <div class="tgme_widget_message_info short js-message_info"> <span class="tgme_widget_message_views">4.1K</span><span class="copyonly"> views</span><span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/xCareers/1510"><time datetime="2020-05-03T15:00:03+00:00">15:00</time></a></span> 
             </div> 
            </div> 
           </div> 
           <div class="tgme_widget_message_inline_keyboard">
            <div class="tgme_widget_message_inline_row">
             <a class="tgme_widget_message_inline_button url_button" href="https://xcareers.me/job/" target="_blank" rel="noopener" onclick="return confirm('Open this link?\n\n'+this.href);"><span class="tgme_widget_message_inline_button_text" dir="auto"><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9B8E.png')"><b>🛎️</b></i> Разместить вакансию</span></a>
            </div>
            <div class="tgme_widget_message_inline_row">
             <a class="tgme_widget_message_inline_button url_button" href="https://t.me/xCareersChat" target="_blank" rel="noopener" onclick="return confirm('Open this link?\n\n'+this.href);"><span class="tgme_widget_message_inline_button_text" dir="auto"><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9782.png')"><b>🗂</b></i> Разместить резюме</span></a>
            </div>
           </div> 
          </div>
         </div>
         <div class="tgme_widget_message_wrap js-widget_message_wrap">
          <div class="tgme_widget_message force_userpic js-widget_message" data-post="xCareers/1511" data-view="eyJjIjotMTE3OTkzMzcwNCwicCI6MTUxMSwidCI6MTU4ODU4MTIyNywiaCI6ImFhZGY2NzMwNjFlNzZlMzdjMyJ9"> 
           <div class="tgme_widget_message_user">
            <a href="https://t.me/xCareers"><i class="tgme_widget_message_user_photo bgcolor5" data-content="x"><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a>
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
             <b>MIDDLE/ SENIOR JAVA DEVELOPER</b>
             <br>
             <br>Формат: <a href="?q=%23%D0%BE%D1%84%D0%B8%D1%81">#офис</a>
             <br>Локация: <a href="?q=%23%D0%BA%D0%B0%D0%B7%D0%B0%D0%BD%D1%8C">#казань</a>, <a href="?q=%23%D1%80%D0%BE%D1%81%D1%81%D0%B8%D1%8F">#россия</a>
             <br>Занятость: Полная
             <br>Опыт: от 3 лет
             <br>Зарплата: От 150 000 до 220 000 рублей
             <br>Компания: Stream (ПАО МТС)
             <br>Отрасль: разработка ПО
             <br>
             <br>Мы создаём приложения для компаний группы МТС, постоянно развиваем текущие проекты и запускаем новые. Приглашаем в команду опытного Java разработчика для участия в проекте Smart Wearables! О проекте: Мы создаем платформу для пользователей носимых устройств. Разрабатываем как software, так и hardware.
             <br>
             <br><b>Требования:</b>
             <br>
             <br>- навыки и опыт работы со стеком: Java 8, Spring Framework, Hibernate, PostgreSQL и Kafka (желательно);
             <br>- умение и желание писать unit тесты;
             <br>- опыт участия в enterprise проектах.
             <br>
             <br><b>Обязанности:</b>
             <br>
             <br>- участие в проектировании архитектуры бэкенда;
             <br>- разработке серверной части продукта;
             <br>- проведение code review;
             <br>- участие в оптимизации технического стека и процессов в компании.
             <br>
             <br><b>Условия:</b>
             <br>
             <br>- оформление по ТК РФ, гибкий график работы
             <br>- Белую" заработную плату + полугодовые бонусы
             <br>- ДМС со стоматологией, страхование жизни после испытательного срока
             <br>- обучение и посещение профильных конференций
             <br>- корпоративную мобильную связь
             <br>- просторный офис в центре Казани.
             <br>
             <br>Откликнуться: <a href="https://t.me/askamasaska" target="_blank">@askamasaska</a>
             <br>
             <br><a href="?q=%23java">#java</a>
             <br>
             <br>--------
             <br><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/E29AA0.png')"><b>⚠️</b></i> Вакансии закрываются очень быстро. Заглядывайте в <a href="https://t.me/xCareers" target="_blank">@xCareers</a> ежедневно, чтобы успеть откликаться!
            </div> 
            <div class="tgme_widget_message_footer compact js-message_footer"> 
             <div class="tgme_widget_message_info short js-message_info"> <span class="tgme_widget_message_views">1.5K</span><span class="copyonly"> views</span><span class="tgme_widget_message_meta"><a class="tgme_widget_message_date" href="https://t.me/xCareers/1511"><time datetime="2020-05-04T07:00:02+00:00">07:00</time></a></span> 
             </div> 
            </div> 
           </div> 
           <div class="tgme_widget_message_inline_keyboard">
            <div class="tgme_widget_message_inline_row">
             <a class="tgme_widget_message_inline_button url_button" href="https://xcareers.me/job/" target="_blank" rel="noopener" onclick="return confirm('Open this link?\n\n'+this.href);"><span class="tgme_widget_message_inline_button_text" dir="auto"><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9B8E.png')"><b>🛎️</b></i> Разместить вакансию</span></a>
            </div>
            <div class="tgme_widget_message_inline_row">
             <a class="tgme_widget_message_inline_button url_button" href="https://t.me/xCareersChat" target="_blank" rel="noopener" onclick="return confirm('Open this link?\n\n'+this.href);"><span class="tgme_widget_message_inline_button_text" dir="auto"><i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F9782.png')"><b>🗂</b></i> Разместить резюме</span></a>
            </div>
           </div> 
          </div>
         </div> 
        </section> 
       </div> 
      </main> 
      <script src="//telegram.org/js/jquery.min.js"></script> 
      <script src="//telegram.org/js/jquery-ui.min.js"></script> 
      <script src="//telegram.org/js/widget-frame.js?29"></script> 
      <script src="//telegram.org/js/telegram-web.js?8"></script> 
      <script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-45099287-3', 'auto', {'sampleRate': 5});
    ga('set', 'anonymizeIp', true);
    ga('send', 'pageview');TWeb.init();
    </script>   
      <!-- page generated in 251.72ms --> 
     </body>
    </html>

""".trimIndent()
    val cleanedHtml = """
        xCareers: Digital Jobs – Telegram                                               <i><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i>  <span>xCareers: Digital Jobs</span>   <a href="https://t.me/xCareers" rel="nofollow">@xCareers</a>     <span>60K</span> <span>members</span>   <span>46</span> <span>photos</span>   <span>1</span> <span>video</span>   <span>479</span> <span>links</span>    Денежные вакансии с зарплатой до 350 000р Заработать смогут все! 
        <br> 
        <br>Digital, IT, Креатив: <a rel="nofollow">#удаленка</a>, <a rel="nofollow">#офис</a>, <a rel="nofollow">#фриланс</a> 
        <br> 
        <br>Размещение вакансий: <a href="http://xcareers.me/job/" rel="nofollow">xcareers.me/job/</a> 
        <br>Информация: <a href="http://xcareers.me/info/" rel="nofollow">xcareers.me/info/</a> 
        <br> 
        <br>Реклама: <a href="https://t.me/xCareersAdBot" rel="nofollow">@xCareersAdBot</a> 
        <br>Связь: <a href="https://t.me/xCareersBot" rel="nofollow">@xCareersBot</a>  <a rel="nofollow">     Download Telegram </a>  to view and join the conversation     <a rel="nofollow">About</a>   <a rel="nofollow">Blog</a>   <a rel="nofollow">Apps</a>   <a rel="nofollow">Platform</a>      <a rel="nofollow">     Join </a> <a href="https://t.me/xCareers" rel="nofollow"> <i><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i>  xCareers: Digital Jobs   60K members  </a>       <a rel="nofollow"></a>    <a href="https://t.me/xCareers" rel="nofollow"><i><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a>   <i>         </i>  <a href="https://t.me/xCareers" rel="nofollow"><span>xCareers: Digital Jobs</span></a>   <b>РАЗРАБОТЧИК IOS <i><b>🔥</b></i></b> 
        <br> 
        <br>Формат: <a rel="nofollow">#удаленка</a> (на время карантина) потом <a rel="nofollow">#офис</a> 
        <br>Локация: <a rel="nofollow">#москва</a>, <a rel="nofollow">#санкт_петербург</a>, <a rel="nofollow">#россия</a> 
        <br>Занятость: Полная 
        <br>Опыт: от 3 до 6 лет 
        <br>Зарплата: от 170 000 рублей в месяц 
        <br>Отрасль: Банковские услуги 
        <br> 
        <br>Российский банк топ-10 
        <br> 
        <br><b>Требования:</b> 
        <br> 
        <br><i><b>🔸</b></i> Опыт разработки мобильных приложений от двух лет 
        <br><i><b>🔸</b></i> Отличные знания платформы iOS и Swift 
        <br><i><b>🔸</b></i> Опыт применения архитектурных паттернов: MV*, VIPER 
        <br><i><b>🔸</b></i> Опыт разработки клиент-серверных приложений (HTTP(S), XML, JSON) 
        <br><i><b>🔸</b></i> Опыт написания unit-тестов 
        <br><i><b>🔸</b></i> Опыт работы с GIT 
        <br> 
        <br><b>Обязанности:</b> 
        <br> 
        <br><i><b>🔸</b></i> Разработка мобильного приложения для платформы iOS 
        <br><i><b>🔸</b></i> Два проекта на выбор: мобильный банк для физических лиц и для юридических лиц 
        <br><i><b>🔸</b></i> Разработка unit-тестов, рефакторинг, code review 
        <br><i><b>🔸</b></i> Формирование принципов командной разработки 
        <br> 
        <br><b>Условия:</b> 
        <br> 
        <br><i><b>🔸</b></i> Гибкое начало и окончание рабочего дня, свободный дресс-код 
        <br><i><b>🔸</b></i> Релокационный пакет (компенсация расходов на покупку билетов и аренду жилья в течение первых трех месяцев) 
        <br><i><b>🔸</b></i> Официальное оформление в соответствии с ТК РФ 
        <br><i><b>🔸</b></i> Конкурентный уровень дохода: оклад + премии 
        <br><i><b>🔸</b></i> Медицинская страховка, страховка для выезжающих за границу 
        <br><i><b>🔸</b></i> Доплата к отпускному и больничному листу 
        <br><i><b>🔸</b></i> Льготное кредитование для сотрудников 
        <br><i><b>🔸</b></i> Обучение в корпоративном университете банка 
        <br> 
        <br><b>Информация:</b> 
        <br> 
        <br>Отличные знания ООП, алгоритмов и структур данных 
        <br>Знание Objective-C 
        <br> 
        <br>Откликнуться: <a href="https://t.me/Levelller" rel="nofollow">@Levelller</a> 
        <br> 
        <br><a rel="nofollow">#разработка</a> <a rel="nofollow">#ios</a>    <span>4.4K</span><span> views</span><span><a href="https://t.me/xCareers/1491" rel="nofollow">17:00</a></span>         <a href="https://t.me/xCareers" rel="nofollow"><i><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a>   <i>         </i>  <a href="https://t.me/xCareers" rel="nofollow"><span>xCareers: Digital Jobs</span></a>   <b>NODE.JS DEVELOPER</b> 
        <br> 
        <br>Формат: <a rel="nofollow">#офис</a> 
        <br>Локация: <a rel="nofollow">#казань</a>, <a rel="nofollow">#россия</a> 
        <br>Занятость: Полная 
        <br>Опыт: от 3 лет 
        <br>Зарплата: От 150 000 до 220 000 рублей 
        <br>Компания: Stream (ПАО МТС) 
        <br>Отрасль: разработка ПО 
        <br> 
        <br>Мы создаём приложения для компаний группы МТС, постоянно развиваем текущие проекты и запускаем новые. Нас уже больше 500 человек, и это только начало! Ищем в команду Node.js разработчика на проект МТС Коннект. МТС Коннект - это коммуникационный сервис (голосовые звонки, чаты) с использованием коммутации на базе IMS-ядра сети МТС (VoIP). 
        <br> 
        <br><b>Требования:</b> 
        <br> 
        <br>- опыт разработки серверных приложений с использованием платформы Node.JS от 2 лет; 
        <br>- понимание структур данных, основ ООП и шаблонов проектирования, применимых в рамках Node.js; 
        <br>- понимание Event Loop, V8 и модулей Node.JS; 
        <br>- опыт работы с микросервисной архитектурой; 
        <br>- умение работать NoSQL БД: Mongo, Redis; 
        <br>- опыт работы с HighLoad проектами 
        <br> 
        <br><b>Обязанности:</b> 
        <br> 
        <br>- разработка серверной части приложения; 
        <br>- участие в проектировании архитектуры серверных компонентов; 
        <br>- code review 
        <br> 
        <br><b>Условия:</b> 
        <br> 
        <br>- оформление по ТК РФ, гибкий график работы 
        <br>- Белую" заработную плату + полугодовые бонусы 
        <br>- ДМС со стоматологией, страхование жизни после испытательного срока 
        <br>- обучение и посещение профильных конференций 
        <br>- корпоративную мобильную связь 
        <br>- просторный офис в центре Казани. 
        <br> 
        <br>Откликнуться: <a href="https://t.me/askamasaska" rel="nofollow">@askamasaska</a> 
        <br> 
        <br><a rel="nofollow">#nodejs</a> 
        <br> 
        <br>-------- 
        <br><i><b>⚠️</b></i> Вакансии закрываются очень быстро. Заглядывайте в <a href="https://t.me/xCareers" rel="nofollow">@xCareers</a> ежедневно, чтобы успеть откликаться!    <span>4.1K</span><span> views</span><span><a href="https://t.me/xCareers/1510" rel="nofollow">15:00</a></span>      <a href="https://xcareers.me/job/" rel="nofollow"><span><i><b>🛎️</b></i> Разместить вакансию</span></a>   <a href="https://t.me/xCareersChat" rel="nofollow"><span><i><b>🗂</b></i> Разместить резюме</span></a>        <a href="https://t.me/xCareers" rel="nofollow"><i><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a>   <i>         </i>  <a href="https://t.me/xCareers" rel="nofollow"><span>xCareers: Digital Jobs</span></a>   <b>MIDDLE/ SENIOR JAVA DEVELOPER</b> 
        <br> 
        <br>Формат: <a rel="nofollow">#офис</a> 
        <br>Локация: <a rel="nofollow">#казань</a>, <a rel="nofollow">#россия</a> 
        <br>Занятость: Полная 
        <br>Опыт: от 3 лет 
        <br>Зарплата: От 150 000 до 220 000 рублей 
        <br>Компания: Stream (ПАО МТС) 
        <br>Отрасль: разработка ПО 
        <br> 
        <br>Мы создаём приложения для компаний группы МТС, постоянно развиваем текущие проекты и запускаем новые. Приглашаем в команду опытного Java разработчика для участия в проекте Smart Wearables! О проекте: Мы создаем платформу для пользователей носимых устройств. Разрабатываем как software, так и hardware. 
        <br> 
        <br><b>Требования:</b> 
        <br> 
        <br>- навыки и опыт работы со стеком: Java 8, Spring Framework, Hibernate, PostgreSQL и Kafka (желательно); 
        <br>- умение и желание писать unit тесты; 
        <br>- опыт участия в enterprise проектах. 
        <br> 
        <br><b>Обязанности:</b> 
        <br> 
        <br>- участие в проектировании архитектуры бэкенда; 
        <br>- разработке серверной части продукта; 
        <br>- проведение code review; 
        <br>- участие в оптимизации технического стека и процессов в компании. 
        <br> 
        <br><b>Условия:</b> 
        <br> 
        <br>- оформление по ТК РФ, гибкий график работы 
        <br>- Белую" заработную плату + полугодовые бонусы 
        <br>- ДМС со стоматологией, страхование жизни после испытательного срока 
        <br>- обучение и посещение профильных конференций 
        <br>- корпоративную мобильную связь 
        <br>- просторный офис в центре Казани. 
        <br> 
        <br>Откликнуться: <a href="https://t.me/askamasaska" rel="nofollow">@askamasaska</a> 
        <br> 
        <br><a rel="nofollow">#java</a> 
        <br> 
        <br>-------- 
        <br><i><b>⚠️</b></i> Вакансии закрываются очень быстро. Заглядывайте в <a href="https://t.me/xCareers" rel="nofollow">@xCareers</a> ежедневно, чтобы успеть откликаться!    <span>1.5K</span><span> views</span><span><a href="https://t.me/xCareers/1511" rel="nofollow">07:00</a></span>      <a href="https://xcareers.me/job/" rel="nofollow"><span><i><b>🛎️</b></i> Разместить вакансию</span></a>   <a href="https://t.me/xCareersChat" rel="nofollow"><span><i><b>🗂</b></i> Разместить резюме</span></a>
    """.trimIndent()

        val elementHtml = """
            <div class="tgme_widget_message force_userpic js-widget_message" data-post="xCareers/1491" data-view="eyJjIjotMTE3OTkzMzcwNCwicCI6MTQ5MSwidCI6MTU4ODU4MTIyNywiaCI6IjNlNGQ4ZjQwOWJkYjZjZWQ2MyJ9"> 
             <div class="tgme_widget_message_user"> <a href="https://t.me/xCareers"><i class="tgme_widget_message_user_photo bgcolor5" data-content="x"><img src="https://cdn4.telesco.pe/file/OCFkr2VFhLtc-cg5BAl4luWTeT4pfAHHstWp1yBSDvCO-zKuogcheuZhqZULP2WQkTlPidZ-IQ35xRPAVIa2tBP2HDy5W37xCD3qXDdk6uqXlBexL4pCpy0SEC1i8md3T9DghYraoYf80t3SwnM8SulRPwp0S3a4thJPawC-m32jKAZOLaA6jz-3ZNl8c3g9wmrMcHRMJ2jutLw5hIgbf0RMc10Xp-df2quVKCXPzvNqe5BmgUF1rJh8EcUXj5GOgynOTSLxb-kukZqrQ83VkEwERSnYRkAjCbLnRevaqgf7gyUuOROxu7Kd-w4LBXsJ22WX4NY_3qwk8xls2BLsWg.jpg"></i></a> 
             </div> 
             <div class="tgme_widget_message_bubble"> <i class="tgme_widget_message_bubble_tail"> 
               <svg class="bubble_icon" width="9px" height="20px" viewbox="0 0 9 20"> <g fill="none"> 
                 <path class="background" fill="#ffffff" d="M8,1 L9,1 L9,20 L8,20 L8,18 C7.807,15.161 7.124,12.233 5.950,9.218 C5.046,6.893 3.504,4.733 1.325,2.738 L1.325,2.738 C0.917,2.365 0.89,1.732 1.263,1.325 C1.452,1.118 1.72,1 2,1 L8,1 Z"></path> 
                 <path class="border_1x" fill="#d7e3ec" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0 L9,0 L9,20 L7,20 L7,20 L7.002,18.068 C6.816,15.333 6.156,12.504 5.018,9.58 C4.172,7.406 2.72,5.371 0.649,3.475 C-0.165,2.729 -0.221,1.464 0.525,0.649 C0.904,0.236 1.439,0 2,0 Z"></path> 
                 <path class="border_2x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.5 L9,0.5 L9,20 L7.5,20 L7.5,20 L7.501,18.034 C7.312,15.247 6.64,12.369 5.484,9.399 C4.609,7.15 3.112,5.052 0.987,3.106 C0.376,2.547 0.334,1.598 0.894,0.987 C1.178,0.677 1.579,0.5 2,0.5 Z"></path> 
                 <path class="border_3x" d="M9,1 L2,1 C1.72,1 1.452,1.118 1.263,1.325 C0.89,1.732 0.917,2.365 1.325,2.738 C3.504,4.733 5.046,6.893 5.95,9.218 C7.124,12.233 7.807,15.161 8,18 L8,20 L9,20 L9,1 Z M2,0.667 L9,0.667 L9,20 L7.667,20 L7.667,20 L7.668,18.023 C7.477,15.218 6.802,12.324 5.64,9.338 C4.755,7.064 3.243,4.946 1.1,2.983 C0.557,2.486 0.52,1.643 1.017,1.1 C1.269,0.824 1.626,0.667 2,0.667 Z"></path> 
                </g> 
               </svg> </i> 
              <div class="tgme_widget_message_author"> <a class="tgme_widget_message_owner_name" href="https://t.me/xCareers"><span dir="auto">xCareers: Digital Jobs</span></a> 
              </div> 
              <div class="tgme_widget_message_text js-message_text" dir="auto"> <b>РАЗРАБОТЧИК IOS <i class="emoji" style="background-image:url('//telegram.org/img/emoji/40/F09F94A5.png')"><b>🔥</b></i></b> 
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
        val element = Jsoup.parse(elementHtml).body()
        val document = Jsoup.parse(xCareerHtml)

        val shortenElementText = """
            <b><a href="https://t.me/xCareers">xCareers: Digital Jobs</a></b>
            <b>РАЗРАБОТЧИК IOS <i><b>🔥</b></i></b> 
             
            Формат: <a rel="nofollow">#удаленка</a> (на время карантина) потом <a rel="nofollow">#офис</a> 
            Локация: <a rel="nofollow">#москва</a>, <a rel="nofollow">#санкт_петербург</a>, <a rel="nofollow">#россия</a> 
            Занятость: Полная 
            Опыт: от 3 до 6 лет 
            Зарплата: от 170 000 рублей в месяц 
            Отрасль: Банковские услуги 
             
            Российский банк топ-10 
             
            <b>Требования:</b> 
             
            <i><b>🔸</b></i> Опыт разработки мобильных приложений от двух лет 
            <i><b>🔸</b></i> Отличные знания платформы iOS и Swift 
            <i><b>🔸</b></i> Опыт применения архитектурных паттернов: MV*, VIPER 
            <i><b>🔸</b></i> Опыт разработки клиент-серверных приложений (HTTP(S), XML, JSON) 
            <i><b>🔸</b></i> Опыт написания unit-тестов 
            <i><b>🔸</b></i> Опыт работы с GIT 
             
            <b>Обязанности:</b> 
             
            <i><b>🔸</b></i> Разработка мобильного приложения для платформы iOS 
            <i><b>🔸</b></i> Два проекта на выбор: мобильный банк для физических лиц и для юридических лиц 
            <i><b>🔸</b></i> Разработка unit-тестов, рефакторинг, code review 
            <i><b>🔸</b></i> Формирование принципов командной разработки 
             
            <b>Условия:</b> 
             
            <i><b>🔸</b></i> Гибкое начало и окончание рабочего дня, свободный дресс-код 
            <i><b>🔸</b></i> Релокационный пакет (компенсация расходов на покупку билетов и аренду жилья в течение первых трех месяцев) 
            <i><b>🔸</b></i> Официальное оформление в соответствии с ТК РФ 
            <i><b>🔸</b></i> Конкурентный у...

        """.trimIndent()

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
    }
}
