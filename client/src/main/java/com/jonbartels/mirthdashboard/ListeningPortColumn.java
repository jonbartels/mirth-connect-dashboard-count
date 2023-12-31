package com.jonbartels.mirthdashboard;

import com.kaurpalang.mirth.annotationsplugin.annotation.MirthClientClass;
import com.mirth.connect.client.ui.PlatformUI;
import com.mirth.connect.client.ui.UIConstants;
import com.mirth.connect.model.ChannelGroup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.util.List;
import org.apache.log4j.Logger;

@MirthClientClass
public class ListeningPortColumn {
    private Logger logger = Logger.getLogger(this.getClass());
    private ListeningPortServletInterface listeningPortServletInterface;

    public ListeningPortColumn() {
        listeningPortServletInterface = PlatformUI.MIRTH_FRAME.mirthClient.getServlet(ListeningPortServletInterface.class);
    }

    public String getColumnHeader() {
        return "Listening Port";
    }

    public Object getTableData(ChannelGroup channelGroup) {
        return "lol wat";
    }

    public Object getTableData(String channelId) {
        String listeningPort = "";

        try {
            listeningPort = listeningPortServletInterface.getListeningPort(channelId);
        } catch (Exception e){
            //pokemon exception handling is necessary here because the client code doesnt log well
            //pokemon exception handling is necessary here because this code is called in a worker thread, failures just cause no output so we have to be very fault tolerant
            logger.error("Caught exception while calculating listening ports for channelId: " + channelId, e);
            System.out.println("Caught exception while calculating listening ports for channelId: " + channelId);
            e.printStackTrace();
        }

        return listeningPort;
    }

    public Object getTableData(String channelId, Integer metaDataId) {
        Object listeningPort = "";

        if(metaDataId == null || metaDataId == 0){
            listeningPort = getTableData(channelId);
        }

        return listeningPort;
    }

    public TableCellRenderer getCellRenderer() {
        return new DefaultTableCellRenderer();
    }

    public int getMaxWidth() {
        return UIConstants.MAX_WIDTH;
    }

    public int getMinWidth() {
        return UIConstants.MIN_WIDTH;
    }

    public boolean isDisplayFirst() {
        return false;
    }

    public String getPluginPointName() {
        return "Channel Listening Port";
    }

    public void start() {
    }

    public void stop() {    }

    public void reset() {

    }
}
