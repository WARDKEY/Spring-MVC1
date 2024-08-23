package hellot.servlet.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.tags.shaded.org.apache.xpath.objects.XString;

import hellot.servlet.web.frontcontroller.ModelView;
import hellot.servlet.web.frontcontroller.MyView;
import hellot.servlet.web.frontcontroller.v3.ControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hellot.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hellot.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hellot.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hellot.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hellot.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hellot.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServlerV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServlerV5 extends HttpServlet {
	// Object 위치에 어떤 컨트롤러든 들어갈 수 있다.
	private final Map<String, Object> handlerMappingMap = new HashMap<>();

	// 어댑터(컨트롤러)가 여러 개 담겨 있고 그 중 하나를 꺼내서 써야되기 때문에 리스트 생성
	private final List<MyHandlerAdapter> handelerAdapters = new ArrayList<>();

	public FrontControllerServlerV5() {
		initHandlerMappingMap();	// 핸들러 매핑 초기화
		initHandlerAdapters();	// 어댑터 초기화
	}

	private void initHandlerMappingMap() {
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

		// V4 추가
		handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
	}

	private void initHandlerAdapters() {
		handelerAdapters.add(new ControllerV3HandlerAdapter());
		handelerAdapters.add(new ControllerV4HandlerAdapter());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServletV3.service");

		// MemberFormControllerV3 찾음
		Object handler = getHandler(request);

		// 만약 해당 키에 대한 값이 없으면(url이 유효하지 않으면) 404 Not Found
		if (handler == null){
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// ControllerV3HandlerAdapter
		MyHandlerAdapter adapter = getHandlerAdapter(handler);

		ModelView mv = adapter.handle(request, response, handler);

		// new-form
		String viewName = mv.getViewName();// 논리 이름 new-form

		// 논리 이름을 물리 이름으로 바꾸고 MyView 반환
		MyView view = viewResolver(viewName);

		view.render(mv.getModel(),request, response);
	}

	private MyHandlerAdapter getHandlerAdapter(Object handler) {


		for (MyHandlerAdapter adapter  : handelerAdapters) {
			if(adapter.supports(handler)){
				return adapter;
			}
		}
		throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
	}

	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return handlerMappingMap.get(requestURI);

	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}
