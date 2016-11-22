package idealista.java.gof.strategy;

import idealista.java.transform.Ad;
import idealista.java.transform.User;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaStyle {


    static String adRender (Ad ad, Predicate<Ad.Typology> typologyCheck, Function<Ad, String> titleCreator) {
        return "";
    }

    public static void main(String[] args) {
        Ad ad = new Ad(new User("ans", "9256983"), LocalDate.now(), Ad.Typology.Office, Ad.Operation.Rent, 26d);
        System.out.println(adRender(ad,
                typology -> typology == Ad.Typology.Office,
                targetAd -> "Esta Oficina molona cuesta " + targetAd.getPrice()));
    }


}
