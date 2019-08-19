package cn.com.agree.aweb.entity.form.query;


import cn.com.agree.aweb.annotation.QueryParam;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors()
public class RoleSystemQueryBean {

	@QueryParam(name = "roleId")
	private String roleId;
	
	@QueryParam(name = "systemId")
	private String systemId;
	
	@QueryParam(name = "roleIds")
	private List<String> roleIds;
	
	@QueryParam(name = "systemIds")
	private List<String> systemIds;
	
}
