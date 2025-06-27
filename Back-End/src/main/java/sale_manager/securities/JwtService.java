package sale_manager.securities;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	
	private final String SECRET_KEY= "mySecretKey1234567890";
	private final long ACCESS_EXPIRATION= 1000*60;
	private final long REFRESH_EXPIRATION= 1000*60*60*24*7;
	
	
	public String generateToken(UserDetails userDetails) {
		return buildToken(userDetails, ACCESS_EXPIRATION);
	}
	
	public String refreshToken(UserDetails userDetails) {
		return buildToken(userDetails, REFRESH_EXPIRATION);
	}
	
	public String buildToken(UserDetails userDetails, long expireMs) {
		Map<String, Object> claim = new HashMap<>();
		List<String> roles = userDetails.getAuthorities().stream()
		        .map(authority -> authority.getAuthority())
		        .toList(); // 
		claim.put("role", roles);
		
		return Jwts.builder()
				.setClaims(claim)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ expireMs))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = Jwts.parser()
							.setSigningKey(SECRET_KEY)
							.parseClaimsJws(token)
							.getBody();
		return resolver.apply(claims);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, claims -> claims.getSubject());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, claims -> claims.getExpiration());
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public boolean isTokenValid(String token, String username) {
		return username.equals(extractUsername(token)) && !isTokenExpired(token);
	}
}