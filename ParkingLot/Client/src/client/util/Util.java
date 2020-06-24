package client.util;

import client.Car;
import client.Configuration;
import client.constants.Msg;
import client.ParkingLot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Util {
    public Util() {
        super();
    }
    
    public  static boolean validateCreateParkingLotCommand(String[] commandIdentifiers){
        try 
        { 
            int capacity = Integer.parseInt(commandIdentifiers[1]); 
            return (commandIdentifiers.length==2 && capacity > 0) ? true : false;
        }  
        catch (NumberFormatException e)  
        { 
            return false;
        } 
    }
    
    public static boolean validateParkCommand(String[] commandIdentifiers){
        return (commandIdentifiers.length==3) ? true : false; 
    }
    
    public static boolean validateLeaveCommand(String[] commandIdentifiers){
        try 
        { 
            int slotNumber = Integer.parseInt(commandIdentifiers[1]); 
            return (commandIdentifiers.length==2 && slotNumber > 0) ? true : false;
        }  
        catch (NumberFormatException e)  
        { 
            return false;
        } 
    }
    
    public static boolean validateStatusCommand(String[] commandIdentifiers){
        return (commandIdentifiers.length==1) ? true : false; 
    }
    
    public static boolean validateRegNumberWithColourCommand(String[] commandIdentifiers){
        return (commandIdentifiers.length==2) ? true : false;  
    }
    
    public static boolean validateSlotWithColourCommand(String[] commandIdentifiers){
        return (commandIdentifiers.length==2) ? true : false;  
    }
    
    public static boolean validateSlotForRegNumberCommand(String[] commandIdentifiers){
        return (commandIdentifiers.length==2) ? true : false;  
    }
    
    public static void initializeCapacity(int capacity,
                                          ParkingLot parkingLot){
        Configuration.CAPACITY = capacity;
        Configuration.IS_CAPACITY_SET = true;
        TreeSet<Integer> availableSlots = new TreeSet<Integer>();
        for(int slot=1;slot<=capacity;slot++){
            availableSlots.add(slot);
        }
        parkingLot.setAvailableSlots(availableSlots);
        parkingLot.setSlots(new Car[capacity+1]);
        parkingLot.setColourToRegNumberMap(new HashMap<String, LinkedHashSet<String>>());
        parkingLot.setColourToSlotMap(new HashMap<String, HashSet<Integer>>());
        parkingLot.setRegNumberToSlotMap(new HashMap<String,Integer>());
    }
    
    public static String printSuccessMessageForParkingLotCreation(int capacity){
        return Msg.INITIAL_MSG_INIT_PARKING_LOT_OPERATION +  capacity + Msg.AFTER_MSG_INIT_PARKING_LOT_OPERATION;
    }
    
    public static boolean checkIfParkingLotIsFull(ParkingLot parkingLot){
        if(parkingLot.getAvailableSlots()!=null && parkingLot.getAvailableSlots().size() <= 0){
            return true;
        } else{
            return false;    
        }
    }
    
    public static boolean checkIfParkingLotIsNotEmpty(ParkingLot parkingLot){
        if(Configuration.IS_CAPACITY_SET && parkingLot.getAvailableSlots()!=null && parkingLot.getAvailableSlots().size()>0){
            return true;
        } else{
            return false;
        }
    }
    
    public static boolean checkIfParkingCapacityNotInitizalized(){
        if(Configuration.IS_CAPACITY_SET == false){
            return true;
        } else{
            return false;    
        }
    }
    
    public static int performPark(String[] commandIdentifiers,
                                  ParkingLot parkingLot){
        String regNumber = commandIdentifiers[1].toUpperCase();
        String colour = commandIdentifiers[2].toUpperCase();
        Car car = new Car(regNumber, colour);
        int slot = -1;
        TreeSet<Integer> availableSlots = parkingLot.getAvailableSlots();
        if(availableSlots!=null && availableSlots.size() > 0){
            slot = availableSlots.pollFirst();
            parkingLot.setAvailableSlots(availableSlots);

            Car[] carSlots = parkingLot.getSlots();
            carSlots[slot] = car;
            parkingLot.setSlots(carSlots);
            
            HashMap<String, LinkedHashSet<String>> colourToRegNumberMap = parkingLot.getColourToRegNumberMap();
            LinkedHashSet<String> regNumbers = colourToRegNumberMap.containsKey(colour) ? colourToRegNumberMap.get(colour) :  new LinkedHashSet<String>();
            regNumbers.add(regNumber);
            colourToRegNumberMap.put(colour, regNumbers);
            parkingLot.setColourToRegNumberMap(colourToRegNumberMap);
            
            HashMap<String, HashSet<Integer>> colourToSlotMap = parkingLot.getColourToSlotMap();
            HashSet<Integer> slots = colourToSlotMap.containsKey(colour) ? colourToSlotMap.get(colour) : new HashSet<Integer>();
            slots.add(slot);
            colourToSlotMap.put(colour, slots);
            parkingLot.setColourToSlotMap(colourToSlotMap);
            
            HashMap<String,Integer> regNumberToSlotMap = parkingLot.getRegNumberToSlotMap();
            regNumberToSlotMap.put(regNumber,slot);
            parkingLot.setRegNumberToSlotMap(regNumberToSlotMap); 
        }
        return slot;
    }
    
    public static boolean checkIfSlotIsNotValidAndOccupied(String[] commandIdentifiers,
                                                           ParkingLot parkingLot){
       int slot = Integer.parseInt(commandIdentifiers[1]);
       if(parkingLot.getAvailableSlots()!=null && !parkingLot.getAvailableSlots().contains(slot) && slot <= Configuration.CAPACITY){
          return false;    
       } else{
         return true;    
       }
    }
    
    public static String printSuccessMessageForParkOperation(int slot){
        if(slot == -1){
            return Msg.ERROR_UNKNOWN;
        }else{
            return Msg.INITIAL_MSG_CONTENT_PARK_OPERATION +  slot;
        }
    }
    
    public static int performLeave(String[] commandIdentifiers,
                                   ParkingLot parkingLot){
        int slot = Integer.parseInt(commandIdentifiers[1]);

        Car[] carSlots = parkingLot.getSlots();
        Car car = carSlots[slot];
        String regNumber = car.getRegNumber();
        String colour = car.getColour();
        
        carSlots[slot] = null;
        parkingLot.setSlots(carSlots);
        
        TreeSet<Integer> availableSlots = parkingLot.getAvailableSlots();
        availableSlots.add(slot);
        parkingLot.setAvailableSlots(availableSlots);
        
       HashMap<String, LinkedHashSet<String>> colourToRegNumberMap = parkingLot.getColourToRegNumberMap();
       LinkedHashSet<String> regNumbers = colourToRegNumberMap.containsKey(colour) ? colourToRegNumberMap.get(colour) :  null;
       if(regNumbers!=null){
          regNumbers.remove(regNumber);  
          colourToRegNumberMap.put(colour, regNumbers);
          parkingLot.setColourToRegNumberMap(colourToRegNumberMap);
       }
    
       HashMap<String, HashSet<Integer>> colourToSlotMap = parkingLot.getColourToSlotMap();
       HashSet<Integer> slots = colourToSlotMap.containsKey(colour) ? colourToSlotMap.get(colour) : null;
       if(slots!=null){
          slots.remove(slot);  
          colourToSlotMap.put(colour, slots);
          parkingLot.setColourToSlotMap(colourToSlotMap);
       }
       
       HashMap<String,Integer> regNumberToSlotMap = parkingLot.getRegNumberToSlotMap();
       if(regNumberToSlotMap.containsKey(regNumber)){
          regNumberToSlotMap.remove(regNumber);
          parkingLot.setRegNumberToSlotMap(regNumberToSlotMap);
       }
       
       return slot;
   }
    
    public static String printSuccessMessageForLeaveOperation(int slot){
        return Msg.INITIAL_MSG_CONTENT_LEAVE_OPERATION +  slot + Msg.AFTER_MSG_CONTENT_LEAVE_OPERATION;
    }
    
    
    public static String printParkingLot(ParkingLot parkingLot){
        Car[] carSlots = parkingLot.getSlots();
        TreeSet<Integer> availableSlots = parkingLot.getAvailableSlots();
        String output = "";
        output += Msg.PARKING_LOT_STATUS_DISPLAY_HEADER + "\n";
        boolean firstFound = false;
        for(int slot=1;slot<carSlots.length;slot++){
            if(!availableSlots.contains(slot)){
                if(firstFound){
                    output += "\n" + slot + "         " +  carSlots[slot].getRegNumber() + "  " + carSlots[slot].getColour();
                } else{
                    output += slot + "         " +  carSlots[slot].getRegNumber() + "  " + carSlots[slot].getColour();
                    firstFound = true;  
                }
            } 
        }
        return output;
    }
    
    public static String printRegNumbersFromColour(String[] commandIdentifiers,
                                                   ParkingLot parkingLot){
        String colour = commandIdentifiers[1].toUpperCase();
        HashMap<String, LinkedHashSet<String>> colourToRegNumberMap = parkingLot.getColourToRegNumberMap();
        String output = "";
        if(colourToRegNumberMap.containsKey(colour)){
            HashSet<String> regNumbers = colourToRegNumberMap.get(colour);
            boolean firstFound = false;
            for(String regNumber : regNumbers){
                if(firstFound){
                    output += ", " + regNumber;
                } else {
                    output += regNumber;
                    firstFound = true;
                }
            }
        } else{
           output += Msg.ERROR_INVALID_OBJECT;    
        }
        return output;
    }
    
    public static String printSlotNumbersFromColour(String[] commandIdentifiers,
                                                    ParkingLot parkingLot){
        String colour = commandIdentifiers[1].toUpperCase();
        HashMap<String, HashSet<Integer>> colourToSlotMap = parkingLot.getColourToSlotMap();
        String output = "";
        if(colourToSlotMap.containsKey(colour)){
            HashSet<Integer> slots = colourToSlotMap.get(colour);
            boolean firstFound = false;
            for(Integer slot : slots){
                if(firstFound){
                    output += ", " + slot;
                } else {
                    output += slot;
                    firstFound = true;
                }
            }
        } else{
           output += Msg.ERROR_INVALID_OBJECT; 
        }
        
        return output;
    }
    
    public static String printSlotFromRegNumber(String[] commandIdentifiers,
                                                ParkingLot parkingLot){
        String regNumber = commandIdentifiers[1].toUpperCase();
        String output = "";
        HashMap<String,Integer> regNumberToSlotMap = parkingLot.getRegNumberToSlotMap();
        if(regNumberToSlotMap.containsKey(regNumber)){
            int slot = regNumberToSlotMap.get(regNumber);
            output += slot;
        } else{
            output += Msg.ERROR_INVALID_OBJECT;
        }
        return output;
    }
    
    public static void printMenu(){
        System.out.println(Msg.DOTTED_LINE);
        System.out.println(Msg.MENU_WELCOME_MSG);
        System.out.println(Msg.MENU_COMMANDS_SAMPLE);
        System.out.println(Msg.DOTTED_LINE);
    }
}
