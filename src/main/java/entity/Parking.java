package entity;

public class Parking {
    private String lotNumber;
    private int spaceNumber;
    private String carNumber;

    public Parking(String lotNumber, int spaceNumber) {
        this.lotNumber = lotNumber;
        this.spaceNumber = spaceNumber;
    }

    public Parking(String lotNumber, int spaceNumber, String carNumber) {
        this.lotNumber = lotNumber;
        this.spaceNumber = spaceNumber;
        this.carNumber = carNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
