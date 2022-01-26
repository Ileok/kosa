package controller.member;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;

public class MemberDeleteController {
	public void execute(HttpServletRequest req) {
		String num = req.getParameter("num");
		MemberDAO dao = new MemberDAO();
		dao.memberDelete(num);
	}
}