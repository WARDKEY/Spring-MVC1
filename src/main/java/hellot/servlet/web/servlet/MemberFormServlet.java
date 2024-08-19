package hellot.servlet.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import hellot.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

	// 싱글톤으로 생성
	MemberRepository memberRepository = MemberRepository.getInstance();

	// Http 응답으로 HTML이 나가야 됨
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter w = response.getWriter();
		w.write("<!DOCTYPE html>\n" +
			"<html>\n" +
			"<head>\n" +
			"    <meta charset=\"UTF-8\">\n" +
			"    <title>Title</title>\n" +
			"</head>\n" +
			"<form action=\"/servlet/members/save\" method=\"post\">\n" +
			"    username: <input type=\"text\" name=\"username\" />\n" +
			"    age:      <input type=\"text\" name=\"age\" />\n" +
			" <button type=\"submit\">전송</button>\n" + "</form>\n" +
			"</body>\n" +
			"</html>\n");
	}
}
