package pet.store.entity;


import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity // Tells us that this class is an entity and that is mapped to a database table.
@Data // Lombok annotation automatically generates getters, setters, toString, equals, and hashCode methods.
public class Customer { 
	@Id // Specifies the primary key of the entity. 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Tells how the strategy should be to generate the primary key. will do it automatically 
	private Long customerId;
	
	private String customerFirstName; 
	private String customerLastName; 
	private String customerEmail;
	//The customers data 
	 
    @EqualsAndHashCode.Exclude // Excludes this field from equals and hashCode methods to prevent petStores from going on forever. 
    @ToString.Exclude // Excludes this field from toString method to prevent infinite loop. 
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    // ManyToMany indicates a relationship,mappedBy helps Hibernate understand the relationship.
    //cascade will automatically save stuff thats related, helps manage lifecycle of related entities.
    private Set<PetStore> petStores = new HashSet<>(); // Represents the pet stores the customer is associated with. No multiple.
}