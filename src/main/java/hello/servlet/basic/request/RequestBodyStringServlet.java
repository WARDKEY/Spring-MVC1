package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();	// 메시지 바디의 내용을 바이트 코드로 바로 얻을 수 있다.
		String messageBody = StreamUtils.copyToString(inputStream,
			StandardCharsets.UTF_8);// 바이트 코드를 String으로 변환, 어떤 형식을 사용할 지 알려줘야 됨

		System.out.println("messageBody = " + messageBody);	// 읽은 문자열을 콘솔에 출력

		response.getWriter().write("ok");
	}
}
