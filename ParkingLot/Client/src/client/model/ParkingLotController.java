package client;

public interface ParkingLotController {
    public String processCreateParkingLot(String[] commandIdentifiers, ParkingLot parkingLot);
    public String processPark(String[] commandIdentifiers, ParkingLot parkingLot);
    public String processLeave(String[] commandIdentifiers, ParkingLot parkingLot);
    public String processStatus(ParkingLot parkingLot);
    public String findRegNumbersByColour(String[] commandIdentifiers, ParkingLot parkingLot);
    public String findSlotNumbersByColour(String[] commandIdentifiers, ParkingLot parkingLot);
    public String findSlotByRegNumber(String[] commandIdentifiers, ParkingLot parkingLot);
    public String flowIdentifier(String[] commandIdentifiers);
}
