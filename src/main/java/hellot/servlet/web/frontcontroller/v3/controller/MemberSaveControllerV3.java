package hellot.servlet.web.frontcontroller.v3.controller;

import java.util.Map;

import hellot.servlet.domain.member.Member;
import hellot.servlet.domain.member.MemberRepository;
import hellot.servlet.web.frontcontroller.ModelView;
import hellot.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberSaveControllerV3 implements ControllerV3 {

	protected MemberRepository memberRepository = MemberRepository.getInstance();

	@Override
	public ModelView process(Map<String, String> paramMap) {
		String username = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));

		Member member = new Member(username, age);
		memberRepository.save(member);

		// 논리적인 경로 이름을 지정
		ModelView mv = new ModelView("save-result");
		mv.getModel().put("member", member);
		return mv;

	}
}
