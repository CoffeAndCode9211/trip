package com.trip.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trip.dto.DocumentMaster;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;


@Path("/")
public interface DocumentIf {   
	
	
	
	@POST
	@Path("/documents")
	@Consumes("multipart/form-data")	
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addDocumentMaster(@MultipartForm DocumentMaster documentMaster);
	
	@PUT
	@Path("/documents/image/{id}")
	@Consumes("multipart/form-data")	
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateDocumentImage(@MultipartForm DocumentMaster documentMaster,@PathParam("id") int id);
	  
	@GET
	@Path("/documents/open/{id}")
    @Produces({MediaType.MULTIPART_FORM_DATA})
	public Response getDocumentToOpen(@PathParam("id") int id);
	
	
}
