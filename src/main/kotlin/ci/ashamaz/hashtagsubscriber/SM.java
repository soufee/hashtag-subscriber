package ci.ashamaz.hashtagsubscriber;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SM {
    public static void main(String[] args) {
        sendToTelegram();
    }
        public static void sendToTelegram() {
            String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

            //Add Telegram token (given Token is fake)
            String apiToken = "1219969568:AAEFv4mVOMK_Ki9hKEiYx1znzYWzvNZ0y-c";

            //Add chatId (given chatId is fake)
            String chatId = "87927916";
            String text = "Hello world!";

            urlString = String.format(urlString, apiToken, chatId, text);

            try {
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();
                InputStream is = new BufferedInputStream(conn.getInputStream());

                //getting text, we can set it to any TextView
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                StringBuilder sb = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                //You can set this String to any TextView
                String response = sb.toString();
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
