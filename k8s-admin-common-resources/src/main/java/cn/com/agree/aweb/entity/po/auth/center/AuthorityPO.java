package cn.com.agree.aweb.entity.po.auth.center;

import cn.com.agree.aweb.entity.po.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhongyi@agree.com.cn
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "AWEB_AUTHORITY")
public class AuthorityPO extends BaseEntity implements GrantedAuthority {
    private static final long serialVersionUID = 3747952780118430379L;

    @Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "authIdGenerator")
    @GenericGenerator(name = "authIdGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "prefix", value = "auth") })
    private String id;
    @Column(name = "STATUS", length = 2)
    private String status;

    @Override
    public String getAuthority() {
        return getName();
    }
}
