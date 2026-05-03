package ru.job4j.ood.lsp.parking.control;

import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class ParkingImpl implements ParkingControl {

    private int freeCarPlaces;
    private int freeTruckPlaces;

    private final Map<Vehicle, Integer> parked = new HashMap<>();

    public ParkingImpl(int carPlace, int truckPlace) {
        this.freeCarPlaces = carPlace;
        this.freeTruckPlaces = truckPlace;
    }

    @Override
    public void park(Vehicle vehicle) {
        int size = vehicle.getSize();
        if (size == 1) {
            if (freeCarPlaces < 1) {
                throw new IllegalStateException("No places available");
            }
            freeCarPlaces -= 1;
            parked.put(vehicle, 1);
            return;
        }
        if (freeTruckPlaces > 0) {
            freeTruckPlaces -= 1;
            parked.put(vehicle, -1);
            return;
        }
        if (freeCarPlaces >= size) {
            freeCarPlaces -= size;
            parked.put(vehicle, size);
            return;
        }
        throw new IllegalStateException("No places available");
    }

    @Override
    public void leave(Vehicle vehicle) {
        Integer occupied = parked.get(vehicle);
        if (occupied == null) {
            throw new IllegalStateException("Vehicle not found");
        }
        if (occupied == -1) {
            freeTruckPlaces += 1;
        } else {
            freeCarPlaces += occupied;
        }
        parked.remove(vehicle);
    }

    @Override
    public int getFreeCarPlaces() {
        return freeCarPlaces;
    }

    @Override
    public int getFreeTruckPlaces() {
        return freeTruckPlaces;
    }
}
