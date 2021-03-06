package kosaShoppingMall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.domain.AuthInfo;
import kosaShoppingMall.service.empJoin.EmpInfoService;
import kosaShoppingMall.service.empJoin.EmpPasswordService;
import kosaShoppingMall.service.empJoin.EmpUpdateService;

@Controller
@RequestMapping("empMypage")
public class EmpMyPageController {
	@Autowired
	EmpInfoService empInfoService ;
	@Autowired
	EmpUpdateService empUpdateService ;
	@Autowired
	EmpPasswordService empPasswordService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@ModelAttribute
	public EmployeeCommand getEmployeeCommand() {
		return new EmployeeCommand();
	}
	
	@RequestMapping(value="empPassModify",method=RequestMethod.POST)
	public String empPassModify(EmployeeCommand employeeCommand, 
			BindingResult result , HttpSession session ) {
		String path = empPasswordService.execute(employeeCommand,result,session );
		return path;
	}
	@RequestMapping(value="empPasswordPro",method=RequestMethod.POST)
	public String empPasswordPro(@RequestParam(value="empPw") String pw,
			HttpSession session,Model model) {
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		if(passwordEncoder.matches(pw, authInfo.getUserPw())) {
			return "thymeleaf/empShip/empPassCon";
		}else {
			model.addAttribute("err_pw", "비밀번호가 틀립니다.");
			return "thymeleaf/empShip/empPass";
		}
	}
	@RequestMapping("empPass")
	public String empPass() {
		return "thymeleaf/empShip/empPass";
	}
	@RequestMapping(value="empInfoUpdate",method=RequestMethod.POST)
	public String empInfoUpdate(EmployeeCommand employeeCommand
			, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/empShip/empModify";
		}
		String path = empUpdateService.execute(employeeCommand, session, result);
		return path;
	}
	@RequestMapping("empInfoModify")
	public String empInfoModify(Model model, HttpSession session) {
		empInfoService.execute(model, session);
		return "thymeleaf/empShip/empModify";
	}
	@RequestMapping("empDetail")
	public String memInfo(Model model, HttpSession session) {
		empInfoService.execute(model, session);
		return "thymeleaf/empShip/empDetail";
	}
}