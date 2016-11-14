package es.juandavidvega.optional;


import java.util.Optional;

public class RequestBag {

    public Optional<String> queryParamOptional(String limitDay) {
        return Optional.of("2011-12-03");
    }


    public String queryParam(String limitDay) {
        return "2011-12-03";
    }
}
