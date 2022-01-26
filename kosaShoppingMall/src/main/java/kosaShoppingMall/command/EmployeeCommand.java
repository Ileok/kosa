package kosaShoppingMall.command;

import lombok.Data;

@Data
public class EmployeeCommand {
	String empId;
	String empPw;
	String empPwCon;
	String empName;
	String empAddr;
	String empPhone;
	
	public boolean isEmpPwEqualsEmpPwCon() {
		return empPw.equals(empPwCon);
	}
}
