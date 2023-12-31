package com.jonbartels.mirthdashboard;

import com.mirth.connect.client.core.ClientException;
import com.mirth.connect.client.core.api.BaseServletInterface;
import com.mirth.connect.client.core.api.MirthOperation;
import com.mirth.connect.client.core.api.Param;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.kaurpalang.mirth.annotationsplugin.annotation.MirthApiProvider;
import com.kaurpalang.mirth.annotationsplugin.type.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/listeningport")
@Tag(name = "Bartels Mirth Dashboard")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@MirthApiProvider(type = com.kaurpalang.mirth.annotationsplugin.type.ApiProviderType.SERVLET_INTERFACE)
public interface ListeningPortServletInterface extends BaseServletInterface {
    @GET
    @Path("/{channelId}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @ApiResponse(responseCode = "200", description = "Found the information",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON),
                    @Content(mediaType = MediaType.APPLICATION_XML)
            })
    @MirthOperation(
            name = "getListeningPort",
            display = "Get the listening port of the given channel ID",
            permission = ListeningPortPermissions.GETLISTENINGPORT
    )
    String getListeningPort(
            @Param("channelId") @Parameter(description = "The ID of the channel to get the listening port for.", required = true) @PathParam("channelId") String channelId
    )
            throws ClientException;
}
