package ma.enset.sma.agent;

import jade.content.lang.Codec;
import jade.content.lang.rdf.RDFCodec;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ma.enset.sma.ontology.CatalogOntology;
import ma.enset.sma.predicate.Available;

public class BuyerAgentRDF extends Agent {

    Ontology catalogOntology = CatalogOntology.getCatalogOntology();
    private RDFCodec codecRDF = new RDFCodec();

    @Override
    protected void setup() {
        System.out.println("Buyer agent started");

        getContentManager().registerOntology(catalogOntology);
        getContentManager().registerLanguage(codecRDF);
        MessageTemplate messageTemplate = MessageTemplate.and(
            MessageTemplate.MatchLanguage(codecRDF.getName()),
            MessageTemplate.MatchOntology(catalogOntology.getName())
        );

        ACLMessage receivedMessage = blockingReceive(messageTemplate);
        try {
            Available available = (Available) getContentManager().extractContent(receivedMessage);
            System.out.println(receivedMessage);
        } catch (Codec.CodecException e) {
            throw new RuntimeException(e);
        } catch (OntologyException e) {
            System.out.println("Error while extracting content");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void takeDown() {
        System.out.println("Buyer agent terminated");
    }
}
