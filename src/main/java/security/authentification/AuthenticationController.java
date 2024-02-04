package security.authentification;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

//	@PostMapping("/login")
//	public ResponseEntity<AbstractResponse> authenticate(@RequestBody AuthenticationController request,
//			HttpServletResponse response) {
//		return ResponseEntity.ok(service.authenticate(request, response));
//	}

	@GetMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response)throws IOException {
		service.refreshToken(request, response);
	}

//	@CrossOrigin
//	@GetMapping("/logout")
//	public ResponseEntity<AbstractResponse> logout(HttpServletRequest request, HttpServletResponse response) {
//		return service.logout(request, response);
//	}
}
