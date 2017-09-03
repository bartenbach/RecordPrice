package com.blakebartenbach.recordprice;

import com.blakebartenbach.recordprice.gson.MarketStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Class provides a way to get a GSON object back from a URL containing JSON
 * This particular class returns an object with GSON bindings at the root node "MarketStatus"
 */
public class JsonReader {

    /**
     * Returns a MarketStatus object from the JSON of the provided URL
     * @param url - the URL to the API returning the JSON response
     * @return MarketStatus - the MarketStatus object containing market statistics
     */
    public static MarketStatus getMarketStatus(String url) {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            Gson gson = new Gson();
            Type type = new TypeToken<MarketStatus>() {}.getType();
            return gson.fromJson(jsonText, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns all String data from provided Reader
     * @param rd - The reader to read from
     * @return String - A concatenated String of all data
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
