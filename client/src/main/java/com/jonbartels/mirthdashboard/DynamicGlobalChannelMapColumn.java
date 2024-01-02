package com.jonbartels.mirthdashboard;

import com.kaurpalang.mirth.annotationsplugin.annotation.MirthClientClass;
import com.mirth.connect.model.ChannelGroup;
import com.mirth.connect.model.DashboardStatus;
import com.mirth.connect.plugins.DashboardColumnPlugin;

import javax.swing.table.TableCellRenderer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MirthClientClass
public class DynamicGlobalChannelMapColumn extends DashboardColumnPlugin {
    private Map<String, Object> channelIdColumnValues = new HashMap<String, Object>();
    private String header = "";
    private final ListeningPortColumn listeningPortColumn;

    public DynamicGlobalChannelMapColumn(String name) {
        super(name);
        this.listeningPortColumn = new ListeningPortColumn();
    }

    public void addValue(String channelId, Object value){
        channelIdColumnValues.put(channelId, value);
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String getColumnHeader() {
        return header;
    }

    /**
     * No data for channel group
     */
    @Override
    public Object getTableData(ChannelGroup channelGroup) {
        return null;
    }

    /**
     * @param channelId
     * @return the value from the internal map object
     */
    @Override
    public Object getTableData(String channelId) {
        return channelIdColumnValues.get(channelId);
    }

    /**
     * @param s
     * @param integer
     * @return always null
     */
    @Override
    public Object getTableData(String s, Integer integer) {
        return null;
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
