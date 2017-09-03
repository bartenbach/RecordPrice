package com.blakebartenbach.recordprice;

import com.blakebartenbach.recordprice.gson.Cryptocurrency;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Class provides a way to get a GSON object back from a URL containing JSON
 */
public class JsonReader {

    /**
     * Returns a Cryptocurrency object from the JSON of the provided URL
     * @param url - the URL to the API returning the JSON response
     * @return Cryptocurrency - the Cryptocurrency object containing market statistics
     */
    public static Cryptocurrency getMarketStatus(String url, Class cryptocurrency) {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);
            Gson gson = new Gson();
            return gson.fromJson(jsonText, (Type) cryptocurrency);
        } catch (IOException e) {
            System.out.println("FATAL: Could not get market status!");
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
