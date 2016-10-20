package org.hsq.wjg.demo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by wujigang on 2016/10/12.
 */
public class DoubleColorBallUtil {
    private static final int RED_BALL_MAX = 33;
    private static final int BLUE_BALL_MAX = 16;

    public static String lottery() {
        Random random = new Random();
        List<Integer> reds = new ArrayList<Integer>(5);
        while (reds.size() < 5) {
            int luckyRed = random.nextInt(RED_BALL_MAX) + 1;
            if (!reds.contains(luckyRed)) {
                reds.add(luckyRed);
            }
        }
        Collections.sort(reds);

        int luckyBlue = random.nextInt(BLUE_BALL_MAX) + 1;

        StringBuilder sb = new StringBuilder();
        for (Integer red : reds) {
            sb.append(red).append(",");
        }
        sb.append(luckyBlue);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(lottery());
    }
}
