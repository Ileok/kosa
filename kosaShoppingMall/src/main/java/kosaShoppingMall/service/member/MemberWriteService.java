package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberWriteService {

	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(MemberCommand memberCommand) {
		
			String memPw = passwordEncoder.encode(memberCommand.getMemPw());
			System.out.println(memPw);
			MemberDTO dto = new MemberDTO();
			dto.setMemNum(memberCommand.getMemNum());
			dto.setMemId(memberCommand.getMemId());
			dto.setMemPw(memPw);
			dto.setMemName(memberCommand.getMemName());
			dto.setMemAddr(memberCommand.getMemAddr());
			dto.setMemRegist(memberCommand.getMemRegist());
			dto.setGender(memberCommand.getGender());
			dto.setMemPhone(memberCommand.getMemPhone());
			dto.setMemBirth(memberCommand.getMemBirth());
			dto.setMemEmail(memberCommand.getMemEmail());
			
			memberMapper.memInsert(dto);
			
		}
	}