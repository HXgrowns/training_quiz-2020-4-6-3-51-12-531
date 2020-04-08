package entity;

public class Parking {
    private String lotNumber;
    private int spaceNumber;

    public Parking(String lotNumber, int spaceNumber) {
        this.lotNumber = lotNumber;
        this.spaceNumber = spaceNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }
}
