package com.jonbartels.mirthdashboard.server.servlet;

import com.jonbartels.mirthdashboard.ListeningPortServletInterface;
import com.jonbartels.mirthdashboard.server.DashboardCountConstants;
import com.kaurpalang.mirth.annotationsplugin.annotation.ApiProvider;
import com.kaurpalang.mirth.annotationsplugin.type.ApiProviderType;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.donkey.model.channel.ConnectorProperties;
import com.mirth.connect.donkey.model.channel.ListenerConnectorPropertiesInterface;
import com.mirth.connect.donkey.server.channel.Channel;
import com.mirth.connect.donkey.server.channel.SourceConnector;
import com.mirth.connect.server.api.MirthServlet;
import com.mirth.connect.server.controllers.ControllerFactory;
import com.mirth.connect.server.controllers.EngineController;
import com.mirth.connect.server.util.TemplateValueReplacer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@ApiProvider(type = ApiProviderType.SERVER_CLASS)
public class ListeningPortServlet extends MirthServlet implements ListeningPortServletInterface {
    private Logger logger = Logger.getLogger(this.getClass());

    public ListeningPortServlet(@Context HttpServletRequest request, @Context SecurityContext sc) {
        super(request, sc, DashboardCountConstants.PLUGIN_POINTNAME);
    }

    @Override
    public String getListeningPort(String channelId) throws ClientException {
        String listeningPort = "";

        try {
            EngineController engineController = ControllerFactory.getFactory().createEngineController();
            TemplateValueReplacer templateValueReplacer = new TemplateValueReplacer();
            Channel deployedChannel = engineController.getDeployedChannel(channelId);

            SourceConnector sourceConnector = deployedChannel.getSourceConnector();
            ConnectorProperties connectorProperties = sourceConnector.getConnectorProperties();
            if (connectorProperties instanceof ListenerConnectorPropertiesInterface) {
                listeningPort = templateValueReplacer.replaceValues(((ListenerConnectorPropertiesInterface) connectorProperties).getListenerConnectorProperties().getPort(), channelId, deployedChannel.getName());
            }
        } catch (Exception e){
            //pokemon exception handling is necessary here because the client code doesnt log well
            //pokemon exception handling is necessary here because this code is called in a worker thread, failures just cause no output so we have to be very fault tolerant
            logger.error("Caught exception while calculating listening ports for channelId: " + channelId, e);
            System.out.println("Caught exception while calculating listening ports for channelId: " + channelId);
            e.printStackTrace();
        }

        return listeningPort;
    }
}
