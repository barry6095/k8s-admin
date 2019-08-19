package cn.com.agree.aweb.entity.po.system;

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
 * @author yesijun@agree.com.cn
 * @date 2019.05.08 15:54:19
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "SYSTEM")
@Data
public class SystemPO extends BaseEntity {

	private static final long serialVersionUID = 4238453084299461887L;

	@Id
	@Column(name = "ID", length = 50)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "cn.com.agree.aweb.entity.utils.IdGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefix", value = "aweb") })
	private String id;
	@Column(name="CODE",length = 50)
	private String code;
    
	@Column(name="DESCRIPTION",length = 400)
    private String description;

}
