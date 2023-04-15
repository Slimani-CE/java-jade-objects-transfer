package ma.enset.sma.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class BuyerContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        AgentContainer buyerContainer = runtime.createAgentContainer(profile);
        AgentController buyerAgent = buyerContainer.createNewAgent("buyer", "ma.enset.sma.agent.BuyerAgentXML", new Object[]{});
        buyerAgent.start();
    }
}
