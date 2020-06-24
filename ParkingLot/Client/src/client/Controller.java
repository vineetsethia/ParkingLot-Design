package client;

import client.constants.Msg;
import client.util.Util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Controller implements ParkingLotController{
    public Controller() {
        super();
    }

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    public String processCreateParkingLot(String[] commandIdentifiers, ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.writeLock().lock();
            int capacity = Integer.parseInt(commandIdentifiers[1]);
            if(Util.checkIfParkingLotIsNotEmpty(parkingLot)){
                output =  Msg.ERROR_NON_EMPTY_PARKING_LOT;
            } else{
                Util.initializeCapacity(capacity, parkingLot);
                output =  Util.printSuccessMessageForParkingLotCreation(capacity);
            }
        } catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.writeLock().unlock();
        }
        return output;
    }
    
    public String processPark(String[] commandIdentifiers, ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.writeLock().lock();
            if(Util.checkIfParkingCapacityNotInitizalized()){
                output = Msg.ERROR_INIT_PARKING_LOT_CAPACITY;      
            } else if(Util.checkIfParkingLotIsFull(parkingLot)){
                output = Msg.ERROR_PARKING_FULL_VALIDATION;                   
            } else {
               int slot = Util.performPark(commandIdentifiers, parkingLot);
               output = Util.printSuccessMessageForParkOperation(slot);
            } 
        }catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.writeLock().unlock();
        }
        return output;
    }
    
    public String processLeave(String[] commandIdentifiers, ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.writeLock().lock();
            if(Util.checkIfParkingCapacityNotInitizalized()){
                output = Msg.ERROR_INIT_PARKING_LOT_CAPACITY;      
            } else if(Util.checkIfSlotIsNotValidAndOccupied(commandIdentifiers, parkingLot)){
                output = Msg.ERROR_SLOT_INVALID_OR_UNOCCUPIED;                   
            } else {
                int slot = Util.performLeave(commandIdentifiers, parkingLot); 
                output = Util.printSuccessMessageForLeaveOperation(slot);
            }         
        }catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.writeLock().unlock();
        }
        return output;    
    }
    
    
    public String processStatus(ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.readLock().lock();
            if(Util.checkIfParkingCapacityNotInitizalized()){
                output = Msg.ERROR_INIT_PARKING_LOT_CAPACITY;      
            } else{
                output = Util.printParkingLot(parkingLot);    
            }
        }catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.readLock().unlock();
        }
        return output;
    }
    
    
    public String findRegNumbersByColour(String[] commandIdentifiers, ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.readLock().lock();
            if(Util.checkIfParkingCapacityNotInitizalized()){
                output = Msg.ERROR_INIT_PARKING_LOT_CAPACITY;      
            } else{
                output = Util.printRegNumbersFromColour(commandIdentifiers, parkingLot);    
            }
        }catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.readLock().unlock();
        }
        return output;
    }
    
    
    public String findSlotNumbersByColour(String[] commandIdentifiers, ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.readLock().lock();
            if(Util.checkIfParkingCapacityNotInitizalized()){
                output = Msg.ERROR_INIT_PARKING_LOT_CAPACITY;      
            } else{
                output = Util.printSlotNumbersFromColour(commandIdentifiers, parkingLot);    
            }
        }catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.readLock().unlock();
        }
        return output;
    }
    
    public String findSlotByRegNumber(String[] commandIdentifiers, ParkingLot parkingLot){
        String output = "";
        try{
            readWriteLock.readLock().lock();
            if(Util.checkIfParkingCapacityNotInitizalized()){
                return Msg.ERROR_INIT_PARKING_LOT_CAPACITY;      
            } else{
                return Util.printSlotFromRegNumber(commandIdentifiers, parkingLot);    
            }
        }catch (Exception e){
            e.printStackTrace();
            output = Msg.ERROR_UNKNOWN;
        } finally{
            readWriteLock.readLock().unlock();
        }
        return output;
    }
    
    //Identify which instruction to process and if the instaruction is valid
    public String flowIdentifier(String[] commandIdentifiers){
        try{
            String instructionType  = commandIdentifiers[0];
            ParkingLot parkingLot= ParkingLot.INSTANCE; 
            if("create_parking_lot".equals(instructionType) && Util.validateCreateParkingLotCommand(commandIdentifiers)){
                return processCreateParkingLot(commandIdentifiers, parkingLot);
            } else if("park".equals(instructionType) && Util.validateParkCommand(commandIdentifiers)){
                return processPark(commandIdentifiers, parkingLot);
            } else if("leave".equals(instructionType) && Util.validateLeaveCommand(commandIdentifiers)){
                return processLeave(commandIdentifiers, parkingLot);
            } else if("status".equals(instructionType) && Util.validateStatusCommand(commandIdentifiers)){
                return processStatus(parkingLot);
            } else if("registration_numbers_for_cars_with_colour".equals(instructionType) && Util.validateRegNumberWithColourCommand(commandIdentifiers)){
                return findRegNumbersByColour(commandIdentifiers, parkingLot);
            } else if("slot_numbers_for_cars_with_colour".equals(instructionType) && Util.validateSlotWithColourCommand(commandIdentifiers)){
                return findSlotNumbersByColour(commandIdentifiers, parkingLot);
            } else if("slot_number_for_registration_number".equals(instructionType) && Util.validateSlotForRegNumberCommand(commandIdentifiers)){
                return findSlotByRegNumber(commandIdentifiers, parkingLot);
            } else{
                return Msg.ERROR_INVALID_INPUT;    
            }
        }catch(Exception e){
          e.printStackTrace();
          return Msg.ERROR_UNKNOWN;
        }
    }   
    
    public String processCommand(String command){
        String[] commandIdentifiers = command.split(" ");
        String output = flowIdentifier(commandIdentifiers);
        System.out.println(output);
        return output;    
    }
}
