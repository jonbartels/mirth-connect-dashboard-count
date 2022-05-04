package com.jonbartels.mirthdashboard;

import com.kaurpalang.mirth.annotationsplugin.annotation.ClientClass;
import com.mirth.connect.client.ui.CellData;
import com.mirth.connect.client.ui.NumberCellRenderer;
import com.mirth.connect.client.ui.UIConstants;
import com.mirth.connect.model.ChannelGroup;
import com.mirth.connect.model.DashboardStatus;
import com.mirth.connect.plugins.DashboardColumnPlugin;

import javax.swing.table.TableCellRenderer;
import java.util.List;

@ClientClass
public class GroupCountColumn extends DashboardColumnPlugin {
    public GroupCountColumn(String name) {
        super(name);
    }

    @Override
    public String getColumnHeader() {
        return "Count";
    }

    @Override
    public Object getTableData(ChannelGroup channelGroup) {
        int size = 0;
        if (channelGroup.getChannels() != null) {
            size = channelGroup.getChannels().size();
        }
        return size;
    }

    @Override
    public Object getTableData(String s) {
        return null;
    }

    @Override
    public Object getTableData(String s, Integer integer) {
        return null;
    }

    @Override
    public TableCellRenderer getCellRenderer() {
        return new NumberCellRenderer();
    }

    @Override
    public int getMaxWidth() {
        return UIConstants.MIN_WIDTH;
    }

    @Override
    public int getMinWidth() {
        return UIConstants.MIN_WIDTH;
    }

    @Override
    public boolean isDisplayFirst() {
        return false;
    }

    @Override
    public void tableUpdate(List<DashboardStatus> list) {
    }

    @Override
    public String getPluginPointName() {
        return "Channel Group Dashboard Count";
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }
}
