package hellot.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;

import hellot.servlet.domain.member.Member;
import hellot.servlet.domain.member.MemberRepository;
import hellot.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberSaveControllerV1 implements ControllerV1 {

	MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		//Model에 데이터를 보관한다.
		request.setAttribute("member", member);

		String viewPath = "/WEB-INF/views/save-result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		// 내부에서 호출
		dispatcher.forward(request, response);
	}
}
