package service;

import dto.Orders;

import java.util.Comparator;

import static dto.Orders.orders;

public class OrdersSorter {

    // Here I've defined my 4 sorting methods.
    // That's where I'm confused because sorting by order value (highest first) turns out to be even worse
    // than sorting orders by deadline, according to total orders value at the end of picking. Weird.
    // My first method is the most effective one - most orders picked and highest total value.
    // I'm pretty sure that this was supposed to be more sophisticated, maybe calculating order value to picking time
    // profitability ratio, but in given time, that's what I've delivered.

    public static void sortOrdersByShortestPickingTime() {
        orders.sort(Comparator.comparing(Orders::getPickingTime));
    }

    public static void sortOrdersByLongestPickingTime() {
        orders.sort(Comparator.comparing(Orders::getPickingTime).reversed());
    }

    public static void sortOrdersByOrderValue() {
        orders.sort(Comparator.comparing(Orders::getOrderValue).reversed());
    }

    public static void sortOrdersByPickingTimeDeadline() {
        orders.sort(Comparator.comparing(Orders::getCompleteBy));
    }

}
