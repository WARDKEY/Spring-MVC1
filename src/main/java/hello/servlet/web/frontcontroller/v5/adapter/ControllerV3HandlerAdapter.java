package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
	@Override
	public boolean supports(Object handler) {
		// 넘어온 컨트롤러가 v3면 true를 반환
		return (handler instanceof ControllerV3);
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		ServletException,
		IOException {
		// handler를 ControllerV3로 캐스팅
		// supports를 먼저 호출하여 컨트롤러를 거르고 호출하기 때문에 문제 없음
		ControllerV3 controller = (ControllerV3)handler;

		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);

		return mv;
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String , String > paraMap = new HashMap<>();
		request.getParameterNames().asIterator().forEachRemaining(paraName -> paraMap.put(paraName, request.getParameter(paraName)));
		return paraMap;
	}

}
