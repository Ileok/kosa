package controller.member;

import javax.servlet.http.HttpServletRequest;

import model.DAO.MemberDAO;

public class MemberNumberController {
	public void execute(HttpServletRequest req) {
		MemberDAO dao = new MemberDAO();
		String memberNum = dao.numberGenerate();
		req.setAttribute("memberNum", memberNum);
	}
}
