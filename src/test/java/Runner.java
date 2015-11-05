import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import setup.ChromeTest;
import setup.FirefoxTest;
import setup.IETest;

public class Runner {
    public static void main(String[] args) {
        Result result= JUnitCore.runClasses(FirefoxTest.class, ChromeTest.class, IETest.class);
        for (Failure failure:result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println("Overall Tests Result = "+result.wasSuccessful());
    }

}
