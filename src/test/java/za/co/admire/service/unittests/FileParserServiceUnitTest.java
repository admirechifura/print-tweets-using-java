package za.co.admire.service.unittests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import za.co.admire.service.FileParserService;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by admirechifura on 27/04/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class FileParserServiceUnitTest {

    private File file;

    @InjectMocks
    private FileParserService fileParserService;

    @Test
    public void when_parseUserFileIsCalledWithValidPathToUserFile_expect_userMapReturned() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("text/user.txt").getFile());
        Map<String, HashSet<String>> followersAndUsers = fileParserService.parseUserFile(file.getAbsolutePath());
        assertThat("The size of followers map is zero", followersAndUsers.size(), not(0));
    }

    @Test(expected = RuntimeException.class)
    public void when_parseUserFileIsCalledWithInvalidPathToUserFile_expect_FileNotFoundException() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("text/user.txt").getFile());
        fileParserService.parseUserFile(file.getAbsolutePath() + "i");
    }

    @Test
    public void when_parseTweetFileIsCalledWithValidPathToUserFile_expect_userMapReturned() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("text/tweet.txt").getFile());
        Map<Double, LinkedList<String>> usersAndTweets = fileParserService.parseTweetFile(file.getAbsolutePath());
        assertThat("The size of usersAndTweets map is zero", usersAndTweets.size(), not(0));
    }

    @Test(expected = RuntimeException.class)
    public void when_parseTweetFileIsCalledWithInvalidPathToUserFile_expect_FileNotFoundException() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("text/tweet.txt").getFile());
        fileParserService.parseTweetFile(file.getAbsolutePath() + "k");
    }

}
