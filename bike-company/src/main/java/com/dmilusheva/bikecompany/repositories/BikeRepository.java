package com.dmilusheva.bikecompany.repositories;

import com.dmilusheva.bikecompany.models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The JPA repository in Spring Data JPA acts as a data access object (DAO).
 * Repositories are interfaces, which define a contract and Spring Data JPA will auto-implement them.
 * It extends the JPA repository, which is part of the Spring Data JPA library.
 *  -> the generics  are replaced with the specific DAO object, which in this cse is the bike object
 *
 *  The first type is the Bike entity and the second type is a Long -> the id ( Long is the type that our primary key is)
 *
 *  When BikeRepository extend theJPA repository, it brings in a whole bunch of methods - create, read, update, delete
 *  (CRUD operations) and you don't actually have to implement or write any code to do that.
 *  So at this point all that's left to do is to go back over to BikesController class and inject the BikeRepository
 *  into the controller by using the @Autowired Spring annotation.
 */
public interface BikeRepository extends JpaRepository<Bike, Long> {
}
