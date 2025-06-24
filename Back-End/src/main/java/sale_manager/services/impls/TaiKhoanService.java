package sale_manager.services.impls;

import java.util.Optional;

import sale_manager.models.TaiKhoanModel;

public interface TaiKhoanService {
	public Optional<TaiKhoanModel> findByUsername(String username);
	
	public void save(TaiKhoanModel taikhoan);
}
