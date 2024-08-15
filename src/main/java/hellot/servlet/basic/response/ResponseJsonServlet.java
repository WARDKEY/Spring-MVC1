package hellot.servlet.basic.response;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import hellot.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Content-Type: application.json
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		HelloData helloData = new HelloData();
		helloData.setUsername("seo");
		helloData.setAge(25);

		//{"username":"seo", "age": 25}
		String result = objectMapper.writeValueAsString(helloData);
		response.getWriter().write(result);
	}
}
