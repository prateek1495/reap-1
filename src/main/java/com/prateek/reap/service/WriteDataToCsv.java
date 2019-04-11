package com.prateek.reap.service;


import com.prateek.reap.entity.BadgesGiven;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class WriteDataToCsv {

    public static void writeObjectToCSV(PrintWriter writer, List<BadgesGiven> badgesGiven) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Receiver's FirstName", "Giver's FirstName", "Comment", "CreatedAt", "UpdatedAt"));
        ) {
            for (BadgesGiven badgesGiven1 : badgesGiven) {
                List<String> data = Arrays.asList(
                        badgesGiven1.getId().toString(),
                        badgesGiven1.getReceiver().getFirstName(),
                        badgesGiven1.getGiver().getFirstName(),
                        badgesGiven1.getComment(),
                        badgesGiven1.getCreatedAt().toString(),
                        badgesGiven1.getUpdatedAt().toString()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }
}