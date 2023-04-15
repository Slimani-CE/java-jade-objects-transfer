package ma.enset.sma.agent;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import ma.enset.sma.concept.Product;
import ma.enset.sma.ontology.CatalogOntology;
import ma.enset.sma.predicate.Available;

public class SellerAgentXML extends Agent {

    Ontology catalogOntology = CatalogOntology.getCatalogOntology();
    private XMLCodec xmlCodec = new XMLCodec();
    @Override
    protected void setup() {
        System.out.println("Seller agent started");

        getContentManager().registerOntology(catalogOntology);
        getContentManager().registerLanguage(xmlCodec);

        Product product = new Product();
        product.setName("SAMSUNG A30");
        product.setPrice(2500);

        Available available = new Available();
        available.setProduct(product);
        available.setSeller(getAID());

        ACLMessage message = new ACLMessage(ACLMessage.QUERY_IF);
        message.setLanguage(xmlCodec.getName());
        message.setOntology(catalogOntology.getName());
        message.addReceiver(new AID("buyer", AID.ISLOCALNAME));
        try {
            getContentManager().fillContent(message, available);
            send(message);
        } catch (Codec.CodecException e) {
            throw new RuntimeException(e);
        } catch (OntologyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Seller agent terminated");
    }
}
