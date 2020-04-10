package service;

import entity.Parking;
import exception.InvalidTicketException;
import repository.ParkingRepository;

public interface ParkingService {
    ParkingRepository parkingRepository = new ParkingRepository();

    default void insertParkingInfo(int aParkingCount, int bParkingCount) {
        parkingRepository.deleteAll();

        for (int spaceIndex = 1; spaceIndex <= aParkingCount; spaceIndex++) {
            parkingRepository.insert("A", spaceIndex);
        }

        for (int spaceIndex = 1; spaceIndex <= bParkingCount; spaceIndex++) {
            parkingRepository.insert("B", spaceIndex);
        }
    }

    public String generateParkingTicket(String carNumber);

    default public String fetchInfo(Parking parking) {
        boolean isValid = parkingRepository.querybyTicket(parking);
        if (!isValid) {
            throw new InvalidTicketException("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！ ");
        }
        parkingRepository.updateCarNumberSetNull(parking);
        return parking.getCarNumber();
    }

    default public void closeConnection() {
        parkingRepository.closeConnection();
    }
}
