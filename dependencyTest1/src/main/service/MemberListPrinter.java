package main.service;

import java.util.Collection;

import main.DTO.MemberDAO;
import main.DTO.MemberDTO;

public class MemberListPrinter {
	MemberDAO memberDao = new MemberDAO();
	MemberPrinter print= new MemberPrinter();
	public void printAll() {
		Collection<MemberDTO> lists = memberDao.selectAll();
		for(MemberDTO dto : lists) {
			print.print(dto);
		}
	}
}