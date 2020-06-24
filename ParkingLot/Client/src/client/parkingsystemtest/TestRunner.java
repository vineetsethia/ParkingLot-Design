package client.parkingsystemtest;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;



/**
 * This class is a junit test runner class for parking lot system
 * 
 * @author vineet
 */

public class TestRunner {
    public TestRunner() {
        super();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestParkingSystem.class);
                  
        for (Failure failure : result.getFailures()) {
           System.out.println(failure.toString());
        }
                  
        System.out.println(result.wasSuccessful());
    }
}
