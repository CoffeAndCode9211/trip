package com.trip.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.trip.dto.UsersTO;

@Path("/users")
public interface UsersIf {

	@GET
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public List<UsersTO> getUsersDetails(@QueryParam("txtlastName")String lastName, 
			@QueryParam("txtFirstName") String firstName, 
			@QueryParam("txtEmail") String email, 
			@QueryParam("txtPhone") String phone);

	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public UsersTO getUsersById(@PathParam("id") int id);

	@POST
	@Path("/")
	@Produces({MediaType.APPLICATION_JSON})
	public Response addUsers( UsersTO empTo);

	@PUT
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateUsers(UsersTO sm, @PathParam("id") int id);

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteUsers(@PathParam("id") int id);
	
	@PUT
	@Path("/logout")
	@Produces({MediaType.APPLICATION_JSON})
	public void logout(@Context HttpServletRequest req);
	
	
	
}
