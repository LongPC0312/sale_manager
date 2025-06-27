package sale_manager.controllers.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sale_manager.dtos.RegisterDto;
import sale_manager.models.TaiKhoanModel;
import sale_manager.services.impls.TaiKhoanService;

@RestController
@RequestMapping("/taikhoan/admin")
public class AdministratorPostController {
	@Autowired private TaiKhoanService tkSer;
	// Tạo tài khoản cho đối tượng sử dụng
	@PostMapping("/dangky")
	public ResponseEntity<Map<String, Object>> registerAcc(@Valid @RequestBody RegisterDto request, BindingResult bindingResult){
		Map<String, Object> response = new HashMap<>();
		if(bindingResult.hasErrors()) {
			response.put("success", false);
			response.put("message", bindingResult.getFieldError().getDefaultMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		try {
			Optional<TaiKhoanModel> existUsername = tkSer.findByUsername(request.getUsername());
			if(existUsername.isPresent()) {
				response.put("success", false);
				response.put("message", "Tài khoản đã tồn tại");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			}
			TaiKhoanModel newAccount = new TaiKhoanModel();
			newAccount.setUsername(request.getUsername());
			newAccount.setPassword(request.getPassword());
			newAccount.setRole(request.getRole());
			newAccount.setStatus(request.isStatus());
			tkSer.save(newAccount);
			response.put("success", true);
			response.put("message", "Tạo tài khoản thành công");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e) {
			response.put("success", false);
			response.put("message", "Có lỗi xảy ra khi tạo tài khoản");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}		
	}
	
}
