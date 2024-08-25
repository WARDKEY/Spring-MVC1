package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 스프링 빈 등록하는데 빈의 이름을 url 이름으로 지정
@Component("/springmvc/old-controller")
public class OldController  implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OldController.handleRequest");

		// 컨트롤러는 정상적으로 호출됐는데 뷰가 존재하지 않아서 페이지가 뜨지 않음(application.properties에 설정 넣어서 뜸)
		return new ModelAndView("new-form");
	}
}
