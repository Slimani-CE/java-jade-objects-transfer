package ma.enset.sma.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.*;
import ma.enset.sma.action.Sell;
import ma.enset.sma.concept.Computer;
import ma.enset.sma.concept.Product;
import ma.enset.sma.concept.USB;
import ma.enset.sma.predicate.Available;
import ma.enset.sma.vocabulary.CatalogVocabulary;

public class CatalogOntology extends Ontology implements CatalogVocabulary {
    // The name identifying this ontology
    public static final String ONTOLOGY_NAME = "Catalog-ontology";

    // The singleton instance of this ontology
    public static final Ontology CATALOG_ONTOLOGY = new CatalogOntology();

    private CatalogOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        // Add all concepts, predicates and agent actions in the vocabulary
        try {
            add(new ConceptSchema(PRODUCT), Product.class);
            add(new ConceptSchema(COMPUTER), Computer.class);
            add(new ConceptSchema(USB), ma.enset.sma.concept.USB.class);
            add(new AgentActionSchema(SELL), Sell.class);
            add(new PredicateSchema(AVAILABLE), Available.class);

            // Structure of the concept Product
            ConceptSchema ps = (ConceptSchema) getSchema(PRODUCT);
            ps.add(PRODUCT_PRICE, (PrimitiveSchema) getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
            ps.add(PRODUCT_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);

            // Structure of the concept Computer
            ConceptSchema cs = (ConceptSchema) getSchema(COMPUTER);
            cs.add(COMPUTER_RAM, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
            cs.add(COMPUTER_DISK, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
            cs.add(COMPUTER_PROCESSORS, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
            cs.addSuperSchema(ps);

            // Structure of the concept USB
            ConceptSchema us = (ConceptSchema) getSchema(USB);
            us.add(USB_CAPACITY, (PrimitiveSchema) getSchema(BasicOntology.FLOAT));
            us.addSuperSchema(ps);

            // Structure of the predicate Available
            PredicateSchema as = (PredicateSchema) getSchema(AVAILABLE);
            as.add(AVAILABLE_PRODUCT, ps);
            as.add(AVAILABLE_SELLER, getSchema(BasicOntology.AID));

            // Structure of the action Sell
            AgentActionSchema ss = (AgentActionSchema) getSchema(SELL);
            ss.add(SELL_PRODUCT, ps);
            ss.add(SELL_BUYER, (TermSchema) getSchema(BasicOntology.AID));
        } catch (OntologyException oe) {
            oe.printStackTrace();
        }
    }

    public static Ontology getCatalogOntology() {
        return CATALOG_ONTOLOGY;
    }
}
