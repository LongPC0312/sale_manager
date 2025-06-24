package sale_manager.models;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="taikhoan")
@Data
public class TaiKhoanModel {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="status")
	private Boolean status;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private Boolean gender;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="timeborn")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate timeborn;
	
	@Column(name="timework")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate timework;
	
	@Column(name="avatar")
	private String avatarUrl;
	
	public TaiKhoanModel() {
		
	}
	
	public TaiKhoanModel(String username) {
		this.username= username;
		this.avatarUrl= generatorAvatar(username);
	}
	public String generatorAvatar(String username) {
		String firstName= username.substring(0, 1).toUpperCase();
		String randomColor = randomHex();
		return "https://ui-avatars.com/api/?name=" + firstName + 
			       "&background=random&size=150&color= " + randomColor + "&rounded=false";
	}
	public String randomHex() {
		Random random = new Random();
		int r = random.nextInt(256);
	    int g = random.nextInt(256);
	    int b = random.nextInt(256);
	    return String.format("%02X%02X%02X", r, g, b);
	}
	
	public String getAvatarUrl() {
		if(avatarUrl == null || avatarUrl.isEmpty()) {
			return this.avatarUrl;
		}
		return avatarUrl;
	}
	
	public void setAvatarUrl(String avatarUrl) {
		 this.avatarUrl = avatarUrl;
	}
	
	
}
