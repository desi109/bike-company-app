package com.dmilusheva.bikecompany.controllers;

import com.dmilusheva.bikecompany.models.Bike;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// creat an API endpoint to pass the bike information from /models/Bike.java
// "/api/v1/bikes"  is the path or the url that the application will respond to when a request is made
// v1 is for version 1


@RestController
@RequestMapping("/api/v1/bikes")
public class  BikesController {

    // @GetMapping  -> method level annotation , which is going to say
    // when there is a GET request with the base path "/api/vi/bikes",
    // this GET request will be forward to the list() method
    // which will return a response with list of bikes
    // list bikes
    @GetMapping
    public List<Bike> list(){
        List<Bike> bikes = new ArrayList<>();
        return bikes;
    }

    // This create() method will handle the Form creation when the user submits their registration information.
    // The registration information will be processed by this method and then --> get() method.
    // create bike
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Bike bike){
    }

    // The get() method, which will get a particular bike and return that single bike based off of the id that we pass it.
    // get bike by id
    @GetMapping("/{id}")
    public Bike get(@PathVariable("id") long id) {
        return new Bike();
    }

}
