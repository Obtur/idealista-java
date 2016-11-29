package idealista.java.recap;

import idealista.java.transform.Ad;
import idealista.java.transform.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.*;

/**
 * Created by acuervo on 29/11/16.
 */
public class ImTheFuckingJava8Master {

    public double sumPriceOfAds(List<Ad> ads) {
        return ads.stream()
                    .mapToDouble(Ad::getPrice)
                    .sum();
    }


    public List<User> findAllDiferentUsersWithNameJuan(List<Ad> ads) {
        return ads.stream()
                .map(Ad::getUser)
                .filter(u -> u.getName().equals("Juan"))
                .distinct()
                .collect(Collectors.toList());
    }

    public Ad findTheOldestAd(List<Ad> ads) {
        return ads.stream()
                .min((a1, a2) -> a1.getPublicationDate().compareTo(a2.getPublicationDate()))
                .get();
    }


    public List<String> findAllPhonesForHousesBeingSold(List<Ad> ads) {
        return ads.stream()
                .filter(a -> a.getOperation().equals(Ad.Operation.Sale))
                .map(a -> a.getUser().getPhone())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Ad> findTwoAdsWithSamePrice(List<Ad> ads) {
        return ads.stream()
                .collect(groupingBy(Ad::getPrice))
                .values().stream()
                .filter(e -> e.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
