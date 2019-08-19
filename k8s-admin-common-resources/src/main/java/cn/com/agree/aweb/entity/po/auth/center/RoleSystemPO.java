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

/**
 * @author YeSijun yesijun@agree.com.cn
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "AWEB_ROLE_SYSTEM")
@Data
public class RoleSystemPO extends BaseEntity {
	private static final long serialVersionUID = -8430180443137062338L;

	@Id
	@Column(name = "ID", length = 50)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefix", value = "rs") })
	private String id;
	@Column(name = "ROLE_ID", nullable = true, length = 50)
	private String roleId;
	@Column(name = "SYSTEM_ID", nullable = true, length = 50)
	private String systemId;
}
