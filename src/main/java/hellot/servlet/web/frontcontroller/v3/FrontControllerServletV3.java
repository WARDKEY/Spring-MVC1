package hellot.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hellot.servlet.web.frontcontroller.ModelView;
import hellot.servlet.web.frontcontroller.MyView;
import hellot.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 경로 뒤에 *은 정규 표현식으로 뭐든 올 수 있다.
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

	// key는 url, value는 controller
	protected Map<String, ControllerV3> controllerMap = new HashMap<>();

	// 생성자를 통해 호출시 각각의 키에 해당하는 url을 따라 컨트롤러들이 생성
	public FrontControllerServletV3() {
		controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV3.service");

		// /front-controller/v1/members/new-form와 같은 주소를 바로 받을 수 있다.
		String requestURI = request.getRequestURI();

		// 해당 맵의 url을 통해 컨트롤러 생성
		ControllerV3 controller = controllerMap.get(requestURI);
		// 만약 해당 키에 대한 값이 없으면(url이 유효하지 않으면) 404 Not Found
		if (controller == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// paraMap을 넘겨줘야 됨

		Map<String, String> paraMap = createParamMap(request);

		// 정상적으로 동작했을 때(요청이 유효할 경우)
		ModelView mv = controller.process(paraMap);

		// new-form
		String viewName = mv.getViewName();// 논리 이름 new-form

		// 논리 이름을 물리 이름으로 바꾸고 MyView 반환
		MyView view = viewResolver(viewName);

		view.render(mv.getModel(),request, response);

	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}

	// 모든 파라미터의 이름을 가져온 다음 이름을 키로 하여 모든 파라미터 다 가져옴
	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String , String > paraMap = new HashMap<>();
		request.getParameterNames().asIterator().forEachRemaining(paraName -> paraMap.put(paraName, request.getParameter(paraName)));
		return paraMap;
	}
}
