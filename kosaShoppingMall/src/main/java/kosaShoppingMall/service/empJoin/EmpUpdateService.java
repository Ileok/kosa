package kosaShoppingMall.service.empJoin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeShipMapper;

@Component
@Service
public class EmpUpdateService {
	@Autowired
	EmployeeShipMapper employeeShipMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(EmployeeCommand employeeCommand,HttpSession session,
			BindingResult result) {
		String path = null;
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(passwordEncoder.matches(
				employeeCommand.getEmpPw(), authInfo.getUserPw())){
			EmployeeDTO dto = new EmployeeDTO();
			dto.setEmpId(employeeCommand.getEmpId());
			dto.setEmpAddr(employeeCommand.getEmpAddr());
			dto.setEmpEmail(employeeCommand.getEmpEmail());
			dto.setEmpName(employeeCommand.getEmpName());
			dto.setEmpPhone(employeeCommand.getEmpPhone());
			Integer i = employeeShipMapper.empUpdate(dto);
			System.out.println(i + "개 행이(가) 수정되었습니다.");
			path = "redirect:empDetail";
			
		}else {
			result.rejectValue("empPw", "employeeCommand.empPw", 
					"비밀번호가 틀렸습니다.");
			path = "thymeleaf/empShip/empModify";
		}
		return path;
	}
}