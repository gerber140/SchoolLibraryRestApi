package pl.kurs.schoollibraryrestapi.security.service;

import pl.kurs.schoollibraryrestapi.security.entity.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
