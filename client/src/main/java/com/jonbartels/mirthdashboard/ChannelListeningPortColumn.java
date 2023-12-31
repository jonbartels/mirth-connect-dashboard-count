package com.jonbartels.mirthdashboard;

import com.kaurpalang.mirth.annotationsplugin.annotation.MirthClientClass;
import com.mirth.connect.model.Channel;
import com.mirth.connect.model.ChannelGroup;
import com.mirth.connect.plugins.ChannelColumnPlugin;

import javax.swing.table.TableCellRenderer;
import java.util.List;

/**
 * This physically can't work due to https://github.com/nextgenhealthcare/connect/issues/6046
 * It doesn't error but won't display the columns on the first login.
 */
@MirthClientClass
public class ChannelListeningPortColumn extends ChannelColumnPlugin {
    private final ListeningPortColumn listeningPortColumn;

    public ChannelListeningPortColumn(String name) {
        super(name);
        this.listeningPortColumn = new ListeningPortColumn();
    }

    @Override
    public String getColumnHeader() {
        System.out.println("In getColumnHeader...");
        return listeningPortColumn.getColumnHeader();
    }

    @Override
    public Object getTableData(ChannelGroup channelGroup) {
        System.out.println("In getTableData...");
        return listeningPortColumn.getTableData(channelGroup);
    }

    @Override
    public Object getTableData(Channel channel) {
        return listeningPortColumn.getTableData(channel.getId());
    }

    @Override
    public TableCellRenderer getCellRenderer() {
        return listeningPortColumn.getCellRenderer();
    }

    @Override
    public int getMaxWidth() {
        return listeningPortColumn.getMaxWidth();
    }

    @Override
    public int getMinWidth() {
        return listeningPortColumn.getMinWidth();
    }

    @Override
    public boolean isDisplayFirst() {
        return listeningPortColumn.isDisplayFirst();
    }

    @Override
    public void tableUpdate(List<Channel> list) {

        System.out.println("In tableUpdate...");
    }

    @Override
    public String getPluginPointName() {
        return listeningPortColumn.getPluginPointName();
    }

    @Override
    public void start() {
        System.out.println("Starting...");
        listeningPortColumn.start();
        System.out.println("Started...");
    }

    @Override
    public void stop() {
        listeningPortColumn.stop();
    }

    @Override
    public void reset() {
        listeningPortColumn.reset();
    }
}
