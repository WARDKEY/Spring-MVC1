package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 스프링이 자동으로 스프링 빈으로 등록한다.
// 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다.
@Controller

// 위와 같은 방식
// @Component	// 컴포넌트 스캔을 통해 스프링 빈으로 등록
// @RequestMapping
public class SpringMemberFormControllerV1 {

	// 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
	@RequestMapping("/springmvc/v1/members/new-form")
	public ModelAndView process() {
		return new ModelAndView("new-form");
	}
}
