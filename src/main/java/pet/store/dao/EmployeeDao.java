package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
//Extending JpaRepository makes it so i can use CRUD on empluyee entity