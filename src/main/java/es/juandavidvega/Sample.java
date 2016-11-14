package es.juandavidvega;

import es.juandavidvega.optional.OptionalStuff;

public class Sample {

    public static void main(String[] args) {
        OptionalStuff stuff = new OptionalStuff();

        //Never Must be null
        System.out.println(stuff.maybeEmptyMap().getText());


    }
}
