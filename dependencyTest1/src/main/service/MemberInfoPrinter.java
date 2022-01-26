package main.service;

import main.DTO.MemberDAO;
import main.DTO.MemberDTO;

public class MemberInfoPrinter {
	MemberDAO memberDao = new MemberDAO();
	MemberPrinter print= new MemberPrinter();
	public void execute(String email) {
		MemberDTO dto = memberDao.selectByEmail(email);
		if(dto == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		print.print(dto);
	}
}