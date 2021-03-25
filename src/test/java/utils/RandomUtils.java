package utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static String emailDomain = "@qa.guru";

    public static String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }

        return result.toString();
    }

    public static String getRandomMessage(int min, int max) {
        String[] words = {"and", "or", "but", "because",
                "red", "white", "Jane", "John", "Bobby",
                "man", "woman", "fish", "elephant", "unicorn",
                "a", "the", "every", "some", "any", "all",
                "big", "tiny", "pretty", "bald", "small",
                "runs", "jumps", "talks", "sleeps", "walks",
                "loves", "hates", "sees", "knows", "looks for", "finds",
                ", ", ", ", ", ", ". ", ". "};

        StringBuilder message = new StringBuilder();
        int messageLength = getRandomInt(min, max);
        while (message.length() < messageLength) {
            int wordIndex = getRandomInt(0, words.length -1);
            message.append(words[wordIndex] + " ");
        }

        String readyMessage = StringUtils.capitalize(message.toString())
                .replace("  ", " ")
                .replace(" ,", ",")
                .replace(" .", ".").trim();

        return readyMessage;
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public static String getRandomPhone() {
        return getRandomLong(11111111111111111L, 99999999999999999L) + "";
    }

    public static String getRandomPhone(String code) {
        return code + getRandomLong(11111111111111111L, 99999999999999999L);
    }

    public static String getDifficultRandomPhone(String code) {
        return code + " (" +getRandomLong(111L, 999L) + ") " + getRandomLong(11111L, 999999L) + "-" + getRandomLong(111L, 999999L);
    }

    public static Long getRandomLong(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String getRandomEmail() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        return getRandomString(5) + timestamp + emailDomain;
    }

    public static String getRandomGender() {
        String[] genderList = {"Male", "Female", "Other"};
        String gender = genderList[(int) Math.floor(Math.random() * genderList.length)];
        return  gender;
    }

    public static String getRandomMonth() {
        String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String month = monthList[(int) Math.floor(Math.random() * monthList.length)];
        return  month;
    }

    public static String getRandomSubject() {
        String[] subjectList = {"English", "Chemistry", "Commerce"};
        String subject = subjectList[(int) Math.floor(Math.random() * subjectList.length)];
        return  subject;
    }

    public static String getRandomHobbie() {
        String[] hobbiesList = {"Music", "Reading", "Sports"};
        String hobbie = hobbiesList[(int) Math.floor(Math.random() * hobbiesList.length)];
        return  hobbie;
    }
}