package com.example.EmployeeLeaveManagement.model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employeeleave {
	 private Long employeeId;

	    @NotBlank(message = "Employee name is required")
	    private String employeeName;

	    @NotBlank(message = "Leave type is mandatory")
	    private String leaveType;

	    @Min(value = 1, message = "Leave days should be minimum 1")
	    private int numberOfDays;
	    @NotBlank(message = "Please give valid reason")
	    private String reason;

}
