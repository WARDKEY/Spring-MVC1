package hello.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFormControllerV1 implements ControllerV1 {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/views/new-form.jsp";
		// 컨트롤러에서 뷰로 이동할 때 사용
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		// servlet에서 jsp 호출
		dispatcher.forward(request, response);
	}
}
