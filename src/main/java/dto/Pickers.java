package dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Pickers {

    // I'm not sure it's accurate to name those classes DTO's, models maybe?

    public String pickerId;
    public LocalTime pickingTimeAvailable;
    public static LocalTime pickingStartTime;
    public static LocalTime pickingEndTime;
    public static List<Pickers> pickers = new ArrayList<>();

    public Pickers(String pickerId, LocalTime pickingTimeAvailable) {
        this.pickerId = pickerId;
        this.pickingTimeAvailable = pickingTimeAvailable;
    }

    public static LocalTime getPickingEndTime() {
        return pickingEndTime;
    }

    public String getPickerId() {
        return pickerId;
    }

    public LocalTime getPickingTimeAvailable() {
        return pickingTimeAvailable;
    }

    public void setPickingTimeAvailable(LocalTime pickingTimeAvailable) {
        this.pickingTimeAvailable = pickingTimeAvailable;
    }

}
