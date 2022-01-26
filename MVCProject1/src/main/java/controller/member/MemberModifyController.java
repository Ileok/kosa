package controller.member;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

public class MemberModifyController {
	public void execute(HttpServletRequest req) {
		String memNum = req.getParameter("memNum");
		String memName = req.getParameter("memName");
		String memId = req.getParameter("memId");
		String memPhone1 = req.getParameter("memPhone1");
		String memPhone2 = req.getParameter("memPhone2");
		String memAddr = req.getParameter("memAddr");
		String memEmail = req.getParameter("memEmail");
		String memGender = req.getParameter("memGender");
		
		// 문자열 date로 변경
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date regiDate = null;
		String memRegiDate = req.getParameter("memRegiDate");
        try {
        	regiDate = sdf.parse(memRegiDate);
        }catch(Exception e) {e.printStackTrace();}
		
		String memBirth = req.getParameter("memBirth");
		// 문자열 Date로 변경한 후에 Timestamp로 변경
		String [] dateTime = memBirth.split("T");
		// 2002-12-15T01:25
		Date memBirthDay = null;
		try {
			memBirthDay = sdf.parse(dateTime[0]);
		}catch(Exception e) {e.printStackTrace();}
		Timestamp birthDay = 
				new Timestamp(memBirthDay.getTime());
		
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
		dto.setMemRegiDate(regiDate);

		
		MemberDAO dao = new MemberDAO();
		dao.memberUpdate(dto);
	}
}