package ma.enset.sma.agent;

import jade.content.lang.Codec;
import jade.content.lang.rdf.RDFCodec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import ma.enset.sma.concept.Product;
import ma.enset.sma.ontology.CatalogOntology;
import ma.enset.sma.predicate.Available;

public class SellerAgentRDF extends Agent {

    Ontology catalogOntology = CatalogOntology.getCatalogOntology();
    private RDFCodec codecRDF = new RDFCodec();
    @Override
    protected void setup() {
        getContentManager().registerOntology(catalogOntology);
        getContentManager().registerLanguage(codecRDF);

        Product product = new Product();
        product.setName("SAMSUNG A30");
        product.setPrice(2500);

        Available available = new Available();
        available.setProduct(product);
        available.setSeller(getAID());

        ACLMessage message = new ACLMessage(ACLMessage.QUERY_IF);
        message.setLanguage(codecRDF.getName());
        message.setOntology(catalogOntology.getName());
        message.addReceiver(new AID("buyer", AID.ISLOCALNAME));
        try {
            getContentManager().fillContent(message, available);
            send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Seller agent terminated");
    }
}
