package Utils;

import java.math.BigInteger;
import java.util.Random;

public final class Utilities {

    public static String randomGenerator() {
        int Min = 10000;
        int Max = 99999;
        int random = (int) (Min + Math.random() * (Max - Min));
        return String.valueOf(random);
    }
}
