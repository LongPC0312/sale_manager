package sale_manager.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
	@NotBlank(message="Tài khoản không được bỏ trống")
	@Size(min = 6, max = 18, message= "Tài khoản có độ dài từ 6 tới 18 ký tự")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,18}$",
			 message= "Tài khoản chứa ít nhất 1 chữ thường, 1 chữ hoa, 1 số và không có bất kỳ ký tự đặc biệt nào")
	private String username;
	
	@NotBlank(message="Mật khẩu không được bỏ trống")
	@Size(min = 6, max = 18, message= "Mật khẩu có độ dài từ 6 tới 18 ký tự")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{6,18}$",
			 message= "Mật khẩu chưa ít nhất 1 chữ thường, 1 chữ hoa, 1 số và không có bất kỳ ký tự đặc biệt nào")
	private String password;
	
	
}
