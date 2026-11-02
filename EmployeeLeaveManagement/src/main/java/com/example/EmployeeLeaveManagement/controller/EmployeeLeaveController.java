package com.example.EmployeeLeaveManagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.EmployeeLeaveManagement.model.Employeeleave;
import com.example.EmployeeLeaveManagement.service.EmployeeLeaveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService service;
    public EmployeeLeaveController(EmployeeLeaveService service) {
    	this.service = service;
    }
    @PostMapping("/apply")
    public Employeeleave applyLeave(@Valid @RequestBody Employeeleave leave) {
        return service.applyLeave(leave);
    }
    @GetMapping
    public List<Employeeleave> getAllLeaves() {
        return service.getAllLeaves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employeeleave> getLeaveById(@PathVariable Long id) {

        Employeeleave leave = service.getLeaveById(id);

        if (leave == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(leave);
    }

    // 4. Update leave
    @PutMapping("/{id}")
    public ResponseEntity<Employeeleave> updateLeave(@PathVariable Long id,@Valid @RequestBody Employeeleave updatedLeave) {

        Employeeleave leave = service.updateLeave(id, updatedLeave);

        if (leave == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(leave);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeave(@PathVariable Long id) {

        boolean deleted = service.deleteLeave(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Leave deleted successfully");
    }
}
