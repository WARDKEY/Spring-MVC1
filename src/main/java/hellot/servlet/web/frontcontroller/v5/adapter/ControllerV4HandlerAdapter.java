package hellot.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hellot.servlet.web.frontcontroller.ModelView;
import hellot.servlet.web.frontcontroller.v4.ControllerV4;
import hellot.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV4);
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		ServletException,
		IOException {
		ControllerV4 controller = (ControllerV4)handler;
		Map<String, String> paramMap = createParamMap(request);
		HashMap<String, Object> model = new HashMap<>();
		String viewName = controller.process(paramMap, model);

		// ModelView를 생성한 뒤
		ModelView mv = new ModelView(viewName);
		// ModelView를 설정하여 반환
		mv.setModel(model);

		return mv;
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String , String > paraMap = new HashMap<>();
		request.getParameterNames().asIterator().forEachRemaining(paraName -> paraMap.put(paraName, request.getParameter(paraName)));
		return paraMap;
	}
}
