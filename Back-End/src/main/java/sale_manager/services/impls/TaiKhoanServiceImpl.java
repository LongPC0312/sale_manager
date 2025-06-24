package sale_manager.services.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sale_manager.models.TaiKhoanModel;
import sale_manager.repositories.TaiKhoanRepository;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
	@Autowired TaiKhoanRepository tkrepo;
	@Override
	public Optional<TaiKhoanModel> findByUsername(String username) {
		return tkrepo.findByUsername(username);
	}

	@Override
	public void save(TaiKhoanModel taikhoan) {
		tkrepo.save(taikhoan);		
	}

}
