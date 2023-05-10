package util;

import exception.CIllegalArgumentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TuiReader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private TuiReader() {
    }
    public static int choice() {
        try {
            int result = Integer.parseInt(br.readLine());
            return result;
        } catch (Exception e) {
            System.out.println("정확히 입력해주세요.");
            return choice();
        }
    }
    public static String readInput(String errorMessage) {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new CIllegalArgumentException(errorMessage);
        }
    }
}
