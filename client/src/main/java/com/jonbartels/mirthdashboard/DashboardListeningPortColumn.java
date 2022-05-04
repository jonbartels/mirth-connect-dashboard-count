package com.jonbartels.mirthdashboard;

import com.kaurpalang.mirth.annotationsplugin.annotation.ClientClass;
import com.mirth.connect.model.ChannelGroup;
import com.mirth.connect.model.DashboardStatus;
import com.mirth.connect.plugins.DashboardColumnPlugin;

import javax.swing.table.TableCellRenderer;
import java.util.List;

@ClientClass
public class DashboardListeningPortColumn extends DashboardColumnPlugin  {
    private final ListeningPortColumn listeningPortColumn;

    public DashboardListeningPortColumn(String name) {
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
    public Object getTableData(String s) {
        return listeningPortColumn.getTableData(s);
    }

    @Override
    public Object getTableData(String s, Integer integer) {
        return listeningPortColumn.getTableData(s, integer);
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
    public void tableUpdate(List<DashboardStatus> list) {

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
