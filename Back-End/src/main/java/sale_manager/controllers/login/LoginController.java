package sale_manager.controllers.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sale_manager.dtos.LoginDto;
import sale_manager.securities.JwtService;

@RestController
@RequestMapping("/taikhoan")
public class LoginController {
	@Autowired private AuthenticationManager authManager;
	@Autowired private JwtService jwtService;
	// Get method login
	@GetMapping("/dangnhap")
	public ResponseEntity<Map<String, Object>> login(){
		Map<String, Object> response = new HashMap<>();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	// Post method login
	@PostMapping("/dangnhap")	
	public ResponseEntity<Map<String, Object>> login1(@Valid @RequestBody LoginDto account, BindingResult bindingresult){		
		Map<String, Object> response = new HashMap<>();		
			if(bindingresult.hasErrors()) {
				response.put("success", false);
				response.put("message", bindingresult.getFieldError().getDefaultMessage());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		try {
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());					
			Authentication authentication = authManager.authenticate(authToken);
	        UserDetails userDetails =   (UserDetails) authentication.getPrincipal();
	        String accessToken = jwtService.generateToken(userDetails);
	        String refreshToken = jwtService.refreshToken(userDetails);
	        response.put("success", true);
	        response.put("message", "Đăng nhập thành công");
	        response.put("accessToken", accessToken);
	        response.put("refreshToken", refreshToken);      
	        response.put("username", userDetails.getUsername());
	        response.put("role", userDetails.getAuthorities());
	        return ResponseEntity.ok(response);

	    } catch (BadCredentialsException e) {
	        response.put("success", false);
	        response.put("message", "Tài khoản hoặc mật khẩu không chính xác");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	    } catch (DisabledException e) {
	        response.put("success", false);
	        response.put("message", "Tài khoản của bạn đã bị khóa");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "Lỗi hệ thống");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	// Put method avatar
	@PostMapping("/avatar")
	public ResponseEntity<Map<String, Object>> avatarUrl(){
		Map<String, Object> response = new HashMap<>();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
