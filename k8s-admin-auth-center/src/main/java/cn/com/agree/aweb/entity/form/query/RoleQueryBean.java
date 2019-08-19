package cn.com.agree.aweb.entity.form.query;

import cn.com.agree.aweb.annotation.QueryParam;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors()
public class RoleQueryBean {

	@QueryParam(name = "id")
	private String id;
	
	@QueryParam(name = "ids")
	private List<String> ids;
	
	@QueryParam(name = "name")
	private String name;
	
}
