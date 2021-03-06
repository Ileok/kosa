package kosaShoppingMall.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeMapper;

@Component
@Service
public class EmployeeModifyService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String execute(String empId, String empPw, Model model) {
		String path;
		EmployeeDTO dto = employeeMapper.selectOne(empId);
		if(passwordEncoder.matches(empPw, dto.getEmpPw())) {
			path = "thymeleaf/employee/empModify";
			model.addAttribute("employeeCommand", dto);
		} else { 
			model.addAttribute("errPw","비밀번호가 일치하지 않습니다.");
			model.addAttribute("employeeCommand", dto);
			path = "thymeleaf/employee/empInfo";
		}
		return path;
	}

}