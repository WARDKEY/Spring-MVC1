package hellot.servlet.web.frontcontroller.v2;

import java.io.IOException;

import hellot.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ControllerV2 {

	// JSP로 이동
	MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
