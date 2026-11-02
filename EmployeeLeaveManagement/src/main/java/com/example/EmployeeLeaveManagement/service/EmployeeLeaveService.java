
package com.example.EmployeeLeaveManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.EmployeeLeaveManagement.model.Employeeleave;

@Service
public class EmployeeLeaveService {

    private List<Employeeleave> leaves = new ArrayList<>();
    private Long nextId = 1L;

    // Apply leave
    public Employeeleave applyLeave(Employeeleave leave) {
        leave.setEmployeeId(nextId++);
        leaves.add(leave);
        return leave;
    }

    // View all leaves
    public List<Employeeleave> getAllLeaves() {
        return leaves;
    }

    // Find leave by ID
    public Employeeleave getLeaveById(Long id) {
        for (Employeeleave leave : leaves) {
            if (leave.getEmployeeId().equals(id)) {
                return leave;
            }
        }
        return null;
    }

    // Update leave
    public Employeeleave updateLeave(Long id, Employeeleave updatedLeave) {
        for (int i = 0; i < leaves.size(); i++) {
            if (leaves.get(i).getEmployeeId().equals(id)) {
                updatedLeave.setEmployeeId(id);
                leaves.set(i, updatedLeave);
                return updatedLeave;
            }
        }
        return null;
    }

    // Delete leave
    public boolean deleteLeave(Long id) {
        return leaves.removeIf(
            leave -> leave.getEmployeeId().equals(id)
        );
    }
}
