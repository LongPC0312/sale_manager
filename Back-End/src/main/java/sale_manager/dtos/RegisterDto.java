package sale_manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
	@NotBlank(message= "Tài khoản không được bỏ trống ")
	@Size(min=6, max=18, message="Tài khoản có độ dài từ 6 đến 18 ký tự")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,18}$",
				message="Tài khoản phải có ít nhất 1 chữ thường, 1 chữ hoa, 1 số và không chứa ký tự đặc biệt")
	private String username;
	@NotBlank(message= "Mật khẩu không được bỏ trống ")
	@Size(min=6, max=18, message="Mật khẩu có độ dài từ 6 đến 18 ký tự")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,18}$",
				message="Mật khẩu phải có ít nhất 1 chữ thường, 1 chữ hoa, 1 số và không chứa ký tự đặc biệt")
	private String password;
	private boolean status = true;
	
	private String role;
	
	
}
