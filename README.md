# mirth-connect-dashboard-count
A Mirth Connect plugin to show the counts of channels in channel groups. 

Also shows the listening port for HTTP, TCP, and WS connectors.

In the Dashboard:
![count and ports columns in MC dashboard](https://github.com/jonbartels/mirth-connect-dashboard-count/blob/main/dashboard.png?raw=true)

In the Channel list:
Coming soon! There's an MC bug that prevents this from working but the plugin code is all ready to go.

# Inspiration
## Listening port idea from MC Issues

https://github.com/nextgenhealthcare/connect/issues/5152 and https://github.com/nextgenhealthcare/connect/issues/2115 proposed adding the listening ports to the dashboard and channel listing

## Channel Group count idea from Slack
```
chris  17:23
It would be great to show a count of channels in the Dashboard. Like a count in each group would meet my needs.

agermano  17:43
@chris I think this channel was supposed to be shut down in favor of using github for ideas. There's a "Feature Request" template when creating a new issue, and there's also a "Feature/Enhancement Ideas" category in discussions.

chris  17:51
GitHub issues sound great, but they apparently get as much consideration as the stuff posted here. So I'm happy to be ignored in either place.

1 reply
Today at 19:02View thread

jonb  17:54
com.mirth.connect.client.ui.DashboardPanel#makeStatusTable appears to support a pluggable dashboard column:
        for (DashboardColumnPlugin plugin : LoadedExtensions.getInstance().getDashboardColumnPlugins().values()) {
            if (!plugin.isDisplayFirst()) {
                columns.add(plugin.getColumnHeader());
            }
        }
17:55
oh ya dude this might be easy
17:58
com.mirth.connect.plugins.DashboardColumnPlugin implement one of those and make getColumnHeader return the header name and getTableData(ChannelGroup group) return group.getChannels().size() and do the plugin signing/ignore cert tango and it might work
17:58
the other abstract methods will just need a no-op implementation to return null or an empty object
```
