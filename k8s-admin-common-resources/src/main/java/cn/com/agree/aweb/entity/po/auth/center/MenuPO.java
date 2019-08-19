package cn.com.agree.aweb.entity.po.auth.center;

import cn.com.agree.aweb.entity.po.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhongyi@agree.com.cn
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "AWEB_MENU")
public class MenuPO extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 6389599871042702089L;

    @Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "prefix", value = "meu") })
    private String id;
    private String status;
    @Column(name = "PATH", length = 400)
    private String path;
    @Column(name = "PARENT_ID", length = 50)
    private String parentId;
    @Column(name = "TITLE", length = 50)
    private String title;
    @Column(name = "ICON", length = 50)
    private String icon;
    @Column(name = "SEQ", length = 5)
    private String seq;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AWEB_MENU_AUTH", joinColumns = {@JoinColumn(name = "MENU_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "AUTH_ID")}, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<AuthorityPO> authorities;

    @Override
    public String getAuthority() {
        return this.getName();
    }

}