package week2.springbootWebTutorial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import week2.springbootWebTutorial.entities.EmployeeEntity;

import java.util.List;

@Repository
public interface   EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    
}
