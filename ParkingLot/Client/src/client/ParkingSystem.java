package client;

import client.util.Util;

import java.util.Scanner;


/**
 * This class is a main/gateway class for parking lot system
 * 
 * @author vineet
 */

public class ParkingSystem {
    public ParkingSystem() {
        super();
    }

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            String command = "";
            Controller controller= new Controller();
            while(true){
                Util.printMenu();
                command = sc.nextLine();
                if("EXIT".equals(command.toUpperCase())){
                    break;
                } else{
                    controller.processCommand(command);
                }
            }
        }catch (Exception e){
          e.printStackTrace();    
        }
    }
}

