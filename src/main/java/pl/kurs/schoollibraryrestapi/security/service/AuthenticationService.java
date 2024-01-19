package pl.kurs.schoollibraryrestapi.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kurs.schoollibraryrestapi.security.entity.User;
import pl.kurs.schoollibraryrestapi.security.repository.UserRepository;
import pl.kurs.schoollibraryrestapi.security.request.SignUpRequest;
import pl.kurs.schoollibraryrestapi.security.response.JwtAuthenticationResponseDTO;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public JwtAuthenticationResponseDTO authenticate(SignUpRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPassword(), request.getPassword()));
        String jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

}
