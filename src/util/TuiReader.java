package util;

import exception.CIllegalArgumentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TuiReader {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private TuiReader() {}
    public static int choice(int start, int end) {
        int result;
        try {
            result = Integer.parseInt(br.readLine());
            if (result < start || result > end) {
                System.out.println("범위 내의 숫자를 입력해주세요.");
                return choice(start, end);
            }
        } catch (Exception e) {
            System.out.println("정확히 입력해주세요.");
            return choice(start, end);
        }
        return result;
    }
    public static String readInput(String errorMessage) {
        try {
            String input = br.readLine();
            if(input == null || input.isEmpty()) {
                throw new CIllegalArgumentException(errorMessage);
            }
            return input;
        } catch (IOException e) {
            throw new CIllegalArgumentException(errorMessage);
        }
    }
    public static String readInputCorrect() {
        try {
            String input = br.readLine();
            if(input == null || input.isEmpty()) {
                throw new RuntimeException();
            }
            return input;
        } catch (Exception e) {
            System.out.println("정확히 입력해주세요.");
            return readInputCorrect();
        }
    }
}
