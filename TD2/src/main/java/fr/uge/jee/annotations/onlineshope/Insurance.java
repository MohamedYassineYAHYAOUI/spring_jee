package fr.uge.jee.annotations.onlineshope;


@FunctionalInterface
public interface Insurance {

    enum Beneficiary{
        ALL("for all clients"),
        MEMBERS ("only for members");

        private final String stringValue;

        Beneficiary(String stringValue){
            this.stringValue = stringValue;
        }

        @Override
        public String toString() {
            return this.stringValue;
        }
    }
    public String getDescription();
}
