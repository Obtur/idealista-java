package idealista.java.gof.builder;

public class ClassicBuilder {
    static class ABuilder {
        static StepOne build(Integer someInt) {
            return new StepOne() {
                @Override
                public StepTwo and(String someText) {
                    return new StepTwo() {
                        @Override
                        public String and(Boolean someBool) {
                            return  someInt + " num " + someText  + " Text " + someBool;
                        }
                    };
                }
            };
        }
    }

    interface StepOne {
        StepTwo and(String someText);
    }

    interface StepTwo {
        String and (Boolean someBool);
    }

    public static void main(String[] args) {
        System.out.println(
                ABuilder.build(1).and("asdf").and(false)
        );
    }
}
