package es.juandavidvega.optional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionalStuff {

    public Text maybeEmptyMap(){
        Map<Integer, Text> staticLoadedTexts = new HashMap<>();
        Text text = staticLoadedTexts.get(1);
        if(text == null)
            return  new Text("No Text");
        return text;
    }

    public Text noATextOnRepository(){
        Text text = useARepositoryThatMaybeReturnsNull();
        if(text == null)
            return  new Text("No Text");
        return text;
    }

    public LocalDateTime parseRequestDate () {
        RequestBag request = new RequestBag();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String limitDate = request.queryParam("limitDate");
        if (limitDate != null && !limitDate.equals("")) {
            return LocalDate.parse(limitDate, formatter).atStartOfDay();
        }
        return LocalDateTime.now();
    }

    public Text getTheTextLargeThan8() {
        Text[] texts = new Text[] {
                new Text("asd"),
                new Text("asd"),
                new Text("asd"),
                new Text("asdsasdfasdfasdf"),
                new Text("asdsasdfasdfasdf3"),
                new Text("asdsasdfasdfasdf4"),
                new Text("asdsasdfasdfasdf5")
        };

        for (Text text : texts) {
            if(text.getText().length() > 8)
                return text;
        }

        return new Text("Sample Text Large Than 8");
    }

    private Text useARepositoryThatMaybeReturnsNull() {
        return null;
    }

}
