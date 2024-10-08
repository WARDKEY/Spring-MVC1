package hello.servlet.web.springmvc.v1;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SpringMemberSaveControllerV1 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("springmvc/v1/members/save")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		// 논리적인 경로 이름을 지정
		ModelAndView mv = new ModelAndView("save-result");
		// mv.getModel().put("member", member);

		mv.addObject("member", member);
		return mv;
	}
}
