package org.fga.paradigmas.utils;

public class Utils {

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Sleep finalizado inesperadamente. Mensagem: " + e.getMessage());
        }
    }

    public static int getIntRandom(int max) {
        return (int) (Math.random() * max);
    }

    public static int calcDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
