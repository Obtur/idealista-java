package idealista.java.transform;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class User {

	private static final List<String> NAMES = asList("Carlos", "Ana", "David", "Belén", "Miguel", "Juan", "Noa", "José", "Rubén", "Guillermo", "Alberto", "Mario", "Alba", "María", "Marco");
	private static final Random RANDOM = new Random();
	private final String name;
	private final String phone;

	public User(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!name.equals(user.name)) return false;
		return phone.equals(user.phone);

	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + phone.hashCode();
		return result;
	}

	public static String randomName() {
		return NAMES.get(RANDOM.nextInt(NAMES.size()));
	}
}
