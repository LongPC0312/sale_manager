package sale_manager.securities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import sale_manager.models.TaiKhoanModel;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private final TaiKhoanModel taikhoan;
	public CustomUserDetails(TaiKhoanModel taikhoan) {
		this.taikhoan = taikhoan;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_" + taikhoan.getRole().toUpperCase()));
	}

	@Override
	public String getPassword() {
		return taikhoan.getPassword();
	}

	@Override
	public String getUsername() {
		return taikhoan.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return taikhoan.getStatus();
	}

}
