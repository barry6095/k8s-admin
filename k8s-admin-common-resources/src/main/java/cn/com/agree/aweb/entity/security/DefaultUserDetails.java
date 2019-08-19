package cn.com.agree.aweb.entity.security;

import cn.com.agree.aweb.entity.enums.status.UserStatus;
import cn.com.agree.aweb.entity.po.auth.center.MenuPO;
import cn.com.agree.aweb.entity.po.auth.center.RolePO;
import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Accessors(chain = true)
@ToString
public class DefaultUserDetails implements UserDetails {

    private static final long serialVersionUID = 2487244848041439923L;

    private UserPO user;
    private Collection<? extends GrantedAuthority> authorities;

    public DefaultUserDetails(UserPO user) {
        this.setUser(user);
        this.setAuthorities(
                user.getRoles().stream().map(RolePO::getMenus).flatMap(List::stream).collect(Collectors.toList())
                        .stream().map(MenuPO::getAuthorities).flatMap(List::stream).collect(Collectors.toList()));
    }

    public UserPO getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        Optional<UserStatus> userStatus = UserStatus.fromCode(user.getStatus());
        return userStatus.isPresent() && userStatus.get().equals(UserStatus.AVAILABLE);
    }

    @Override
    public boolean isAccountNonLocked() {
        Optional<UserStatus> userStatus = UserStatus.fromCode(user.getStatus());
        return userStatus.isPresent() && userStatus.get().equals(UserStatus.AVAILABLE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        Optional<UserStatus> userStatus = UserStatus.fromCode(user.getStatus());
        return userStatus.isPresent() && userStatus.get().equals(UserStatus.AVAILABLE);
    }

    @Override
    public boolean isEnabled() {
        Optional<UserStatus> userStatus = UserStatus.fromCode(user.getStatus());
        return userStatus.isPresent() && userStatus.get().equals(UserStatus.AVAILABLE);
    }
}
