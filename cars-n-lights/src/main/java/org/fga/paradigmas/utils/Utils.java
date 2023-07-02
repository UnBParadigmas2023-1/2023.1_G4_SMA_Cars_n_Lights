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
        return (int) (Math.random() * 10);
    }

}
