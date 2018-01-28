import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private Long id ;
	private String name ;
	private String value;

	public Product(Long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
}
