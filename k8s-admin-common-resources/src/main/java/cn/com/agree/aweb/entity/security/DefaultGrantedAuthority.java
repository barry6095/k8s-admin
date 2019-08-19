package cn.com.agree.aweb.entity.security;

import cn.com.agree.aweb.entity.po.auth.center.MenuPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DefaultGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = -8750019035552452687L;
    private MenuPO menu;

    @Override
    public String getAuthority() {
        return menu.getName();
    }
}
