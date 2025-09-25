package com.anas.fleetshare.ui;

import com.anas.fleetshare.application.PricingFactory;
import com.anas.fleetshare.application.repositories.TripRepository;
import com.anas.fleetshare.application.repositories.UserRepository;
import com.anas.fleetshare.application.repositories.VehicleRepository;
import com.anas.fleetshare.application.service.TripService;
import com.anas.fleetshare.application.service.UserService;
import com.anas.fleetshare.application.service.VehicleService;
import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.trip.Trip;
import com.anas.fleetshare.domain.user.MembershipTier;
import com.anas.fleetshare.domain.user.User;
import com.anas.fleetshare.domain.vehicle.Car;
import com.anas.fleetshare.domain.vehicle.Vehicle;
import com.anas.fleetshare.domain.vehicle.VehicleState;
import com.anas.fleetshare.infrastructure.repositories.InMemoryTripRepository;
import com.anas.fleetshare.infrastructure.repositories.InMemoryUserRepository;
import com.anas.fleetshare.infrastructure.repositories.InMemoryVehicleRepository;

import java.math.BigDecimal;

public class ConsoleApp {
    public static void main(String[] args) throws InterruptedException {

        UserRepository userRepo = new InMemoryUserRepository();
        VehicleRepository vehicleRepo = new InMemoryVehicleRepository();
        TripRepository tripRepo = new InMemoryTripRepository();


        PricingFactory factory = new PricingFactory();
        TripService tripService = new TripService(userRepo, vehicleRepo, tripRepo, factory);


        var userService = new UserService(userRepo, tripService);
        var vehicleService = new VehicleService(vehicleRepo);


        var u = userService.register("anas", MembershipTier.PREMIUM, new Money(new BigDecimal("50.00")));
        var car = vehicleService.addCar("toyota corolla", "1-8055", 5);


        var trip = userService.startTrip(u.getId(), car.getId(), 1200.0);
        Thread.sleep(10000);
        var price = userService.stopTrip(trip.getId(), 1250.4, PricingFactory.Type.DISTANCE);


        System.out.printf("trip price: %.2f JOD, new balance: %.2f JOD%n",
                price.getAmount(), u.getBalance().getAmount());

        System.out.println("available cars: " + vehicleService.listByTypeAndState(Car.class, VehicleState.AVAILABLE).size());

    }
}
