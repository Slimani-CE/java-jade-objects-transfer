package ma.enset.sma.containers;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.GUI, "true");
        AgentContainer mainContainer = runtime.createMainContainer(profile);
        try {
            mainContainer.start();
        } catch (ControllerException e) {
            System.out.println("Error while starting the main container");
            throw new RuntimeException(e);
        }
    }
}
