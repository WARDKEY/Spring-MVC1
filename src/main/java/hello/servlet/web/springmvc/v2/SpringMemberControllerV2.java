package hello.servlet.web.springmvc.v2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("springmvc/v2/members")
public class SpringMemberControllerV2 {

	private MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping("/new-form")
	public ModelAndView newForm() {
		return new ModelAndView("new-form");
	}

	@RequestMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
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

	// springmvc/v2/members
	@RequestMapping
	public ModelAndView members() {
		List<Member> members = memberRepository.findAll();

		ModelAndView mv = new ModelAndView("members");
		// members라는 이름으로 뷰로 넣어줌
		// mv.getModel().put("members", members);
		mv.addObject("members", members);
		return mv;
	}
}
