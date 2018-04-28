package za.co.admire.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by admirechifura on 27/04/2018.
 */
@Service
public class PrintAndFormatService {

    public static final Logger log = LoggerFactory.getLogger(PrintAndFormatService.class);

    public void printToConsole(final Map<String, HashSet<String>> followersAndUsers, final Map<Double, LinkedList<String>> userAndTweetsDoubleLinkedListMap) {
        log.info("Printing followersAndUsers hashSet in a map of size:{}",followersAndUsers.size());
        log.info("Printing userAndTweetsDoubleLinkedListMap linkedList in a map of size:{}",userAndTweetsDoubleLinkedListMap.size());
        log.info("<<<<<<<<<<<<<<< Program Start Of Output Solution >>>>>>>>>>>>>>");
        if (!followersAndUsers.entrySet().isEmpty()) {
            Set<String> users = new TreeSet<>();  //Using Tree set which sorts according to natural ordering of its elements
            followersAndUsers.keySet().forEach(followerKey -> {
                HashSet<String> userSet = followersAndUsers.get(followerKey);
                userSet.forEach(user -> users.add(user)); //Add all users to users tree set
                users.add(followerKey); //Add followers to users tree set
            });
            users.forEach(userSet -> {
                System.out.println(userSet);  //Print user
                userAndTweetsDoubleLinkedListMap.keySet().forEach(aDouble -> {
                    LinkedList<String> userAndTweet = userAndTweetsDoubleLinkedListMap.get(aDouble);
                    if ((followersAndUsers.get(userSet) != null
                            && followersAndUsers.get(userSet).contains(userAndTweet.getFirst()))
                            || userSet.equalsIgnoreCase(userAndTweet.getFirst())) {
                        System.out.printf("\t @%s: %s \n", userAndTweet.getFirst(), userAndTweet.getLast());  //Print tweets
                    }
                });
            });
        }
    }
}
