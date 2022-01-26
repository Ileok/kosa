package kosaShoppingMall.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeWriteService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(EmployeeCommand employeeCommand) {
		String empPw = passwordEncoder.encode(employeeCommand.getEmpPw());
		System.out.println(empPw);
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpPw(empPw);
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		
		Integer i = employeeMapper.empInsert(dto);
		System.out.println(i + "개 행이 삽입되었습니다.");
	}
}
