package com.kcc.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;


public class JwtUser implements UserDetails {
    @JsonIgnore
    private final Long id;
    private final String username;
    @JsonIgnore
    private final String secret;
    private final String nickname;
    @JsonIgnore
    private final List<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    @JsonIgnore
    private final Date passwordLastResetDate;
    @JsonIgnore
    private String password;

    public JwtUser(Long id, String username, String secret, String nickname, List<GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Date passwordLastResetDate, String password) {
        this.id = id;
        this.username = username;
        this.secret = secret;
        this.nickname = nickname;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.passwordLastResetDate = passwordLastResetDate;
        this.password = password;
    }

    public static JwtUser.UserBuilder builder() {
        return new JwtUser.UserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public String getSecret() {
        return secret;
    }

    public String getNickname() {
        return nickname;
    }

    public Date getPasswordLastResetDate() {
        return passwordLastResetDate;
    }

    public void eraseCredentials() {
        password = null;
    }

    public static class UserBuilder {
        private Long id;
        private String username;
        private String nickname;
        private String password;
        private String secret;
        private List<GrantedAuthority> authorities;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;
        private Date passwordLastResetDate;

        private Function<String, String> passwordEncoder = password -> password;

        private UserBuilder() {
        }

        public JwtUser.UserBuilder id(Long id) {
            Assert.notNull(id, "id cannot be null");
            this.id = id;
            return this;
        }

        public JwtUser.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public JwtUser.UserBuilder nickname(String nickname) {
            Assert.notNull(nickname, "nickname cannot be null");
            this.nickname = nickname;
            return this;
        }

        public JwtUser.UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public JwtUser.UserBuilder secret(String secret) {
            Assert.notNull(secret, "secret cannot be null");
            this.secret = secret;
            return this;
        }

        public JwtUser.UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public JwtUser.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(
                    roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), () -> role
                        + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return authorities(authorities);
        }

        public JwtUser.UserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        public JwtUser.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        public JwtUser.UserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        public JwtUser.UserBuilder accountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public JwtUser.UserBuilder accountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public JwtUser.UserBuilder credentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public JwtUser.UserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public JwtUser.UserBuilder passwordLastResetDate(Date passwordLastResetDate) {
            this.passwordLastResetDate = passwordLastResetDate;
            return this;
        }

        public JwtUser build() {
            String encodedPassword = this.passwordEncoder.apply(password);

            return new JwtUser(id, username, secret, nickname, authorities, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, passwordLastResetDate, password);
        }
    }

}
