import org.json.simple.parser.ParseException;
import service.FilesLoader;
import service.ScheduleMaker;

import java.io.IOException;

public class Main {

    // My program is starting with getFiles() method and then schedule maker method is initialized.
    // I was experimenting with different priorities for orders and so, four different sorting methods and
    // schedule makers were made. I'm using only one, with orders sorted by completion time deadline, which is the most
    // efficient according to total number of orders completed and total value of those.
    // I've spent nearly 20 hours on this but unfortunately there isn't even one test written and I'm very sorry...
    // However, I'm satisfied with this application speed - making 4 different schedules and printing them, even without
    // multithreading takes a second.
    // PC specs: Intel i7 6700K @ 4.00GHz, 16GB of RAM, M2 SSD.

    public static void main(String[] args) throws IOException, ParseException {
        try {
            FilesLoader.getFiles();
            ScheduleMaker.makePickingSchedulePrioritizedByPickingTimeDeadline();
//            ScheduleMaker.makePickingSchedulePrioritizedByShortestPickingTime();
//            ScheduleMaker.makePickingSchedulePrioritizedByOrderValue();
//            ScheduleMaker.makePickingSchedulePrioritizedByLongestPickingTime();
            System.out.println("##########################################");
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
    }

}
