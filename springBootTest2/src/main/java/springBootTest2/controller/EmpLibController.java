package springBootTest2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.EmpLibCommand;
import springBootTest2.service.emplib.EmpLibDeleteService;
import springBootTest2.service.emplib.EmpLibInfoService;
import springBootTest2.service.emplib.EmpLibListService;
import springBootTest2.service.emplib.EmpLibModifyService;
import springBootTest2.service.emplib.EmpLibNumberService;
import springBootTest2.service.emplib.EmpLibUpdateService;
import springBootTest2.service.emplib.EmpLibWriteService;
import springBootTest2.service.emplib.FileDownService;

@Controller
@RequestMapping("emplib")
public class EmpLibController {
	@Autowired
	EmpLibWriteService emplibWriteService;
	@Autowired
	EmpLibNumberService emplibNumberService;
	@Autowired
	EmpLibListService emplibListService;
	@Autowired
	EmpLibInfoService emplibInfoService;
	@Autowired
	EmpLibModifyService emplibModifyService;
	@Autowired
	EmpLibUpdateService emplibUpdateService;
	@Autowired
	EmpLibDeleteService empLibDeleteService;
	@Autowired
	FileDownService fileDownService;
	
	@RequestMapping("fileDown")
	public void fileDown(@RequestParam(value="sfile") String sfileName,
			@RequestParam(value="ofile") String ofileName,
			HttpServletRequest request, HttpServletResponse response) {
		fileDownService.fileDownLoad(sfileName,ofileName,request,response);
	}
	
	@RequestMapping(value = "emplibDelete", method = RequestMethod.GET)
	public String EmpLibDelete(EmpLibCommand empLibCommand, Model model,
			HttpSession session) {
		String path = empLibDeleteService.execute(empLibCommand, model, session);
		return path;
	}
	@RequestMapping(value = "emplibUpdate",  method = RequestMethod.POST)
	public String EmpLibModify(EmpLibCommand emplibCommand, HttpSession session, Model model) {
		emplibUpdateService.execute(emplibCommand, session, model);
		return "redirect:emplibInfo?num="+emplibCommand.getLibNum();
	}
	@RequestMapping("emplibModify")
	public String EmpLibModify(@RequestParam(value="libNum") Integer libNum,
			@RequestParam(value="libPw") String libPw, Model model,
			HttpSession session) {
		String path = emplibModifyService.execute(libNum,libPw,model,session);
		return path;
	}
	@RequestMapping("emplibInfo")
	public String EmpLibInfo(@RequestParam(value="num") Integer libNum,
			Model model,HttpSession session) {
		emplibInfoService.execute(libNum, model, session);
		return "thymeleaf/emplib/emplibInfo";
	}
	@RequestMapping("emplibList")
	public String EmpLibList(Model model) {
		emplibListService.execute(model);
		return "thymeleaf/emplib/emplibList";
	}
	
	@RequestMapping("emplibForm")
	public String EmpLibForm(HttpSession session, Model model) {
		emplibNumberService.execute(session, model);
		return "thymeleaf/emplib/emplibForm";
	}
	
	@RequestMapping("emplibWrite")
	public String EmpLibWrite(EmpLibCommand emplibCommand, HttpServletRequest request) {
		emplibWriteService.execute(emplibCommand, request);
		return "redirect:emplibList";
	}
}
