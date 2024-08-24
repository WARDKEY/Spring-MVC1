package hello.servlet.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")	// /hello로 접속할 경우 실행
public class HelloServlet extends HttpServlet {

	// 서블릿 호출되면 service 메서드 실행
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);

		String username = request.getParameter("username");	// 쿼리 파라미터의 값을 받아서 username의 변수에 저장
		System.out.println("username = " + username);

		// 아래 두 개는 헤더 정보로 들어감
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		// write를 하면 Http Body에 데이터가 들어감
		response.getWriter().write("hello " + username);

	}
}
