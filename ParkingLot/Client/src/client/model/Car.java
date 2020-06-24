package client;

public class Car implements Vehicle{
    
    String regNumber;
    String colour;
    
    public Car(String regNumber, String colour) {
        super();
        this.regNumber = regNumber;
        this.colour = colour;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
    
}
