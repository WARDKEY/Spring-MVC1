package hellot.servlet.web.frontcontroller.v3;

import java.util.Map;

import hellot.servlet.web.frontcontroller.ModelView;

public interface ControllerV3 {
	ModelView process(Map<String, String> paramMap);
}
