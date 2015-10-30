import utils.Service;

/**
 * Created by kyak on 19.10.2015.
 */
public class Main {
    public static void main(String[] args) {

        Service service=new Service();
        service.startCount();
        for (int i = 1; i < 3000000; i++) {
            System.out.println(i);
        }
        service.stopCount();
        System.out.println("Start="+service.timeStart);
        System.out.println("Finish="+service.timeFinish);
        System.out.println("Duration="+service.getTimeDuration());
    }
}
