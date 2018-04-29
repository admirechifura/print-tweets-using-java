package za.co.admire.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by admirechifura on 28/04/2018.
 */
public class ProcessTextUsingBasicJava {

    public static final Logger log = LoggerFactory.getLogger(ProcessTextUsingBasicJava.class);

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the absolute path of the user file and Enter the absolute path of the tweet file seperated by comma");
        System.out.println("e.g /tmp/user.txt,/tmp/tweet.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = in.readLine()) != null) {
            String[] filePaths = input.split(",");
            if (filePaths.length == 2) {
                String userFileAbsolutePath = filePaths[0];
                String userFilename = userFileAbsolutePath.substring(userFileAbsolutePath.lastIndexOf('/') + 1);

                String tweetFileAbsolutePath = filePaths[1];
                String tweetFilename = tweetFileAbsolutePath.substring(userFileAbsolutePath.lastIndexOf('/') + 1);
                log.info("Received user input file:{}", userFilename);
                log.info("Received tweet input file:{}", tweetFilename);
                FileParserService fileParserService = new FileParserService();
                PrintAndFormatService printAndFormatService = new PrintAndFormatService();
                Map<String, HashSet<String>> followersAndUsers = fileParserService.parseUserFile(userFileAbsolutePath);
                Map<Double, LinkedList<String>> userAndTweets = fileParserService.parseTweetFile(tweetFileAbsolutePath);
                printAndFormatService.printToConsole(followersAndUsers, userAndTweets);
                log.info("<<<<<<<<<<<<<<< Program Ended Of Output Solution >>>>>>>>>>>>>>");
            } else {
                System.out.println("Input cannot be parsed");
            }

        }
    }

}
