package service;

import dto.Orders;
import dto.Pickers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import static dto.Orders.orders;
import static dto.Pickers.pickers;

public class FilesService {

    // This part is a little messy but still it's great compared to ScheduleMaker.class ;)
    // So I'm using Google's json-simple to parse files and get what's needed.
    // This probably could have been done better (yes, I'm looking at you orderValue!) but I did what I could.
    // In first method I'm getting array of pickers and adding them to pickers array with their ID and available time
    // which is a copy of pickingStartTime. I've added it later to help me solve problem with pickers.
    // In second method I'm getting order details and at the end I'm adding every order to orders array.
    // DRY, but I do.

    public static void convertStoreFile(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject store = (JSONObject) parser.parse(new FileReader(filePath));
        JSONArray pickersArray = (JSONArray) store.get("pickers");
        String pickingTimeAvailableTemp = (String) store.get("pickingStartTime");
        LocalTime pickingTimeAvailable = LocalTime.parse(pickingTimeAvailableTemp);
        for (Object p : pickersArray) {
            String pickerId = (String) p;
            pickers.add(new Pickers(pickerId, pickingTimeAvailable));
        }
        Pickers.pickingStartTime = LocalTime.parse(pickingTimeAvailableTemp);
        String pickingEndTimeTemp = (String) store.get("pickingEndTime");
        Pickers.pickingEndTime = LocalTime.parse(pickingEndTimeTemp);
    }

    public static void convertOrdersFile(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray ordersArray = (JSONArray) parser.parse(new FileReader(filePath));
        for (Object o : ordersArray) {
            JSONObject order = (JSONObject) o;
            String orderId = (String) order.get("orderId");
            String orderValueString = (String) order.get("orderValue");
            double orderValueDouble = Double.parseDouble(orderValueString);
            BigDecimal orderValue = BigDecimal.valueOf(orderValueDouble);
            String pickingTimeTemp = (String) order.get("pickingTime");
            Duration pickingTime = Duration.parse(pickingTimeTemp);
            String completeByString = (String) order.get("completeBy");
            LocalTime completeBy = LocalTime.parse(completeByString);
            orders.add(new Orders(orderId, orderValue, pickingTime, completeBy));
        }
    }

}

