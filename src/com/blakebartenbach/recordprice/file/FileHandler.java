package com.blakebartenbach.recordprice.file;

import com.blakebartenbach.recordprice.RecordHolder;
import com.blakebartenbach.recordprice.util.OsHelper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileHandler {


    private String recordFile;
    private String recordDir;


    public FileHandler() {
        this.recordFile = getRecordFile();
    }

    private String getRecordFile() {
        if (OsHelper.isWindows()) {
            recordDir = "../conf";
            return "../conf/market.records";
        } else if (OsHelper.isUnix()) {
            String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
            this.recordDir = absolutePath + File.separator + "conf";
            return absolutePath + File.separator + "conf/market.records";
        }
        System.out.println("FATAL: Failed to detect operating system!");
        return null;
    }


    public RecordHolder getRecords() {
        System.out.println(Paths.get(this.recordFile).toAbsolutePath());
        boolean created = createRecordsFile();
        if (created) {
            writeRecords(new RecordHolder(0,0,0));
        }
        try (BufferedReader br = Files.newBufferedReader(Paths.get(recordFile), Charset.forName("UTF-8"))) {
            String bitcoinRecordPrice = null;
            String litecoinRecordPrice = null;
            String ethereumRecordPrice = null;
            for (int i = 0; i < 3; i++) {
                switch(i) {
                    case 0:
                        bitcoinRecordPrice = br.readLine();
                        break;
                    case 1:
                        litecoinRecordPrice = br.readLine();
                        break;
                    case 2:
                        ethereumRecordPrice = br.readLine();
                        break;
                    default:
                        System.out.println("ERROR: Reached default in switch statement!");
                        break;
                }
            }
            return new RecordHolder(Double.valueOf(bitcoinRecordPrice), Double.valueOf(litecoinRecordPrice), Double.valueOf(ethereumRecordPrice));
        } catch (IOException e) {
            System.out.println("FATAL: Failed to parse records from records file!");
            e.printStackTrace();
        }
        return null; // if null, exit servlet
    }

    private boolean createRecordsFile() {
        File file = new File(recordFile);
        File dir = new File(recordDir);
        if (!file.exists()) {
            try {
                boolean createdDir = dir.mkdir();
                if (createdDir) {
                    boolean created = file.createNewFile();
                    if (created) {
                        System.out.println("Created new records file at " + file.getAbsolutePath());
                        return true;
                    }
                } else {
                    System.out.println("FATAL: Failed to create directory!");
                }

            } catch (IOException e) {
                System.out.println("FATAL: Failed to create records file!");
                e.printStackTrace();
            }
        }
        return false;
    }

    public void writeRecords(RecordHolder rh) {
        List<String> records = Arrays.asList(String.valueOf(rh.getBitcoinRecordPrice()),
                String.valueOf(rh.getLitecoinRecordPrice()),
                String.valueOf(rh.getEthereumRecordPrice()));
        Path recordPath = Paths.get(recordFile);
        try {
            Files.write(recordPath, records);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FATAL: Failed to write new record prices to file!");
            System.out.println("Market prices at the time of failure: " + records.toString());
        }
    }

}
