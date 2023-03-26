package service;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class FilesLoader {

    // I'm using Scanner for user's absolute path inputs and assigning them to String variables.
    // Then I'm getting data from both .json files using two different methods.
    // I've tried to keep it clean, but as You can see, code is repeated.

    public static void getFiles() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input absolute path for store.json file...");
        String store = scanner.nextLine();
        FilesService.convertStoreFile(store);
        System.out.println("Input absolute path for orders.json file...");
        String orders = scanner.nextLine();
        FilesService.convertOrdersFile(orders);
    }

}
