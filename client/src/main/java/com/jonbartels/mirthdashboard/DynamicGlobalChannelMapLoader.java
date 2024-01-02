package com.jonbartels.mirthdashboard;
import com.kaurpalang.mirth.annotationsplugin.annotation.MirthClientClass;
import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.client.ui.LoadedExtensions;
import com.mirth.connect.client.ui.PlatformUI;
import com.mirth.connect.plugins.ClientPlugin;

import com.mirth.connect.plugins.globalmapviewer.GlobalMapServletInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MirthClientClass
public class DynamicGlobalChannelMapLoader extends ClientPlugin {
    private GlobalMapServletInterface globalMapServletInterface;
    private final String regex = "_dashboard_(.*)_";
    private final Pattern pattern = Pattern.compile(regex);
    public DynamicGlobalChannelMapLoader(String pluginName) {
        super(pluginName);
        globalMapServletInterface = PlatformUI.MIRTH_FRAME.mirthClient.getServlet(GlobalMapServletInterface.class);
    }

    @Override
    public String getPluginPointName() {
        return "Dynamic Header - Loader";
    }

    @Override
    public void start() {
        //get $gc
        // do we use getall maps or should we enumerate deployed chanels and iterate the deplyoed channels
        // the structure is server id / channel id / global channel map key / global channel map value
        // null channel ID is global map

        // TODO - gotta enumerate the deployed channels, getallmaps wont give me back the channel data
        // TODO - IDK if this is a lifecycle issue or if get all maps doesnt fucking get all maps
        Map<String, Map<String, Map<String, String>>> allMaps = null;
        try {
            allMaps = globalMapServletInterface.getAllMaps(null, false);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        String serverId = null;
        try {
            serverId = parent.mirthClient.getServerId();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        System.out.println("serverId is: " + serverId);
        System.out.println("All maps keys is: " + allMaps.keySet().toString());

        Map<String, Map<String, String>> globalChannelMaps = allMaps.get(serverId);

        System.out.println("globalChannelMaps keys is: " + globalChannelMaps.keySet().toString());

        Map<String, DynamicGlobalChannelMapColumn> dynamicMapByHeader = new HashMap<>(1);

        //enumerate keys, looking for `_dashboard_(.*)_'
        //for each unique header
        for(String channelId : globalChannelMaps.keySet()){
            System.out.println("Checking $gc for channelId: "  +channelId);
            for(String mapKey : globalChannelMaps.get(channelId).keySet()){
                System.out.println("Checking mapKey against regex: " + mapKey);
                final Matcher matcher = pattern.matcher(mapKey);
                if(matcher.find()){
                    System.out.println("Found magic $gc variable: " + matcher.group(0));
                    System.out.println("Header name is: " + matcher.group(1));
                    String header = matcher.group(1);
                    String value = globalChannelMaps.get(channelId).get(mapKey);

                    //create an instance of a DynamicGlobalChannelMapColumn such that:
                    // - get header returns a static string with the column heading name
                    // - getTableData(s) returns a static value for each channel Id with that value populated
                    // - getTableData(s, int) returns empty string, since we dont need this data per connector

                    if(dynamicMapByHeader.get(header) == null){
                        dynamicMapByHeader.put(header, new DynamicGlobalChannelMapColumn("Dynamic Header - " + header));
                    }

                    dynamicMapByHeader.get(header).addValue(channelId, value);
                }
            }
        }

        //TODO - This seems really really dangerous as if affects ALL plugins. Will it affect state?
        //TODO is there a better, more selective way to do this?
//        LoadedExtensions.getInstance().initialize();
    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }
}
