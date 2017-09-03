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


    public void init() {
        fileHandler = new FileHandler();
        recordHolder = fileHandler.getRecords();
        if (recordHolder == null) {
            System.out.println("FATAL: RecordHolder failed to initialize!");
            System.exit(1);
        }
        Thread pollingThread = new Thread(new MarketPollingThread(recordHolder));
        pollingThread.setDaemon(true);
        pollingThread.start();
    }

    public void destroy() {
        if (recordHolder != null){
            fileHandler.writeRecords(recordHolder);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<h1>Bitcoin Record Price: $" + PriceFormatter.formatPrice(recordHolder.getBitcoinRecordPrice()) + "</h1>");
        out.print("<h1>Litecoin Record Price: $" + PriceFormatter.formatPrice(recordHolder.getLitecoinRecordPrice()) + "</h1>");
        out.print("<h1>Ethereum Record Price: $" + PriceFormatter.formatPrice(recordHolder.getEthereumRecordPrice()) + "</h1>");
    }
}
