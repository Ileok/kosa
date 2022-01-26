package controller.member;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

public class MemberWriteController {
	public void execute(HttpServletRequest req) {
		String memNum = req.getParameter("memNum");
		String memName = req.getParameter("memName");
		String memId = req.getParameter("memId");
		String memPw = req.getParameter("memPw");
		String memPhone1 = req.getParameter("memPhone1");
		String memPhone2 = req.getParameter("memPhone2");
		String memAddr = req.getParameter("memAddr");
		String memEmail = req.getParameter("memEmail");
		String memGender = req.getParameter("memGender");
		
		// 문자열 date로 변경
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date regidate = null;
		
		String memRegiDate = req.getParameter("memRegiDate");
		try {
			regidate = sdf.parse(memRegiDate);
		} catch (Exception e) {e.printStackTrace();}
		
		String memBirth = req.getParameter("memBirth");
		String[] dateTime = memBirth.split("T");
		Date memBirthDay = null;
		try {
			memBirthDay = sdf.parse(dateTime[0]);
		} catch(Exception e) {e.printStackTrace();}
		Timestamp birthDay = new Timestamp(memBirthDay.getTime());
		
		MemberDTO dto = new MemberDTO();
		dto.setMemAddr(memAddr);
		dto.setMemBirth(birthDay);
		dto.setMemEmail(memEmail);
		dto.setMemGender(memGender);
		dto.setMemId(memId);
		dto.setMemName(memName);
		dto.setMemNum(memNum);
		dto.setMemPhone1(memPhone1);
		dto.setMemPhone2(memPhone2);
		dto.setMemRegiDate(regidate);
		dto.setMemPw(memPw);
		
		MemberDAO dao = new MemberDAO();
		dao.memberInsert(dto);
	}
}
