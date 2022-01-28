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

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.service.member.MemberDeleteService;
import kosaShoppingMall.service.member.MemberInfoService;
import kosaShoppingMall.service.member.MemberListService;
import kosaShoppingMall.service.member.MemberModifyService;
import kosaShoppingMall.service.member.MemberNumService;
import kosaShoppingMall.service.member.MemberUpdateService;
import kosaShoppingMall.service.member.MemberWriteService;

@Controller
@RequestMapping("mem")
public class MemberController {
	@Autowired
	MemberNumService memberNumService;
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberModifyService memberModifyService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@ModelAttribute
	MemberCommand setMemberCommand() {
		return new MemberCommand();
	}
	
	@RequestMapping("memDelete")
	   public String memberDelete(@RequestParam(value="num") String memNum, Model model) {
	      memberDeleteService.execute(memNum, model);
	      //return "redirect:memList"; : ajax는 바로 redirect 불가능. next page 무적권 필요.
	      return "thymeleaf/member/memberdel";
	   }
	
//	@RequestMapping(value="memberDelete", method = RequestMethod.GET)
//	public String memberDelete(MemberCommand memberCommand, Model model) {
//		String path = memberDeleteService.execute(memberCommand, model);
//		return path;
//	}
	@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberModify";
		}
		
		memberUpdateService.execute(memberCommand);
		return "redirect:/";
	}
	@RequestMapping("memberModify") 
	public String memberModify(@RequestParam(value="memNum") String memNum, Model model) {
		memberModifyService.execute(memNum, model);
		return "thymeleaf/member/memberModify";
	}
	@RequestMapping("memberInfo/{num}")
	public String memberInfo(@PathVariable(value="num") String memNum, Model model) {
		memberInfoService.execute(memNum, model);
		return "thymeleaf/member/memberInfo";
	}
	@RequestMapping("memList")
	public String memberList(Model model) {
		memberListService.execute(model);
		return "thymeleaf/member/memberList";
	}
	@RequestMapping(value="memberRegist", method=RequestMethod.GET)
	public String memberForm(MemberCommand memberCommand) {
		memberNumService.execute(memberCommand);
		return "thymeleaf/member/memberForm";
	}
	@RequestMapping(value="memberRegist", method=RequestMethod.POST)
	public String memberRegist(@Validated MemberCommand memberCommand, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		
		if(!memberCommand.isMemPwEqualsMemPwCon()) {
			result.rejectValue("memPw", "memberCommand.memPw", "비밀번호 확인이 다릅니다."); 
			return "thymeleaf/member/memberForm";
		}
		memberWriteService.execute(memberCommand);
		return "redirect:/";
	}
}
