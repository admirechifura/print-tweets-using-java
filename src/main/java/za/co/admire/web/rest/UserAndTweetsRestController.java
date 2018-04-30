package za.co.admire.web.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.admire.service.FileParserService;
import za.co.admire.service.PrintAndFormatService;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by admirechifura on 30/04/2018.
 */
@RestController
@RequestMapping(value = "/printUserAndTweets")
public class UserAndTweetsRestController {

    public static final Logger log = LoggerFactory.getLogger(UserAndTweetsRestController.class);

    private FileParserService fileParserService;
    private PrintAndFormatService printAndFormatService;

    @Autowired
    public UserAndTweetsRestController(final FileParserService fileParserService, final PrintAndFormatService printAndFormatService) {
        this.fileParserService = fileParserService;
        this.printAndFormatService = printAndFormatService;
    }

    @RequestMapping(value = "/input", method = GET)
    public String printUserAndTweets(@RequestParam("userFileAbsolutePath") final String userFileAbsolutePath,
                                     @RequestParam("tweetFileAbsolutePath") final String tweetFileAbsolutePath) {
        if (!StringUtils.isEmpty(userFileAbsolutePath)
                && !StringUtils.isEmpty(tweetFileAbsolutePath)) {
            String userFilename = userFileAbsolutePath.substring(userFileAbsolutePath.lastIndexOf('/') + 1);

            String tweetFilename = tweetFileAbsolutePath.substring(userFileAbsolutePath.lastIndexOf('/') + 1);
            log.info("Received user input file:{}", userFilename);
            log.info("Received tweet input file:{}", tweetFilename);
            Map<String, HashSet<String>> followersAndUsers = fileParserService.parseUserFile(userFileAbsolutePath);
            Map<Double, LinkedList<String>> userAndTweets = fileParserService.parseTweetFile(tweetFileAbsolutePath);
            printAndFormatService.printToConsole(followersAndUsers, userAndTweets);
            log.info("<<<<<<<<<<<<<<< Program Ended Of Output Solution >>>>>>>>>>>>>>");

        } else {
            return "All parameters: userAbsolutePath and tweetAbsolutePath are mandatory.Please try again";
        }
        return "Information successfully printed, check logs";
    }
}
