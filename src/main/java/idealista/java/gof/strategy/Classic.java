package idealista.java.gof.strategy;

import idealista.java.transform.Ad;
import idealista.java.transform.User;

import java.time.LocalDate;

public class Classic {

    interface TitleCreator {
        boolean is (Ad.Typology typology);
        String with (Ad ad);
    }

    static class OfficeTitleCreator implements TitleCreator{

        @Override
        public boolean is(Ad.Typology typology) {
            return typology == Ad.Typology.Office;
        }

        @Override
        public String with(Ad ad) {
            return "Esta Oficina molona cuesta " + ad.getPrice();
        }
    }

    static class HouseTitleCreator implements TitleCreator{

        @Override
        public boolean is(Ad.Typology typology) {
            return typology == Ad.Typology.House;
        }

        @Override
        public String with(Ad ad) {
            return "Esta Casa cagona cuesta " + ad.getPrice();
        }
    }

    static class AdRender {
        TitleCreator titleCreator;

        AdRender(TitleCreator titleCreator) {
            this.titleCreator = titleCreator;
        }

        String render(Ad ad) {
            if(titleCreator.is(ad.getTypology()))
                return titleCreator.with(ad);
            return "";
        }
    }

    public static void main(String[] args) {
        AdRender adRender = new AdRender(new OfficeTitleCreator());
        Ad ad = new Ad(new User("ans", "9256983"), LocalDate.now(), Ad.Typology.Office, Ad.Operation.Rent, 26d);
        System.out.println(adRender.render(ad));
    }

}
