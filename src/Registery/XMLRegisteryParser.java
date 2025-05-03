package Registery;

class XMLRegisteryParser implements RegisteryParser {
    @Override
    public void parseRegistery(String xmlData, RegisteryBuilder builder) throws Exception {
        // Créer un DocumentBuilder pour parser le XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(new java.io.StringBufferInputStream(xmlData));
        doc.getDocumentElement().normalize();

        // Récupérer tous les éléments "file"
        NodeList fileNodes = doc.getElementsByTagName("file");

        // Format pour parser la date (ISO 8601)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        // Parcourir chaque fichier dans le XML
        for (int i = 0; i < fileNodes.getLength(); i++) {
            Element fileElement = (Element) fileNodes.item(i);

            // Extraire le chemin (path)
            String path = fileElement.getElementsByTagName("path").item(0).getTextContent();

            // Extraire et parser la date de dernière modification
            String lastModifiedStr = fileElement.getElementsByTagName("lastModified").item(0).getTextContent();
            Date lastModified = dateFormat.parse(lastModifiedStr);

            // Ajouter le fichier au builder
            builder.addFile(path, lastModified);
        }
    }
}
