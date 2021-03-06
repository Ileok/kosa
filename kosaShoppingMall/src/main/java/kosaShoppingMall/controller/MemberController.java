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
import kosaShoppingMall.service.member.MemberDetailService;
import kosaShoppingMall.service.member.MemberListService;
import kosaShoppingMall.service.member.MemberModifyService;
import kosaShoppingMall.service.member.MemberNumService;
import kosaShoppingMall.service.member.MemberWriteService;

@Controller
@RequestMapping("mem")
public class MemberController {
	@Autowired
	MemberNumService memberNumberService;
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberModifyService memberModifyService ;
	@Autowired
	MemberDeleteService memberDeleteService ;

	@ModelAttribute
	MemberCommand setMemberCommand() {
		return new MemberCommand();
	}
	
	@RequestMapping(value = "memberDelete")
	public String memberDelete(@RequestParam(value = "num") String memberNum,
			Model model) {
		memberDeleteService.execute(memberNum , model);
		//return "thymeleaf/member/memberdel";
		return "member/memberdel";
	}
	@RequestMapping(value="memberModify" , method = RequestMethod.GET)
	public String memberModify(@RequestParam(value="memberNum") 
				String memberNum ,Model model) {
		memberDetailService.execute(memberNum, model);
		//return "thymeleaf/member/memberUpdate";
		return "member/memberUpdate";
	}
	@RequestMapping(value="memberModify" , method = RequestMethod.POST)
	public String memberUpdate(@Validated MemberCommand memberCommand, 
			BindingResult result) {
		if (result.hasErrors()) {
			//return "thymeleaf/member/memberUpdate";
			return "member/memberUpdate";
		}
		memberModifyService.execute(memberCommand);
		return "redirect:memberDetail/"+memberCommand.getMemberNum();
	}
	@RequestMapping(value="memberDetail/{num}")
	public String memberDetail(@PathVariable(value = "num") String memberNum,
			Model model) {
		memberDetailService.execute(memberNum, model);
		//return "thymeleaf/member/memberDetail";
		return "member/memberDetail";
	}
	@RequestMapping("memList")
	public String memList(Model model) {
		memberListService.execute(model);
		//return "thymeleaf/member/memberList";
		return "member/memberList";
	}
	@RequestMapping(value="memberRegist" ,method = RequestMethod.GET)
	public String memberForm(MemberCommand memberCommand,Model model) {
		memberNumberService.execute(memberCommand,model);
		return "thymeleaf/member/memberForm";
		//return "member/memberForm";
	}
	@RequestMapping(value="memberRegist" ,method = RequestMethod.POST)
	public String memberFrom(@Validated MemberCommand memberCommand, 
			BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/member/memberForm";
			//return "member/memberForm";
		}
		
		if(!memberCommand.isMemberPwEqualsMemberPwCon()) {
			result.rejectValue("memberPw", "memberCommand.memberPw", 
					"???????????? ????????? ????????????.");
			//return "thymeleaf/member/memberForm";
			return "member/memberForm";
		}
		memberWriteService.execute(memberCommand);
		return "redirect:memList";
	}
}
