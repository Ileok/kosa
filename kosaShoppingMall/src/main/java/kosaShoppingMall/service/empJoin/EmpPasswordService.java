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
public class EmpPasswordService {
	@Autowired
	EmployeeShipMapper employeeShipMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(EmployeeCommand empCommand ,
			BindingResult result , HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(passwordEncoder.matches(empCommand.getOldPw(), authInfo.getUserPw())) {
			if(empCommand.getEmpPw() == "" || !empCommand.isEmpPwEqualsEmpPwCon() ) {
				result.rejectValue("empPw", "employeeCommand.empPw", 
						"비밀번호확인이 틀립니다.");
				return "thymeleaf/empShip/empPassCon";
			}
			String newPw = passwordEncoder.encode(empCommand.getEmpPw());
			EmployeeDTO dto = new EmployeeDTO();
			dto.setEmpId(authInfo.getUserId());
			dto.setEmpPw(newPw);
			employeeShipMapper.updatePassword(dto);
			authInfo.setUserPw(newPw);
			return "redirect:empDetail";
		}else {
			result.rejectValue("oldPw", "employeeCommand.oldPw", "현재 비밀번호가 틀립니다.");
			return "thymeleaf/empShip/empPassCon";
		}
	}
}