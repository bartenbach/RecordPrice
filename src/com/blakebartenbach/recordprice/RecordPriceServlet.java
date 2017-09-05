package com.blakebartenbach.recordprice;

import com.blakebartenbach.recordprice.file.FileHandler;
import com.blakebartenbach.recordprice.thread.MarketPollingThread;
import com.blakebartenbach.recordprice.util.PriceFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RecordPriceServlet", urlPatterns = { "/price" })
public class RecordPriceServlet extends HttpServlet {


    private FileHandler fileHandler;
    private RecordHolder recordHolder;
    private MarketPollingThread pollingThread;


    public void init() {
        fileHandler = new FileHandler();
        recordHolder = fileHandler.getRecords();
        if (recordHolder == null) {
            System.out.println("FATAL: RecordHolder failed to initialize!");
            System.exit(1);
        }
        pollingThread = new MarketPollingThread(recordHolder);
        Thread marketPollingThread = new Thread(pollingThread);
        marketPollingThread.setDaemon(true);
        marketPollingThread.start();
    }

    public void destroy() {
        if (recordHolder != null){
            fileHandler.writeRecords(recordHolder);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        pw.println("{ \"records\": {");
        pw.println("\"btc\": " + String.valueOf(PriceFormatter.formatPrice(recordHolder.getBitcoinRecordPrice()) + ","));
        pw.println("\"ltc\": " + String.valueOf(PriceFormatter.formatPrice(recordHolder.getLitecoinRecordPrice()) + ","));
        pw.println("\"eth\": " + String.valueOf(PriceFormatter.formatPrice(recordHolder.getEthereumRecordPrice()) + " },"));
        pw.println("\"current\":{");
        pw.println("\"btc\": " + String.valueOf(PriceFormatter.formatPrice(pollingThread.getBitcoinCurrentPrice())) + ",");
        pw.println("\"ltc\": " + String.valueOf(PriceFormatter.formatPrice(pollingThread.getLitecoinCurrentPrice()) + ","));
        pw.println("\"eth\": " + String.valueOf(PriceFormatter.formatPrice(pollingThread.getEthereumCurrentPrice()) + " }"));
        pw.println("}");
        pw.close();
    }
}
