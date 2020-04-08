package service;

import entity.Parking;
import exception.InvalidTicketException;
import exception.ParkingLotFullException;
import repository.ParkingRepository;

public class ParkingService {
    private ParkingRepository parkingRepository = new ParkingRepository();

    public void insertParkingInfo(int aParkingCount, int bParkingCount) {
        parkingRepository.deleteAll();

        for (int spaceIndex = 1; spaceIndex <= aParkingCount; spaceIndex++) {
            parkingRepository.insert("A", spaceIndex);
        }

        for (int spaceIndex = 1; spaceIndex <= bParkingCount; spaceIndex++) {
            parkingRepository.insert("B", spaceIndex);
        }
    }

    public String generateParkingTicket(String carNumber) {
        Parking parking = parkingRepository.queryMinNumberParking();

        if (parking == null) {
            throw new ParkingLotFullException(" 非常抱歉，由于车位已满，暂时无法为您停车！");
        }

        parkingRepository.updateCarNumber(parking.getLotNumber(), parking.getSpaceNumber(), carNumber);
        return String.format("%s,%d,%s", parking.getLotNumber(), parking.getSpaceNumber(), carNumber);
    }

    public String fetchInfo(String lotNo, int spaceNo, String carNumber) {

        boolean isValid = parkingRepository.querybyTicket(lotNo, spaceNo, carNumber);
        if (!isValid) {
            throw new InvalidTicketException("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！ ");
        }
        parkingRepository.deleteByTicket(lotNo, spaceNo, carNumber);
        return carNumber;
    }

    public void closeConnection() {
        parkingRepository.closeConnection();
    }
}