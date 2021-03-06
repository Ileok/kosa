package kosaShoppingMall.service.empJoin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.domain.EmployeeDTO;
import kosaShoppingMall.mapper.EmployeeShipMapper;

@Component
@Service
public class EmpInfoService {
	@Autowired
	EmployeeShipMapper empShipMapper;
	public void execute(Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		String empId = authInfo.getUserId();
		EmployeeDTO dto = empShipMapper.selectOne(empId);
		model.addAttribute("employeeCommand", dto);
	}
}
