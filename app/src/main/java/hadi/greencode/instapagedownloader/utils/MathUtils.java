package hadi.greencode.instapagedownloader.utils;

public class MathUtils {

    public static int calculateHalf(int inputMax) {
        int halfSize = 2;
        if (inputMax != 1) {
            try {
                halfSize = inputMax / 2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return halfSize;
    }
}
