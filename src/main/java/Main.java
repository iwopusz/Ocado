import org.json.simple.parser.ParseException;
import service.FilesLoader;
import service.ScheduleMaker;

import java.io.IOException;

public class Main {

    // My program is starting with getFiles() method and then four different picking schedules makers are initialized.
    // I was experimenting with different priorities for orders.
    // I'm not really sure if I did this right, because my first method (shortest picking time of orders)
    // brings the best results - both completed orders amount and total value of completed orders... ;(
    // I've spent nearly 20 hours on this but unfortunately there isn't even one test written and I'm very sorry...
    // However, I'm satisfied with this application speed - making 4 different schedules, even without multithreading
    // takes a second.
    // PC specs: Intel i7 6700K @ 4.00GHz, 16GB of RAM, M2 SSD.

    public static void main(String[] args) throws IOException, ParseException {
        try {
            FilesLoader.getFiles();
            ScheduleMaker.makePickingSchedulePrioritizedByShortestPickingTime();
            ScheduleMaker.makePickingSchedulePrioritizedByOrderValue();
            ScheduleMaker.makePickingSchedulePrioritizedByPickingTimeDeadline();
            ScheduleMaker.makePickingSchedulePrioritizedByLongestPickingTime();
            System.out.println("##########################################");
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
    }

}
