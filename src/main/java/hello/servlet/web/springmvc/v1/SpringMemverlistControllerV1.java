package hello.servlet.web.springmvc.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;

@Controller
public class SpringMemverlistControllerV1 {

	protected MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("springmvc/v1/members")
	public ModelAndView process() {
		List<Member> members = memberRepository.findAll();

		ModelAndView mv = new ModelAndView("members");
		// members라는 이름으로 뷰로 넣어줌
		// mv.getModel().put("members", members);
		mv.addObject("members", members);
		return mv;
	}
}
