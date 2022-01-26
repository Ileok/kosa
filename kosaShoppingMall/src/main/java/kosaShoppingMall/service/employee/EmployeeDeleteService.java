package kosaShoppingMall.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;


@Component
@Service
public class EmployeeDeleteService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(EmployeeCommand employeeCommand, Model model) {
		String path = "thymeleaf/employee/empList";
		String empPw = employeeCommand.getEmpPw();
		EmployeeDTO dto = employeeMapper.selectOne(employeeCommand.getEmpId());
		if(passwordEncoder.matches(empPw, dto.getEmpPw())) {
			Integer i = employeeMapper.empDelete(employeeCommand.getEmpId());
			System.out.println(i + "개 행이 삭제되었습니다.");
			path = "thymeleaf/employee/empList";
		} else { 
			model.addAttribute("errPw","비밀번호가 일치하지 않습니다.");
			model.addAttribute("dto", dto);
			path = "thymeleaf/employee/empInfo";
		}
		return path;
	}

}