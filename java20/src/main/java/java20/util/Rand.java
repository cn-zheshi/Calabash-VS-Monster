package java20.util;


import java.util.Random;

/**
 * @author hwd
 * @date 2020-12-27
 **/
public class Rand {

    private static Random rand = new Random();

    public static String randomName(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            char cur = (char) (rand.nextInt(26) + 'a');
            int flag = rand.nextInt(2);
            if (flag == 1) cur = (char) (cur - 'a' + 'A');
            builder.insert(rand.nextInt(i + 1), cur);
        }
        return builder.toString();
    }

    public static int randNum(int bound) {
        return rand.nextInt(bound);
    }
}