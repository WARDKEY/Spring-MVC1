package hellot.servlet.web.frontcontroller.v1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hellot.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hellot.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hellot.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 경로 뒤에 *은 정규 표현식으로 뭐든 올 수 있다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

	// key는 url, value는 controller
	protected Map<String, ControllerV1> controllerMap = new HashMap<>();

	// 생성자를 통해 호출시 각각의 키에 해당하는 url을 따라 컨트롤러들이 생성
	public FrontControllerServletV1() {
		controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
		controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
		controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV1.service");

		// /front-controller/v1/members/new-form와 같은 주소를 바로 받을 수 있다.
		String requestURI = request.getRequestURI();

		// 해당 맵의 url을 통해 컨트롤러 생성
		ControllerV1 controller = controllerMap.get(requestURI);
		// 만약 해당 키에 대한 값이 없으면(url이 유효하지 않으면) 404 Not Found
		if (controller == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 정상적으로 동작했을 때(요청이 유효할 경우)
		controller.process(request, response);


	}
}
