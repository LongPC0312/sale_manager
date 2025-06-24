package sale_manager.securities;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication)
			throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority auth : authorities) {
			String role = auth.getAuthority();
			if(role.equals("ROLE_ADMIN")) {
				response.sendRedirect("/taikhoan/admin");
				return;
			}
			if(role.equals("ROLE_MANAGER")) {
				response.sendRedirect("/taikhoan/manager");
				return;
			}
			if(role.equals("ROLE_CUSTOMER")) {
				response.sendRedirect("/taikhoan/customer");
				return;
			}
			if(role.equals("ROLE_ACCOUNTANT")) {
				response.sendRedirect("/taikhoan/accountant");
				return;
			}
			if(role.equals("ROLE_SALE")) {
				response.sendRedirect("/taikhoan/sale");
				return;
			}
			if(role.equals("ROLE_WAREHOUSE")) {
				response.sendRedirect("/taikhoan/warehouse");
				return;
			}
		}
	response.sendRedirect("/taikhoan/dangnhap");	
	}

}
