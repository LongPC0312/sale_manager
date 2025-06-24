package sale_manager.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sale_manager.models.TaiKhoanModel;
import sale_manager.repositories.TaiKhoanRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired TaiKhoanRepository tkrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TaiKhoanModel taikhoan = tkrepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tên tài khoản" + username));
		return new CustomUserDetails(taikhoan);
	}

}
