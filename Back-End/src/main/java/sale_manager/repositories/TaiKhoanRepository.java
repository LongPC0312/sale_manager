package sale_manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sale_manager.models.TaiKhoanModel;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoanModel, Integer>{
	public Optional<TaiKhoanModel> findByUsername(String username);
}
