/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PricecheckQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Denver
 */
public final class PricecheckLookup {

    //-------------------
    //2. Get price from pricecheck
    public static String getPriceFromPriceCheck(String productName) {
        String replaceTemp = productName.replaceAll(" ", "+");
        return parseHTML(queryURL("http://www.pricecheck.co.za/search?search=" + replaceTemp));
    }

    private static String queryURL(String urlWithItem) {
        try {
            String inputLine;
            StringBuilder response = new StringBuilder();
            URL obj = new URL(urlWithItem);
            int responseCode = 0;
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(PricecheckLookup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PricecheckLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String parseHTML(String returnedResult) {
        Document doc = Jsoup.parse(returnedResult);
        Elements list_items = doc.getElementsByClass("txt-price");
        Elements list_items2 = doc.getElementsByClass("square-block wallpaper");
        System.out.println("----" + list_items.get(0).text());
        return list_items.get(0).text();
    }

}
