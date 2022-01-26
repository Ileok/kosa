package kosaShoppingMall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping(value="empDelete", method = RequestMethod.GET)
	public String empDelete(EmployeeCommand employeeCommand, Model model) {
		String path = employeeDeleteService.execute(employeeCommand,model);
		return path;
	}
	
	@RequestMapping(value = "empUpdate", method = RequestMethod.POST)
	public String empUpdate(EmployeeCommand employeeCommand) {
		employeeUpdateService.execute(employeeCommand);
		return "redirect:empInfo?id="+employeeCommand.getEmpId();
	}
	
	@RequestMapping("empModify") 
	public String empModify(@RequestParam(value="empId") String empId, @RequestParam(value="empPw") String empPw, Model model) {
		String path = employeeModifyService.execute(empId, empPw, model);
		return path;
		
	}
	@RequestMapping("empInfo")
	public String empInfo(@RequestParam(value="id") String empId, 
			Model model) {
		employeeInfoService.execute(empId, model);
		return "thymeleaf/employee/empInfo";
		
	}
	@RequestMapping("empList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/empList";
	}
	@RequestMapping(value = "empWrite", method = RequestMethod.GET)
	public String empWrite1() {
		return "thymeleaf/employee/empForm";
	}
	@RequestMapping(value = "empWrite", method = RequestMethod.POST)
	public String empWrite(EmployeeCommand employeeCommand) {
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			return "thymeleaf/employee/empForm";
		}
		employeeWriteService.execute(employeeCommand); 
		return "redirect:/";
	}
}
