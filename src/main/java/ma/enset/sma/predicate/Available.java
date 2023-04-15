package ma.enset.sma.predicate;

import jade.content.Predicate;
import jade.core.AID;
import ma.enset.sma.concept.Product;

public class Available implements Predicate {
    private AID seller;
    private Product product;

    public AID getSeller() {
        return seller;
    }

    public void setSeller(AID seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Available{" +
                "seller=" + seller +
                ", product=" + product +
                '}';
    }
}
