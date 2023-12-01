package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient mapClient;
    private final PriceClient priceClient;
    @Autowired
    public CarService(CarRepository repository, MapsClient mapClient, PriceClient priceClient) {
        /**
         * TODO: Add the Maps and Pricing Web Clients you create
         *   in `VehiclesApiApplication` as arguments and set them here.
         */
        this.mapClient = mapClient;
        this.priceClient = priceClient;
        this.repository = repository;
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        return repository.findAll();
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         *   Remove the below code as part of your implementation.
         */
        String priceOfRequestedCar = null;
        Location loctionOfRequestedCar = null;
        boolean car_available = this.repository.findById(id).isPresent();
        Car  currentCar = car_available ? this.repository.findById(id).get():null;
        System.out.println("currentCar.getId()");
        System.out.println(currentCar.getId());
        if(car_available) {
            try {
                loctionOfRequestedCar = mapClient.getAddress(currentCar.getLocation());
                currentCar.setLocation(loctionOfRequestedCar);
                System.out.println("city");
                System.out.println(loctionOfRequestedCar.getCity());
                priceOfRequestedCar = priceClient.getPrice(id);
                currentCar.setPrice(priceOfRequestedCar);
            } catch (Exception e){
                throw new CarNotFoundException("Problem of reading s price or location");
            }
        } else {
            throw new CarNotFoundException("Car not found exception");
        }



        /**
         * TODO: Use the Pricing Web client you create in `VehiclesApiApplication`
         *   to get the price based on the `id` input'
         * TODO: Set the price of the car
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         */


        /**
         * TODO: Use the Maps Web client you create in `VehiclesApiApplication`
         *   to get the address for the vehicle. You should access the location
         *   from the car object and feed it to the Maps service.
         * TODO: Set the location of the vehicle, including the address information
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         */


        return  currentCar;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
        System.out.println(car.getDetails().getBody());
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setCreatedAt(LocalDateTime.now());
                        carToBeUpdated.setModifiedAt(LocalDateTime.now());
                        carToBeUpdated.setCondition(car.getCondition());
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return repository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        /**
         * TODO: Find the car by ID from the `repository` if it exists.
         *   If it does not exist, throw a CarNotFoundException
         */
        boolean car_available = this.repository.findById(id).isPresent();
        Car  currentCar = car_available ? this.repository.findById(id).get():null;

        if(currentCar == null) {
            throw new CarNotFoundException("Car not found exception");
        }
        else {
            repository.deleteById(id);
        }

        /**
         * TODO: Delete the car from the repository.
         */


    }
}
