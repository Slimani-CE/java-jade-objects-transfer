package ma.enset.sma.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SellerContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer sellerContainer = runtime.createAgentContainer(profile);
        AgentController sellerAgent = sellerContainer.createNewAgent("seller", "ma.enset.sma.agent.SellerAgentXML", new Object[]{});
        sellerAgent.start();
    }
}
