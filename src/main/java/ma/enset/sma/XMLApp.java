package ma.enset.sma;

import jade.content.lang.Codec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import ma.enset.sma.ontology.CatalogOntology;
import ma.enset.sma.predicate.Available;

public class XMLApp {
    public static void main(String[] args) throws OntologyException, Codec.CodecException {
        XMLCodec xmlCodec = new XMLCodec();
        Ontology ontology = CatalogOntology.getCatalogOntology();
        String message = "<available>\n" +
                "\t<product price=\"2500.0F\" name=\"SAMSUNG A30\"/>\n" +
                "\t<agent-identifier name=\"seller@192.168.56.1:1099/JADE\">\n" +
                "\t\t<addresses>\n" +
                "\t\t\t<primitive type=\"STRING\" value=\"http://android-51im4n1.mshome.net:7778/acc\"/>\n" +
                "\t\t</addresses>\n" +
                "\t</agent-identifier>\n" +
                "</available>";
        Available available = (Available) xmlCodec.decodeObject(ontology, message);
        System.out.println(available);
    }
}
