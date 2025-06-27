package sale_manager.controllers.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sale_manager.dtos.RequestToken;
import sale_manager.securities.JwtService;
import sale_manager.securities.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api")
public class RequestRefreshToken {
	@Autowired private UserDetailsServiceImpl userDetailsServiceImpl; 
	@Autowired private JwtService jwtSer;
	@PostMapping("/refreshtoken")
	public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody RequestToken request){
		String refreshToken = request.getRefreshToken();
		Map<String, Object> response = new HashMap<>();
		String username= jwtSer.extractUsername(refreshToken);
		
		try {
			if(!jwtSer.isTokenValid(refreshToken, username)) {
				response.put("message", "RefreshToken không hợp lệ hoặc đã hết hạn");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
			String newAccessToken = jwtSer.generateToken(userDetails);
			response.put("accessToken", newAccessToken);
			response.put("role", userDetails.getAuthorities());
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		catch(Exception e){
			response.put("message", "Lỗi khi xử lý refreshtoken");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}		
}
