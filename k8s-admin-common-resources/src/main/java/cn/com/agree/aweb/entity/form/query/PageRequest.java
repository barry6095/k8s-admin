package cn.com.agree.aweb.entity.form.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * @author zhongyi@agree.com.cn
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
	
	private int start = 0;
	//默认加载全部数据
	private int size = Integer.MAX_VALUE;
	private Map<String, String> sorts = new HashMap<>();
	
	
	public org.springframework.data.domain.PageRequest getPageRequest(){
		List<Order> orders = new ArrayList<>();
		sorts.forEach((k, v) -> {
			if(v.equals("asc")) {
				orders.add(Order.asc(k));
			}
			orders.add(Order.desc(k));
		});
		return org.springframework.data.domain.PageRequest.of(start, size, Sort.by(orders));
	}
}
