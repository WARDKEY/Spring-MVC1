package hellot.servlet.web.frontcontroller.v4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hellot.servlet.web.frontcontroller.ModelView;
import hellot.servlet.web.frontcontroller.MyView;
import hellot.servlet.web.frontcontroller.v3.ControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hellot.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hellot.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hellot.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 경로 뒤에 *은 정규 표현식으로 뭐든 올 수 있다.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

	// key는 url, value는 controller
	protected Map<String, ControllerV4> controllerMap = new HashMap<>();

	// 생성자를 통해 호출시 각각의 키에 해당하는 url을 따라 컨트롤러들이 생성
	public FrontControllerServletV4() {
		controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV4.service");

		// /front-controller/v1/members/new-form와 같은 주소를 바로 받을 수 있다.
		String requestURI = request.getRequestURI();

		// 해당 맵의 url을 통해 컨트롤러 생성
		ControllerV4 controller = controllerMap.get(requestURI);
		// 만약 해당 키에 대한 값이 없으면(url이 유효하지 않으면) 404 Not Found
		if (controller == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// model도 생성(프론트 컨트롤러에서 직접 모델 제공)
		Map<String, Object> model = new HashMap<>();

		// paraMap을 넘겨줘야 됨
		Map<String, String> paraMap = createParamMap(request);

		// 정상적으로 동작했을 때(요청이 유효할 경우)
		String viewName = controller.process(paraMap, model);

		// 논리 이름을 물리 이름으로 바꾸고 MyView 반환
		MyView view = viewResolver(viewName);

		view.render(model,request, response);

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
