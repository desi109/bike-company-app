package com.dmilusheva.bikecompany.controllers;

import com.dmilusheva.bikecompany.models.Bike;
import com.dmilusheva.bikecompany.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Creat an API endpoint to pass the bike information from /models/Bike.java
 * "/api/v1/bikes"  is the path or the url that the application will respond to when a request is made.
 *
 *  v1 is for version 1
 *
 */
@RestController
@RequestMapping("/api/v1/bikes")
public class  BikesController {

    /**
     * @Autowired is a Spring annotation that inject the BikeRepository into the BikesController.
     * I can now take the injected BikeRepository, which Spring will handle for us and I can replace
     * each of these methods by using the actual persistence code.
     *
     */
    @Autowired
    private BikeRepository bikeRepository;

    /**
     * @GetMapping  -> method level annotation , which is going to say
     * when there is a GET request with the base path "/api/vi/bikes",
     * this GET request will be forward to the list() method
     * which will return a response with list of bikes.
     *
     * bikeRepository.findAll() -> will find all of the records in our database table and return it
     *                          -> that is a free method from Spring Data JPA
     *
     * @return all bike records
     */
    @GetMapping
    public List<Bike> list(){
        return bikeRepository.findAll();
    }


    /**
     * bikeRepository.save(bike) -> take the bike information passed in via the API and save it as a Bike object.
     *                           -> will handle the Form creation when the user submits their registration information.
     *
     * The registration information will be processed by this method and then --> get() method.
     *
     * @param bike - this method create new bike
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Bike bike){
        bikeRepository.save(bike);
    }


    /**
     * bikeRepository.getOne(id) -> will get a particular bike by its id and return that single bike.
     *                           -> select all from bikes and return the bike where primary key equals the id passed in.
     *
     * @param id - bike's id
     * @return a specific bike using the id, which is passed from the API endpoint.
     */
    @GetMapping("/{id}")
    public Bike get(@PathVariable("id") long id) {
        return bikeRepository.getOne(id);
    }

}
