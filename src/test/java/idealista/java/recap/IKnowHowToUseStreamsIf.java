package idealista.java.recap;

import idealista.java.transform.Ad;
import idealista.java.transform.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static idealista.java.transform.Ad.Operation;
import static idealista.java.transform.Ad.Typology;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class IKnowHowToUseStreamsIf {

    private ImTheFuckingJava8Master imTheFuckingJava8Master = new ImTheFuckingJava8Master();

	private static final List<Ad> ads = asList(
			new Ad(new User("Juan", "34928411238"), LocalDate.of(2012, 6, 23), Typology.House, Operation.Sale, 300000d),
			new Ad(new User("Juan", "34628421238"), LocalDate.of(2014, 5, 2), Typology.Garage, Operation.Rent, 50d),
			new Ad(new User("Rubén", "34928411238"), LocalDate.of(2011, 1, 3), Typology.Office, Operation.Sale, 23d),
			new Ad(new User("Esther", "34928239517"), LocalDate.of(2015, 8, 10), Typology.House, Operation.Sale, 23d),
			new Ad(new User("Juan", "33917211238"), LocalDate.of(1996, 9, 21), Typology.House, Operation.Rent, 300d),
			new Ad(new User("Juan", "34928411238"), LocalDate.of(2007, 4, 30), Typology.House, Operation.Sale, 500d)
	);

	@Test
	public void i_can_sum_the_price_of_all_the_ads() {
		double totalPrice = imTheFuckingJava8Master.sumPriceOfAds(ads);

		assertThat(totalPrice, is(closeTo(300896d, 0.0001)));
	}

	@Test
	public void i_can_find_all_different_users_with_name_Juan() {
		List<User> users = imTheFuckingJava8Master.findAllDiferentUsersWithNameJuan(ads);

		assertThat(users, containsInAnyOrder(
				new User("Juan", "34928411238"),
				new User("Juan", "34628421238"),
				new User("Juan", "33917211238")
		));
	}

	@Test
	public void i_can_find_the_oldest_ad() {
		Ad ad = imTheFuckingJava8Master.findTheOldestAd(ads);
		Ad expectedAd = new Ad(new User("Juan", "33917211238"), LocalDate.of(1996, 9, 21), Typology.House, Operation.Rent, 300d);

		assertThat(ad, is(expectedAd));
	}

	@Test
	public void i_can_find_all_the_phones_for_houses_being_sold() {
		List<String> users = imTheFuckingJava8Master.findAllPhonesForHousesBeingSold(ads);

		assertThat(users, containsInAnyOrder(
				"34928411238",
				"34928239517"
		));
	}

	@Test
	public void i_can_find_two_ads_with_the_same_price() {
		List<Ad> adsWithSamePrice = imTheFuckingJava8Master.findTowAdsWithSamePrice(ads);

		assertThat(adsWithSamePrice, containsInAnyOrder(
				new Ad(new User("Rubén", "34928411238"), LocalDate.of(2011, 1, 3), Typology.Office, Operation.Sale, 23d),
				new Ad(new User("Esther", "34928239517"), LocalDate.of(2015, 8, 10), Typology.House, Operation.Sale, 23d)
		));
	}
}
