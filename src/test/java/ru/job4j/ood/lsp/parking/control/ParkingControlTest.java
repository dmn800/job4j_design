package ru.job4j.ood.lsp.parking.control;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.vehicle.Car;
import ru.job4j.ood.lsp.parking.vehicle.Truck;
import ru.job4j.ood.lsp.parking.vehicle.Vehicle;

import static org.assertj.core.api.Assertions.*;

public class ParkingControlTest {

    @Test
    void whenParkCarThenSuccess() {
        ParkingControl parking = new ParkingImpl(2, 1);
        Vehicle car = new Car();
        parking.park(car);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(1);
    }

    @Test
    void whenParkTruckOnTruckPlaceThenSuccess() {
        ParkingControl parking = new ParkingImpl(2, 1);
        Vehicle truck = new Truck(2);
        parking.park(truck);
        assertThat(parking.getFreeTruckPlaces()).isEqualTo(0);
    }

    @Test
    void whenParkTruckOnCarPlacesThenSuccess() {
        ParkingControl parking = new ParkingImpl(3, 0);
        Vehicle truck = new Truck(3);
        parking.park(truck);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(0);
    }

    @Test
    void whenLeaveVehicleThenPlaceBecomesFree() {
        ParkingControl parking = new ParkingImpl(1, 0);
        Vehicle car = new Car();
        parking.park(car);
        parking.leave(car);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(1);
    }

    @Test
    void whenLeaveFreePlacesThenCanParkAgain() {
        ParkingControl parking = new ParkingImpl(1, 0);
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        parking.park(car1);
        parking.leave(car1);
        parking.park(car2);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(0);
    }

    @Test
    void whenParkCarThenOccupiesOneCarPlace() {
        ParkingControl parking = new ParkingImpl(2, 1);
        Vehicle car = new Car();
        parking.park(car);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(1);
        assertThat(parking.getFreeTruckPlaces()).isEqualTo(1);
    }

    @Test
    void whenNoFreeCarPlacesThenThrowException() {
        ParkingControl parking = new ParkingImpl(1, 0);
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        parking.park(car1);
        assertThatThrownBy(() -> parking.park(car2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("No places available");
    }

    @Test
    void whenParkTruckOnTruckPlaceThenTruckPlaceDecreases() {
        ParkingControl parking = new ParkingImpl(2, 1);
        Vehicle truck = new Truck(2);
        parking.park(truck);
        assertThat(parking.getFreeTruckPlaces()).isEqualTo(0);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(2);
    }

    @Test
    void whenParkTruckOnCarPlacesThenCarPlacesDecreaseBySize() {
        ParkingControl parking = new ParkingImpl(3, 0);
        Vehicle truck = new Truck(2);
        parking.park(truck);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(1);
    }

    @Test
    void whenNotEnoughCarPlacesForTruckThenThrowException() {
        ParkingControl parking = new ParkingImpl(1, 0);
        Vehicle truck = new Truck(2);
        assertThatThrownBy(() -> parking.park(truck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("No places available");
    }

    @Test
    void whenLeaveCarThenPlaceFreed() {
        ParkingControl parking = new ParkingImpl(1, 0);
        Vehicle car = new Car();
        parking.park(car);
        parking.leave(car);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(1);
    }

    @Test
    void whenLeaveTruckFromCarPlacesThenPlacesFreed() {
        ParkingControl parking = new ParkingImpl(3, 0);
        Vehicle truck = new Truck(2);
        parking.park(truck);
        parking.leave(truck);
        assertThat(parking.getFreeCarPlaces()).isEqualTo(3);
    }

    @Test
    void whenLeaveNotParkedVehicleThenThrowException() {
        ParkingControl parking = new ParkingImpl(1, 0);
        Vehicle car = new Car();
        assertThatThrownBy(() -> parking.leave(car))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Vehicle not found");
    }
}