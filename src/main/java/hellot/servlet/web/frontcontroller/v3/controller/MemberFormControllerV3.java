package hellot.servlet.web.frontcontroller.v3.controller;

import java.util.Map;

import hellot.servlet.web.frontcontroller.ModelView;
import hellot.servlet.web.frontcontroller.v3.ControllerV3;

public class MemberFormControllerV3 implements ControllerV3 {
	@Override
	public ModelView process(Map<String, String> paramMap) {
		// 논리적인 경로 이름을 지정
		return new ModelView("new-form");
	}
}
