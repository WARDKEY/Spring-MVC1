package hello.servlet.web.frontcontroller.v3.controller;

import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberListControllerV3 implements ControllerV3 {

	protected MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public ModelView process(Map<String, String> paramMap) {
		List<Member> members = memberRepository.findAll();

		ModelView mv = new ModelView("members");
		// members라는 이름으로 뷰로 넣어줌
		mv.getModel().put("members", members);
		return mv;
	}
}
