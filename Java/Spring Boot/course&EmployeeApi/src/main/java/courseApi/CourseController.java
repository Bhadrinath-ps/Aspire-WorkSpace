package courseApi;

//Session - 02

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	@RequestMapping("/hello")
	public String sayHi() {
		return "Hi";
	}
}
