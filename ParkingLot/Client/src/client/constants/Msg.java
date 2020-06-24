package client.constants;

public class Msg {
    public static final String ERROR_INIT_PARKING_LOT_CAPACITY = "Sorry, parking lot capacity has not been initialized, please intialize it first to proceed further";
    public static final String ERROR_PARKING_FULL_VALIDATION = "Sorry, parking lot is full";
    public static final String ERROR_SLOT_ALREADY_OCCUPIED = "Sorry, parking slot is already occupied";
    public static final String ERROR_SLOT_INVALID_OR_UNOCCUPIED = "Parking slot is either invalid or unoccupied, please try again with a valid input";
    public static final String ERROR_INVALID_OBJECT = "Not found";
    public static final String ERROR_INVALID_INPUT = "Sorry, input is not valid";
    public static final String ERROR_UNKNOWN = "Unknown Error";
    public static final String ERROR_NON_EMPTY_PARKING_LOT = "Sorry, parking lot is not empty. Please try after some time when the parking lot is empty";
    
    public static final String MENU_COMMANDS_SAMPLE = "create_parking_lot <number_of_slots>\n" + 
                                                        "park <registeration_number> <colour>\n" + 
                                                        "leave <slot_number>\n" + 
                                                        "status\n" + 
                                                        "registration_numbers_for_cars_with_colour <colour>\n" + 
                                                        "slot_numbers_for_cars_with_colour <colour>\n" + 
                                                        "slot_number_for_registration_number <registeration_number>\n" +
                                                        "exit";
    public static final String DOTTED_LINE = "-----------------------------------";
    public static final String MENU_WELCOME_MSG = "Please enter one of the below commands to proceed : ";
    public static final String PARKING_LOT_STATUS_DISPLAY_HEADER = "Slot No.  Registration No  Color";
    public static final String INITIAL_MSG_CONTENT_PARK_OPERATION = "Allocated slot number: ";
    public static final String INITIAL_MSG_CONTENT_LEAVE_OPERATION = "Slot number ";
    public static final String AFTER_MSG_CONTENT_LEAVE_OPERATION = " is free";
    public static final String INITIAL_MSG_INIT_PARKING_LOT_OPERATION = "Created a parking lot with ";
    public static final String AFTER_MSG_INIT_PARKING_LOT_OPERATION = " slots";  
}
