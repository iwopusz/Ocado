package service;

import dto.Orders;
import dto.Pickers;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dto.Pickers.getPickingEndTime;
import static dto.Pickers.pickingStartTime;

public class ScheduleMaker {

    // So here's the heart of this app. And it's beating fast! Maybe a little arrhythmic, but fast. ;)
    // 4 methods for building schedules and 1 major method to supply them.

    public static void makePickingSchedulePrioritizedByShortestPickingTime() throws IOException, ParseException {
        OrdersSorter.sortOrdersByShortestPickingTime();
        makeSchedule();
    }

    public static void makePickingSchedulePrioritizedByLongestPickingTime() throws IOException, ParseException {
        OrdersSorter.sortOrdersByLongestPickingTime();
        makeSchedule();
    }

    public static void makePickingSchedulePrioritizedByOrderValue() throws IOException, ParseException {
        OrdersSorter.sortOrdersByOrderValue();
        makeSchedule();
    }

    public static void makePickingSchedulePrioritizedByPickingTimeDeadline() throws IOException, ParseException {
        OrdersSorter.sortOrdersByPickingTimeDeadline();
        makeSchedule();
    }

    // What I'm using is arrays of orders and pickers, completed orders count, completed orders value and 2 conditions.
    // Conditions check whether picker will finish picking order before or on deadline.
    // After checking conditions pickerID, orderId and time of picking start is printed.
    // Then order picking time is subtracted from picker's available time (actually by adding picking time to available
    // time, which is a copy of picking start time, so available time is something like "9:00" at the beginning).
    // Next, completed orders counter and total value of completed orders are updated and order removed from list.
    // After that program is checking whether continue to another picker or if reached last picker, return to first.
    // Lastly, if none of pickers have time to pick remaining orders, summary regarding orders is printed.
    // And those 3 lines of code are something that I'm least proud of. I had no time left to try to make this work
    // any other way, so this inelegant solution "resets" pickers available time, getting them ready for next schedule.

    public static void makeSchedule() {
        List<Orders> ordersToDo = new ArrayList<>(Orders.orders);
        int completedOrders = 0;
        BigDecimal completedOrdersValue = new BigDecimal(0);
        List<Pickers> pickersAvailable = new ArrayList<>(Pickers.pickers);
        System.out.println("##########################################");
        int i = 0;
        while (true) {
            boolean Condition1 = pickersAvailable.get(i).getPickingTimeAvailable().plus(ordersToDo.get(0).
                    getPickingTime()).isBefore(getPickingEndTime());
            boolean Condition2 = pickersAvailable.get(i).getPickingTimeAvailable().plus(ordersToDo.get(0).
                    getPickingTime()).equals(getPickingEndTime());
            if (Condition1 || Condition2) {
                System.out.println(pickersAvailable.get(i).getPickerId() + " " + ordersToDo.get(0).
                        getOrderId() + " " + pickersAvailable.get(i).getPickingTimeAvailable());
                pickersAvailable.get(i).setPickingTimeAvailable(pickersAvailable.get(i).getPickingTimeAvailable().
                        plus(ordersToDo.get(0).getPickingTime()));
                completedOrders += 1;
                completedOrdersValue = completedOrdersValue.add(ordersToDo.get(0).getOrderValue());
                ordersToDo.remove(0);
                if (i + 1 != pickersAvailable.size()) {
                    i += 1;
                } else {
                    i = 0;
                }
            } else {
                System.out.println('\n' + "Completed orders: " + completedOrders);
                System.out.println("Total value of completed orders: " + completedOrdersValue);
                for (Pickers value : pickersAvailable) {
                    value.setPickingTimeAvailable(pickingStartTime);
                }
                return;
            }
        }
    }
}
