package sale_manager.controllers.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taikhoan")
public class AdministratorPostController {
	@GetMapping("/dangky")
	public ResponseEntity<Map<String, Object>> registerAcc(){
		Map<String, Object> response = new HashMap<>();
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	@GetMapping("/admin")
	public ResponseEntity<Map<String, Object>> login(){
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Xin chào quản trị viên");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
