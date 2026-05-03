package ru.job4j.ood.lsp.parking.control;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

public interface ParkingControl {

    void park(Vehicle vehicle);

    void leave(Vehicle vehicle);

    int getFreeCarPlaces();

    int getFreeTruckPlaces();
}
