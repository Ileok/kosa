package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
//	public String execute(MemberCommand memberCommand, Model model) {
//		String path = "thymeleaf/member/memberList";
//		String memPw = memberCommand.getMemPw();
//		MemberDTO dto = memberMapper.selectOne(memberCommand.getMemNum());
//		if(passwordEncoder.matches(memPw, dto.getMemPw())) {
//			Integer i = memberMapper.memDelete(memberCommand.getMemNum());
//			System.out.println(i + "개 행이 삭제되었습니다.");
//			path = "thymeleaf/member/memberList";
//		} else { 
//			model.addAttribute("errPw","비밀번호가 일치하지 않습니다.");
//			model.addAttribute("dto", dto);
//			path = "thymeleaf/member/memberInfo";
//		}
//		return path;
	
	public void execute(String memNum, Model model) {
	      Integer i = memberMapper.memDelete(memNum);
	      model.addAttribute("num", i);

	}
}
