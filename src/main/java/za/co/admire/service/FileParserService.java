package za.co.admire.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by admirechifura on 27/04/2018.
 */
@Service
public class FileParserService {

    public static final Logger log = LoggerFactory.getLogger(FileParserService.class);

    private static final String FOLLOWS = "follows";
    private static final String GREATERTHAN = ">";

    public Map<String, HashSet<String>> parseUserFile(final String fileAbsolutePath) {
        log.info("Parsing user file {}", fileAbsolutePath);
        File inputFile = new File(fileAbsolutePath);
        Map<String, HashSet<String>> followersAndUsers = new TreeMap<>();

        try (InputStream userFileInputStream = new FileInputStream(inputFile)) {
            Scanner scanner = new Scanner(userFileInputStream);
            while (scanner.hasNext()) {
                StringBuilder readLine = new StringBuilder(scanner.nextLine());
                Map<String, HashSet<String>> followerAndUsers;
                if (readLine.toString().contains(FOLLOWS)) {
                    String[] inputValues = readLine.toString().split(FOLLOWS);
                    followerAndUsers = extractUsersToHashSet(inputValues);
                    String followerKey = followerAndUsers.keySet().stream().findFirst().get();
                    if (followersAndUsers.containsKey(followerKey)) {
                        HashSet<String> unionSet = new HashSet<>(followersAndUsers.get(followerKey));
                        unionSet.addAll(followerAndUsers.get(followerKey));
                        followersAndUsers.replace(followerKey, unionSet);
                    } else {
                        followersAndUsers.put(followerKey, followerAndUsers.get(followerKey));
                    }
                }
            }
        } catch (IOException fe) {
            throw new RuntimeException(fe);
        }
        return followersAndUsers;
    }

    public Map<Double, LinkedList<String>> parseTweetFile(final String fileAbsolutePath) {
        log.info("Parsing tweet file {}", fileAbsolutePath);
        File inputFile = new File(fileAbsolutePath);
        Map<Double, LinkedList<String>> usersAndTweets = new LinkedHashMap<>();
        try (InputStream userFileInputStream = new FileInputStream(inputFile)) {
            Scanner scanner = new Scanner(userFileInputStream);
            while (scanner.hasNext()) {
                StringBuilder readLine = new StringBuilder(scanner.nextLine());
                if (readLine.toString().contains(GREATERTHAN)) {
                    String[] inputValues = readLine.toString().split(GREATERTHAN);
                    LinkedList<String> userAndTweetLinkedList = new LinkedList<>();
                    userAndTweetLinkedList.addFirst(inputValues[0]);  //Assuming there is only one tweet per line.
                    userAndTweetLinkedList.addLast(inputValues[1]);   //Assuming there is only one tweet per line.
                    usersAndTweets.put(Math.random(), userAndTweetLinkedList);
                }
            }
        } catch (IOException fe) {
            throw new RuntimeException(fe);
        }
        return usersAndTweets;
    }

    public Map<String, HashSet<String>> extractUsersToHashSet(final String[] inputValues) {
        log.trace("Adding {} identified users to HashSet", inputValues.length);
        Map<String, HashSet<String>> followerAndUsersSet = new HashMap<>();
        HashSet<String> users = new HashSet<>();
        if (!Arrays.asList(inputValues).isEmpty()) {
            String followerKey = inputValues[0].trim();
            String[] trimmedInputValues = new String[inputValues.length];
            for (int i = 0; i < inputValues.length; i++) {
                trimmedInputValues[i] = inputValues[i].trim();
            }
            Arrays.asList(trimmedInputValues).stream()
                    .filter(s -> !s.equalsIgnoreCase(followerKey)) //Assumption e.g Ward does not have a follower called Ward
                    .forEach(s -> {
                        String[] followed = s.split(",");
                        String[] trimmedFollowed = new String[followed.length];
                        for (int k = 0; k < followed.length; k++) {
                            trimmedFollowed[k] = followed[k].trim();
                        }
                        users.addAll(Arrays.asList(trimmedFollowed));
                    });
            followerAndUsersSet.put(followerKey, users);
        }
        return followerAndUsersSet;
    }
}
