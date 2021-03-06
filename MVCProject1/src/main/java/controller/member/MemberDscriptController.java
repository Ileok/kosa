package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberDscriptController {
	public void execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 로그인 사용자 아이디로 정보 확인
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo"); // 임시로 임의의 사용자 아이디 사용
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.selectUser(authInfo.getUserId());
		request.setAttribute("memberDTO", dto);
	}
}