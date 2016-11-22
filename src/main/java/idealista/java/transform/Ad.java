package idealista.java.transform;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.generate;
import static java.util.stream.IntStream.range;

public class Ad {
	private static final Random random = new Random();
	private final User user;
	private final LocalDate publicationDate;
	private final Typology typology;
	private final Operation operation;
	private final Double price;
	private final Boolean active;

	public Ad(User user, LocalDate publicationDate, Typology typology, Operation operation, Double price) {
		this(user, publicationDate, typology, operation, price, true);
	}

	private Ad(User user, LocalDate publicationDate, Typology typology, Operation operation, Double price, Boolean isActive) {
		this.user = user;
		this.publicationDate = publicationDate;
		this.typology = typology;
		this.operation = operation;
		this.price = price;
		this.active = isActive;
	}

	public User getUser() {
		return user;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public Typology getTypology() {
		return typology;
	}

	public Operation getOperation() {
		return operation;
	}

	public Double getPrice() {
		return price;
	}

	public Boolean isActive() {
		return active;
	}

	public Ad deactivate() {
		return new Ad(user, publicationDate, typology, operation, price, false);
	}

	public enum Typology {
		House, Garage, Office
	}

	public enum Operation {
		Sale, Rent
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Ad ad = (Ad) o;

		if (!user.equals(ad.user)) return false;
		if (!publicationDate.equals(ad.publicationDate)) return false;
		if (typology != ad.typology) return false;
		if (operation != ad.operation) return false;
		if (!price.equals(ad.price)) return false;
		return active.equals(ad.active);

	}

	@Override
	public int hashCode() {
		int result = user.hashCode();
		result = 31 * result + publicationDate.hashCode();
		result = 31 * result + typology.hashCode();
		result = 31 * result + operation.hashCode();
		result = 31 * result + price.hashCode();
		return 31 * result + active.hashCode();
	}

	public static List<Ad> sampleLargeListOfAds() {
		Stream<Ad> houses = createAds(Typology.House);
		Stream<Ad> garage = createAds(Typology.Garage);
		Stream<Ad> offices = createAds(Typology.Office);
		Stream<Ad> inactiveHouses = createAds(Typology.House).map(Ad::deactivate);
		return Stream.of(houses, garage, offices, inactiveHouses)
				.flatMap(identity())
				.collect(toList());
	}

	private static Stream<Ad> createAds(Typology typology) {
		Random random = new Random();
		return range(1, 350)
				.parallel()
				.mapToObj(number -> new Ad(randomUser(), randomDate(), typology, Operation.values()[random.nextInt(2)], random.nextDouble() * 2000));
	}

	private static User randomUser() {
		return new User(User.randomName(), randomPhone());
	}

	private static String randomPhone() {
		return generate(() -> 9).limit(9)
				.map(random::nextInt)
				.mapToObj(Integer::toString).collect(joining());
	}

	private static LocalDate randomDate() {
		long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
		long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
		return LocalDate.ofEpochDay(randomDay);
	}
}
