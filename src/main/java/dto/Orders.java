package dto;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Orders {

    // I'm not sure it's accurate to name those classes DTO's, models maybe?

    public String orderId;
    public BigDecimal orderValue;
    public Duration pickingTime;
    public LocalTime completeBy;
    public static List<Orders> orders = new ArrayList<>();

    public Orders(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public Duration getPickingTime() {
        return pickingTime;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

}
