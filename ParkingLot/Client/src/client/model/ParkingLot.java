package client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;


/**
 * This class is a singleton class to manage the data of parking lot
 * 
 * @author vineet
 */
public enum ParkingLot {
    INSTANCE;
    
    HashMap<String, LinkedHashSet<String>> colourToRegNumberMap; //To store colour to Registeration Number Mapping
    HashMap<String, HashSet<Integer>> colourToSlotMap; //To store colour to Slot Number Mapping
    HashMap<String,Integer> regNumberToSlotMap; //To store Registeration Number to Slot Nummber Mapping
    TreeSet<Integer> availableSlots; //To store all the available vacant slots
    Car[] slots; //Slot array to check if car is present or not on a specific slot

    public void setColourToRegNumberMap(HashMap<String, LinkedHashSet<String>> colourToRegNumberMap) {
        this.colourToRegNumberMap = colourToRegNumberMap;
    }

    public HashMap<String, LinkedHashSet<String>> getColourToRegNumberMap() {
        return colourToRegNumberMap;
    }

    public void setColourToSlotMap(HashMap<String, HashSet<Integer>> colourToSlotMap) {
        this.colourToSlotMap = colourToSlotMap;
    }

    public HashMap<String, HashSet<Integer>> getColourToSlotMap() {
        return colourToSlotMap;
    }

    public void setRegNumberToSlotMap(HashMap<String, Integer> regNumberToSlotMap) {
        this.regNumberToSlotMap = regNumberToSlotMap;
    }

    public HashMap<String, Integer> getRegNumberToSlotMap() {
        return regNumberToSlotMap;
    }

    public void setAvailableSlots(TreeSet<Integer> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public TreeSet<Integer> getAvailableSlots() {
        return availableSlots;
    }

    public void setSlots(Car[] slots) {
        this.slots = slots;
    }

    public Car[] getSlots() {
        return slots;
    }
}
