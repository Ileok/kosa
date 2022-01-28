package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosaShoppingMall.command.EmployeeCommand;
import kosaShoppingMall.service.employee.EmployeeDeleteService;
import kosaShoppingMall.service.employee.EmployeeInfoService;
import kosaShoppingMall.service.employee.EmployeeListService;
import kosaShoppingMall.service.employee.EmployeeModifyService;
import kosaShoppingMall.service.employee.EmployeeUpdateService;
import kosaShoppingMall.service.employee.EmployeeWriteService;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	@Autowired
	EmployeeWriteService employeeWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeInfoService employeeInfoService;
	@Autowired
	EmployeeModifyService employeeModifyService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	
	@ModelAttribute
	EmployeeCommand setEmployeeCommand() {
		return new EmployeeCommand();
	}
	
	@RequestMapping("empDelete")
	public String empDelete(@RequestParam(value="id") String empId, Model model) {
		employeeDeleteService.execute(empId, model);
	    //return "redirect:memList"; : ajax는 바로 redirect 불가능. next page 무적권 필요.
	    return "thymeleaf/employee/empDel";
	}
	
//	@RequestMapping(value="empDelete", method = RequestMethod.GET)
//	public String empDelete(EmployeeCommand employeeCommand, Model model) {
//		String path = employeeDeleteService.execute(employeeCommand,model);
//		return path;
//	}
	
	@RequestMapping(value = "empUpdate", method = RequestMethod.POST)
	public String empUpdate(EmployeeCommand employeeCommand, Model model) {
		String path = employeeUpdateService.execute(employeeCommand, model);
		return path;
	}
	
	@RequestMapping("empModify") 
	public String empModify(@RequestParam(value="empId") String empId, @RequestParam(value="empPw") String empPw, Model model) {
		String path = employeeModifyService.execute(empId, empPw, model);
		return path;
		
	}
	@RequestMapping("empInfo/{id}")
	public String empInfo(@PathVariable(value="id") String empId, 
			Model model) {
		employeeInfoService.execute(empId, model);
		return "thymeleaf/employee/empInfo";
		
	}
	@RequestMapping("empList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/empList";
	}
	@RequestMapping("empJoin")
	public String empJoin() {
		return "thymeleaf/employee/empForm";
	}
	@RequestMapping(value = "empWrite", method = RequestMethod.GET)
	public   String empWrite1() {
		return "thymeleaf/employee/empForm";
	}
	@RequestMapping(value = "empWrite", method = RequestMethod.POST)
	public String empWrite(@Validated EmployeeCommand employeeCommand,
			BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/employee/empForm";
		}
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호 확인이 다릅니다.");
			return "thymeleaf/employee/empForm";
		}
		employeeWriteService.execute(employeeCommand);
		return "redirect:/";
	}
}