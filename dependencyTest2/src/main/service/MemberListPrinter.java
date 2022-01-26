package main.service;

import java.util.Collection;

import main.DTO.MemberDAO;
import main.DTO.MemberDTO;

public class MemberListPrinter {
	MemberDAO memberDao;
	MemberPrinter print;
	public MemberListPrinter(MemberDAO memberDao, MemberPrinter print) {
		this.memberDao = memberDao;
		this.print = print;
	}
	public void printAll() {
		Collection<MemberDTO> lists = memberDao.selectAll();
		for(MemberDTO dto : lists) {
			print.print(dto);
		}
	}
}