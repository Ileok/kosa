package controller.member;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;
import model.DTO.MemberDTO;

public class MemberDetailController {
	public void execute(HttpServletRequest req) {
		String memNum = req.getParameter("num");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.selectOne(memNum);
		req.setAttribute("memberDTO", dto);
	}
}
