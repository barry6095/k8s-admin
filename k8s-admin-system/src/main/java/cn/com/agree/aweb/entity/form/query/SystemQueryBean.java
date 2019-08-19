package cn.com.agree.aweb.entity.form.query;

import cn.com.agree.aweb.annotation.QueryParam;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors()
public class SystemQueryBean {

	@QueryParam(name = "id")
	private String id;
}
