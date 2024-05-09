package kr.or.kosa.nux2.web.auth;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal; //username
    private final String password;

    public JwtAuthenticationToken(Object principal, String password) {
        super(null);
        super.setAuthenticated(false);
        this.principal = principal;
        this.password = password;
    }

    public JwtAuthenticationToken(Object principal, String password, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
        this.principal = principal;
        this.password = password;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        JwtAuthenticationToken that = (JwtAuthenticationToken) obj;
        return Objects.equals(principal, that.principal) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), principal, password);
    }

    /*
     @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted.");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
     */
}
