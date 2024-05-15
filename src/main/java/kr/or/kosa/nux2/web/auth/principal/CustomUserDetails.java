package kr.or.kosa.nux2.web.auth.principal;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User{

    private final MemberDto.UserDto userDto;
    private Map<String, Object> attributes;

    //일반로그인
    public CustomUserDetails(MemberDto.UserDto userDto){
        this.userDto = userDto;
    }
    //oAuth 로그인
    public CustomUserDetails(MemberDto.UserDto userDto, Map<String, Object> attributes){
        this.userDto = userDto;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getMemberId();
    }
    private Collection<GrantedAuthority> createAuthorities(String roles){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: roles.split(",")){
            if(!StringUtils.hasText(role)) continue;
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return attributes.get("sub").toString();
    }
}
