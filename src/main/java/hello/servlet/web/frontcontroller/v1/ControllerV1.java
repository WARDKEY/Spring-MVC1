package hello.servlet.web.frontcontroller.v1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 프론트 컨트롤러는 인터페이스에 의존하여 다형성으로 컨트롤러를 편하게 호출
public interface ControllerV1 {

	void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
