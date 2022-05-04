package com.jonbartels.mirthdashboard.server.servlet;

import com.jonbartels.mirthdashboard.ListeningPortPermissions;
import com.jonbartels.mirthdashboard.ListeningPortServletInterface;
import com.jonbartels.mirthdashboard.server.DashboardCountConstants;
import com.kaurpalang.mirth.annotationsplugin.annotation.ServerClass;
import com.mirth.connect.client.core.api.util.OperationUtil;
import com.mirth.connect.model.ExtensionPermission;
import com.mirth.connect.plugins.ServicePlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@ServerClass
public class ListeningPortServicePlugin implements ServicePlugin {
    @Override
    public void init(Properties properties) {

    }

    @Override
    public void update(Properties properties) {

    }

    @Override
    public Properties getDefaultProperties() {
        return new Properties();
    }

    @Override
    public ExtensionPermission[] getExtensionPermissions() {
        ExtensionPermission getPermission = new ExtensionPermission (
                DashboardCountConstants.PLUGIN_POINTNAME,
                ListeningPortPermissions.GETLISTENINGPORT,
                "Allows listing listening ports in channel dashboard view",
                OperationUtil.getOperationNamesForPermission(ListeningPortPermissions.GETLISTENINGPORT, ListeningPortServletInterface.class), new String[] {}
        );

        return new ExtensionPermission[] {getPermission};
    }

    @Override
    public Map<String, Object> getObjectsForSwaggerExamples() {
        return new HashMap<>();
    }

    @Override
    public String getPluginPointName() {
        return DashboardCountConstants.PLUGIN_POINTNAME;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
