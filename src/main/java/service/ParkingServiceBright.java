package service;

import entity.Parking;
import exception.ParkingLotFullException;
import repository.ParkingRepository;

public class ParkingServiceBright {
    private ParkingRepository parkingRepository = new ParkingRepository();

    public String generateParkingTicket(String carNumber) {
        Parking parking = parkingRepository.queryMostLotMinNumberParking();

        if (parking == null) {
            throw new ParkingLotFullException(" 非常抱歉，由于车位已满，暂时无法为您停车！");
        }
        parking.setCarNumber(carNumber);
        parkingRepository.updateCarNumber(parking);
        return String.format("%s,%d,%s", parking.getLotNumber(), parking.getSpaceNumber(), carNumber);
    }
}

