package com.anderson.contasapagar.config.security;

import java.util.Collection;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@Slf4j
public class JWTConverter  implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
        Collection<String> roles = realmAccess.get("roles");
        log.info("Roles: {}", roles);
        var grants = roles
                .stream()
                .map(SimpleGrantedAuthority::new).toList();
                //.map(role -> new SimpleGrantedAuthority("ROLE_"+ role)).toList();


        return new JwtAuthenticationToken(jwt, grants);
    }

}
