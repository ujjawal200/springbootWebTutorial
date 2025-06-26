package week2.springbootWebTutorial.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week2.springbootWebTutorial.dto.EmployeeDTO;
import week2.springbootWebTutorial.entities.EmployeeEntity;
import week2.springbootWebTutorial.exceptions.ResourceNotFoundException;
import week2.springbootWebTutorial.repositories.EmployeeRepository;
import week2.springbootWebTutorial.services.EmployeeService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
//    @GetMapping(path = "/getSecretMessage")
//    public String getSecretMessage(){
//        return "secret message : #345$@&*";
//    }
//    private final EmployeeRepository employeeRepository ;
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
//        if(employeeDTO == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(employeeDTO);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
//                .orElse(ResponseEntity.notFound().build());
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Found with id: " + id));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees (@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
        EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee , HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO , @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById( employeeId ,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return new ResponseEntity<Boolean>(false , HttpStatus.NOT_FOUND);
//        return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> partialUpdateEmployeeById(@RequestBody Map<String,Object>updates , @PathVariable Long employeeId ){
        EmployeeDTO employeeDTO = employeeService.partialUpdateEmployeeById(employeeId,updates);
        if(employeeDTO == null)return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }





}

