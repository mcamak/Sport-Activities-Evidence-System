package cz.muni.fi.pa165.mvc.security;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

import static cz.muni.fi.pa165.mvc.security.Roles.ADMIN;
import static cz.muni.fi.pa165.mvc.security.Roles.USER;

/**
 * Created by Marian Camak on 11. 1. 2016.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    UserFacade userFacade;

    public CustomAuthenticationProvider(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName().trim();
        String password = authentication.getCredentials().toString().trim();

        UserLogInDTO loginDTO = new UserLogInDTO();
        loginDTO.setUsername(userName);
        loginDTO.setPassword(password);

        UserDTO user;
        try {
            user = userFacade.findByUsername(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with username " + userName + " was not found");
        }
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + userName + " was not found");
        }

        if (userFacade.logIn(loginDTO)) {
            Collection<GrantedAuthority> grantedAuths = new ArrayList<>();
            if (userFacade.isAdmin(user.getId())) {
                grantedAuths.add(new SimpleGrantedAuthority(ADMIN));
            } else {
                grantedAuths.add(new SimpleGrantedAuthority(USER));
            }
            ApplicationUser appUser = new ApplicationUser(userName, password, true, true, true, true, grantedAuths);
            return new UsernamePasswordAuthenticationToken(appUser, password, grantedAuths);
        } else {
            throw new BadCredentialsException("Wrong password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
