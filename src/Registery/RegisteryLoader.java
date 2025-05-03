package Registery;

class RegisteryLoader {
    private RegisteryParser parser;
    private RegisteryBuilder builder;

    public RegisteryLoader(RegisteryParser parser, RegisteryBuilder builder) {
        this.parser = parser;
        this.builder = builder;
    }

    public Registery load(String data) throws Exception {
        // Utiliser le parser pour lire les données et remplir le builder
        parser.parseRegistery(data, builder);
        // Retourner le Registery construit
        return builder.build();
    }
}
