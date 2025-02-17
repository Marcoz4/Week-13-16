package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import pet.store.service.PetStoreService;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;

@RestController //This class is a REST controller, which means it can handle HTTP requests and return responses in JSON format.
@RequestMapping("/pet_store")//The URI for every HTTP request mapped to a method in this controller class must start with /pet_store
@Slf4j// Adds an SLF4J logger named "log" for writing messages.
public class PetStoreController {

    @Autowired
 // Spring Boot will automatically create and assign the PetStoreService object
    private PetStoreService petStoreService;
 //Declares a variable to access the service methods.  
    @PostMapping//Maps to HTTP POST request to this method
    @ResponseStatus(code = HttpStatus.CREATED)//Sets Response Status 201
    public PetStoreData createPetStore(
    		@RequestBody PetStoreData petStoreData) {
    	//Handles Request Body
        log.info("Creating new pet store: {}", petStoreData);//Logs the Request
        return petStoreService.savePetStore(petStoreData);//Calls Service Method to save data
    }
    

    @PutMapping("/{petStoreId}")
 // Maps HTTP PUT requests to this method and expects a pet store ID in the URI
    public PetStoreData updatePetStore(@PathVariable Long petStoreId, 
    	@RequestBody PetStoreData petStoreData) { // Reads the store data from the request body
      petStoreData.setPetStoreId(petStoreId); // Sets the pet store ID in the DTO
      log.info("Updating pet store {}", petStoreData); // Logs the method call
        return petStoreService.savePetStore(petStoreData);// Calls the savePetStore method in the service class
    }
    //15
    @PostMapping("/{petStoreId}/employee")//Maps HTTP POST requests to this method. 
    //The {petStoreId} part of the URL will be replaced with the actual pet store ID.
    @ResponseStatus(code = HttpStatus.CREATED)//Sets HTTP status code to 201 when method is successful
    public PetStoreEmployee addEmployeeToPetStore(@PathVariable Long petStoreId,//Gets the petStoreId from the URL.
    //It returns a PetStoreEmployee object, which is the result of adding the employee.		
        @RequestBody PetStoreEmployee petStoreEmployee) {//Converts the JSON request body into a PetStoreEmployee object.
        log.info("Adding employee {} to pet store with ID={}", petStoreEmployee, petStoreId);//Logs informational message
        return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
    }//returns as parameters in petStoreService
    
    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreCustomer addCustomerToPetStore(@PathVariable Long petStoreId,
        @RequestBody PetStoreCustomer petStoreCustomer) {
        // Log the request details
        log.info("Adding customer {} to pet store with ID={}", petStoreCustomer, petStoreId);
      
        return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
    }
    
    @GetMapping
    public List<PetStoreData> retriveAllPetStores() {
        log.info("Retrieving all pet stores");
        return petStoreService.retrieveAllPetStores();
    }

    @GetMapping("/{petStoreId}")
    public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
        log.info("Retrieving pet store with ID={}", petStoreId);
        return petStoreService.retrievePetStoreById(petStoreId);
    }
    
    @DeleteMapping("/{petStoreId}")
    public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID={}", petStoreId);
        
        petStoreService.deletePetStoreById(petStoreId);
        
        return Map.of("message", "Pet Store with ID=" + petStoreId + " deleted.");
    }
    
}



    
    


