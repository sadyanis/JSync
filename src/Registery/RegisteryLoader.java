package Registery;

public class RegisteryLoader {
    private RegisteryParser parser;
    private RegisteryBuilder builder;

    public RegisteryLoader(RegisteryParser parser, RegisteryBuilder builder) {
        this.parser = parser;
        this.builder = builder;
    }

    public Registery load(String data) throws Exception {
        parser.parseRegistery(data, builder);
        return builder.build();
    }
}
