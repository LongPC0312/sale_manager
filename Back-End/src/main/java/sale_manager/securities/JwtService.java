package sale_manager.securities;



import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	private final String SECRET_KEY= "mySecretKey1234567890";
	private final long ACCESS_EXPIRATION= 1000*60*15;
	private final long REFRESH_EXPIRATION= 1000*60*60*24*7;
	
	public String generateToken(String username) {
		return buildToken(username, ACCESS_EXPIRATION);
	}
	
	public String refreshToken(String username) {
		return buildToken(username, REFRESH_EXPIRATION);
	}
	
	public String buildToken(String username, long expireMs) {
		return Jwts.builder()
				.setSubject(username)
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