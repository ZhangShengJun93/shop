import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by felix on 2018/01/23.
 */
public class MyTest {
	@Test
	public void test1() throws Exception {
		List<Product> lists = new ArrayList<Product>();
		lists.add(new Product(1L, "颜色", "黑"));
		lists.add(new Product(2L, "颜色", "白"));
		lists.add(new Product(3L, "颜色", "黄"));
		lists.add(new Product(4L, "尺寸", "20"));
		lists.add(new Product(5L, "尺寸", "21"));
		lists.add(new Product(6L, "尺寸", "22"));
		lists.add(new Product(7L, "大小", "24"));
		lists.add(new Product(8L, "大小", "25"));
		Map<String, StringBuilder> map = select(lists);
		System.out.println(map.get("颜色"));
		System.out.println(map.get("尺寸"));
		System.out.println(map.get("大小"));
		System.out.println(map);
	}

	private Map<String, StringBuilder> select(List<Product> lists) {
		Map<String,StringBuilder> map = new HashMap<String,StringBuilder> ();
		for (Product p : lists) {
			StringBuilder value = map.get(p.getName());
			if (value==null){
				HashMap<String, String> product = new HashMap<String, String>();
				product.put(p.getName(),p.getValue());
				map.put(p.getName(),new StringBuilder(p.getValue()));
			}else{
				value.append(","+p.getValue());
			}
		}
		return map;
	}
}
