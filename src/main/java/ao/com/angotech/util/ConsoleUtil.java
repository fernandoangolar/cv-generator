package ao.com.angotech.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static String prompt(String mensagem) {
        System.out.print(mensagem + ": ");
        return scanner.nextLine();
    }

    public static List<String> promptList(String mensagem) {
        System.out.print(mensagem + " (separe por vírgula): ");
        String input = scanner.nextLine();
        return Arrays.asList(input.split(","));
    }
}
