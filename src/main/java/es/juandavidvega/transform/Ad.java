package es.juandavidvega.transform;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ad {
    private final Integer id;
    private final Typology typology;
    private final Double price;
    private final Boolean active;

    public Ad(Integer id, Typology typology, Double price) {
        this.id = id;
        this.typology = typology;
        this.price = price;
        this. active = true;
    }

    private Ad(Ad ad, Boolean isActive) {
        this.id = ad.getId();
        this.typology = ad.getTypology();
        this.price = ad.getPrice();
        this. active = isActive;
    }

    public Integer getId() {
        return id;
    }

    public Typology getTypology() {
        return typology;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isActive() {
        return active;
    }

    public Ad deactivate() {
        return new Ad(this, false);
    }

    public enum Typology {
        House, Garage, Office
    }

    public static List<Ad> sampleLargeListOfAds() {


        Stream<Ad> houses = IntStream.range(1, 350)
                .parallel()
                .mapToObj(number -> new Ad(number, Typology.House, new Random(number).nextDouble()));

        Stream<Ad> garage = IntStream.range(1, 350)
                .parallel()
                .mapToObj(number -> new Ad(number, Ad.Typology.Garage, new Random(number).nextDouble()));

        Stream<Ad> offices = IntStream.range(1, 350)
                .parallel()
                .mapToObj(number -> new Ad(number, Ad.Typology.Office, new Random(number).nextDouble()));

        Stream<Ad> inactiveHouses = IntStream.range(1, 350)
                .parallel()
                .mapToObj(number -> new Ad(number, Typology.House, new Random(number).nextDouble()))
                .map(Ad::deactivate);

        return Stream.of(houses, garage, offices, inactiveHouses)
                .flatMap(Function.identity())
                .collect(toList());

    }
}
