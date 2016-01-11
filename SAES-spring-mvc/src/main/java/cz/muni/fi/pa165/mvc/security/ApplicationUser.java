package cz.muni.fi.pa165.mvc.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Marian Camak on 11. 1. 2016.
 */
public class ApplicationUser extends User {

    public ApplicationUser(String username, String password, boolean enabled,
                           boolean accountNonExpired, boolean credentialsNonExpired,
                           boolean accountNonLocked,
                           Collection<GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
    }
}
