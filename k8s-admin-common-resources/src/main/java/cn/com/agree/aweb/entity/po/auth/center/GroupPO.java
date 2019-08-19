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
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author yesijun@agree.com.cn
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "AWEB_GROUP")
@Data
@Accessors(chain = true)
public class GroupPO extends BaseEntity {
    private static final long serialVersionUID = 2309774546644179318L;
    @Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = "prefix", value = "grp") })
    private String id;
    @Column(name = "DESCRIPTION", length = 400)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AWEB_USER_GROUP", joinColumns = {
            @JoinColumn(name = "GROUP_ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "USER_ID")
    }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<UserPO> users;
}