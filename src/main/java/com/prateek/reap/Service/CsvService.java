package com.prateek.reap.Service;

import com.prateek.reap.Entity.BadgesGiven;
import com.prateek.reap.Entity.CsvDto;
import com.prateek.reap.Repository.BadgeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvService {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private StarService starService;

    @Autowired
    private BadgeRepository badgeRepository;



    public CsvDto getCsv(PrintWriter writer, Iterable<BadgesGiven> badgesGivens) {
        try {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Receiver Name", "Sender Name"
                    , "Receiver Email", "Sender Email", "Star", "Comment", "Date"));

            for (BadgesGiven badgesGiven : badgesGivens) {


                List<String> data = Arrays.asList(
                        badgesGiven.getReceiver().getFirstName(),
                        badgesGiven.getGiver().getFirstName(),
                        badgesGiven.getReceiver().getEmail(),
                        badgesGiven.getGiver().getEmail(),
                        badgesGiven.getStar().getName(),
                        badgesGiven.getComment(),
                        badgesGiven.getUpdatedAt().toString()

                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
