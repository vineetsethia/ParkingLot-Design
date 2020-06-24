package client.parkingsystemtest;

import client.Controller;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestParkingSystem {
    public TestParkingSystem() {
        super();
    }
    
       @Test
       public void testBasicTestCase() {
           Controller controller = new Controller();
           assertEquals("Created a parking lot with 6 slots",controller.processCommand("create_parking_lot 6"));
           assertEquals("Allocated slot number: 1",controller.processCommand("park KA-01-HH-1234 White"));
           assertEquals("Allocated slot number: 2",controller.processCommand("park KA-01-HH-9999 White"));
           assertEquals("Allocated slot number: 3",controller.processCommand("park KA-01-BB-0001 Black"));
           assertEquals("Allocated slot number: 4", controller.processCommand("park KA-01-HH-7777 Red"));
           assertEquals("Allocated slot number: 5",controller.processCommand("park KA-01-HH-2701 Blue"));
           assertEquals("Allocated slot number: 6",controller.processCommand("park KA-01-HH-3141 Black"));
           assertEquals("Slot number 4 is free",controller.processCommand("leave 4"));
           
           String testOutput = "Slot No.  Registration No  Color\n" + 
           "1         KA-01-HH-1234  WHITE\n" + 
           "2         KA-01-HH-9999  WHITE\n" + 
           "3         KA-01-BB-0001  BLACK\n" + 
           "5         KA-01-HH-2701  BLUE\n" + 
           "6         KA-01-HH-3141  BLACK";
           
           assertEquals(testOutput,controller.processCommand("status"));
           assertEquals("Allocated slot number: 4",controller.processCommand("park KA-01-P-333 White"));
           assertEquals("Sorry, parking lot is full",controller.processCommand("park DL-12-AA-9999 White"));
           assertEquals("KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333",controller.processCommand("registration_numbers_for_cars_with_colour White"));
           assertEquals("1, 2, 4",controller.processCommand("slot_numbers_for_cars_with_colour White"));
           assertEquals("6",controller.processCommand("slot_number_for_registration_number KA-01-HH-3141"));
           assertEquals("Not found",controller.processCommand("slot_number_for_registration_number MH-04-AY-1111"));
       }

}
