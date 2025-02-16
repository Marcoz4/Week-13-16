package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;//extends to be able to use CRUD
import pet.store.entity.PetStore;//imports PetStore entity

public interface PetStoreDao extends JpaRepository<PetStore, Long> {
// This interface extends JpaRepository to provide CRUD operations for PetStore entities	
}
