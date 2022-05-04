package com.jonbartels.mirthdashboard;

import com.kaurpalang.mirth.annotationsplugin.annotation.ClientClass;
import com.mirth.connect.model.Channel;
import com.mirth.connect.model.ChannelGroup;
import com.mirth.connect.plugins.ChannelColumnPlugin;

import javax.swing.table.TableCellRenderer;
import java.util.List;

/**
 * This physically can't work due to https://github.com/nextgenhealthcare/connect/issues/5171
 *
 * Exclude it from the plugin assembly by commenting out the @ClientClass annotation
 */
@ClientClass
public class ChannelListeningPortColumn extends ChannelColumnPlugin {
    private final ListeningPortColumn listeningPortColumn;

    public ChannelListeningPortColumn(String name) {
        super(name);
        this.listeningPortColumn = new ListeningPortColumn();
    }

    @Override
    public String getColumnHeader() {
        return listeningPortColumn.getColumnHeader();
    }

    @Override
    public Object getTableData(ChannelGroup channelGroup) {
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

    }

    @Override
    public String getPluginPointName() {
        return listeningPortColumn.getPluginPointName();
    }

    @Override
    public void start() {
        listeningPortColumn.start();
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
