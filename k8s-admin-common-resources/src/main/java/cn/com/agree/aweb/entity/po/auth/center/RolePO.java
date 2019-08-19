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
@Table(name = "AWEB_ROLE")
@Data
public class RolePO extends BaseEntity {

	private static final long serialVersionUID = 2946693361392572022L;

	@Id
	@Column(name = "ID", length = 50)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefix", value = "rol") })
	private String id;
	@Column(name = "STATUS", length = 2)
	private String status;
	@Column(name = "COMMENT", length = 400)
	private String comment;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AWEB_ROLE_MENU", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "MENU_ID") }, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
	private List<MenuPO> menus;
}