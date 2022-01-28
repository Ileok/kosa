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
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String execute(EmployeeCommand employeeCommand, Model model) {
		String path = "redirect:empInfo?id="+employeeCommand.getEmpId();
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			// EmployeeDTO dto = employeeMapper.selectOne(employeeCommand.getEmpId());
			model.addAttribute("errPw","비밀번호가 일치하지 않습니다.");
			// model.addAttribute("employeeCommand", dto);
			/*
			 Command를 인자값으로 받아온 경우 addAttribute를 사용하지 않아도 값 전달이 가능하다.
			 */
			path = "thymeleaf/employee/empModify";
		} else {
		String empPw = passwordEncoder.encode(employeeCommand.getEmpPw());
		System.out.println(empPw);
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpPw(empPw);
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		
		Integer i = employeeMapper.empUpdate(dto);
		System.out.println(i + "개 행이 수정되었습니다.");
		}
		return path;
	}

}
