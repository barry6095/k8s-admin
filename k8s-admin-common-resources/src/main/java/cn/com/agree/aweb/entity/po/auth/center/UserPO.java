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

/**
 * @author zhongyi@agree.com.cn
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@Table(name = "AWEB_USER")
public class UserPO extends BaseEntity {
    private static final long serialVersionUID = 2487244848041439923L;

    @Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "prefix", value = "usr") })
    private String id;
    @Column(name = "PASSWORD", nullable = true)
    private String password;
    @Column(name = "NICKNAME", length = 200)
    private String nickname;
    @Column(name = "EMAIL", length = 100)
    private String email;
    @Column(name = "PHONE", length = 18)
    private String phone;
    @Column(name = "STATUS", length = 2)
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AWEB_USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<RolePO> roles;
}
