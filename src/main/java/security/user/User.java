package security.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.token.Token;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue
	private Long id;
	@Size(min = 2, max = 30, message = "First name length must be at least 2 characters long and up to 30 characters")
	private String firstname;
    @Column(nullable = false)
   	@Size(min = 2, max = 50, message = "Last name length must be at least 2 characters long and up to 50 characters")
   	private String lastname;
    @Column(nullable = false)
	private String email;
    @Column(nullable = false)
	private String password;
    
	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "user")
	  private List<Token> tokens;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}
	@Override
	public String getUsername() {
		return email;
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
		return true;
	}
	@Override
	public String getPassword() {
		return password;
	}

}
