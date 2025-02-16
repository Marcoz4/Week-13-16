package pet.store.service;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;
import java.util.Objects;

@Service
//Makes this class as a service component
public class PetStoreService {
    @Autowired//Automatically creates and assigns the PetStoreDao object
    private PetStoreDao petStoreDao;
    // This is the variable that we use to interact with the database  
    
    @Transactional(readOnly = false) // Manages transactions
    public PetStoreData savePetStore(PetStoreData petStoreData) {
    	Long petStoreId = petStoreData.getPetStoreId();// Get the pet store ID from the data
        PetStore petStore = findOrCreatePetStore(petStoreId);// Find or create a pet store
       
        copyPetStoreFields(petStore, petStoreData);
        return new PetStoreData(petStoreDao.save(petStore));  
        //Where and data is copied to and from where.
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
    	// Copying fields from PetStoreData to PetStore. 
    	petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
    	petStore.setPetStoreCity(petStoreData.getPetStoreCity());
    	petStore.setPetStoreId(petStoreData.getPetStoreId());
    	petStore.setPetStoreName(petStoreData.getPetStoreName());
    	petStore.setPetStorePhone(petStoreData.getPetStorePhone());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
    }     
    
    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (Objects.isNull(petStoreId)) { // If the ID is null
            return new PetStore();
        }
        else {
        	return findPetStoreById(petStoreId);// Find an existing pet store by ID
        }	
    }			
        
    private PetStore findPetStoreById(Long petStoreId)  {
      return petStoreDao.findById(petStoreId)
               .orElseThrow(() -> new NoSuchElementException("Pet store with ID=" + petStoreId + " was not found."));
    //Will find Id if not error message will be given.
    }
}
