package com.unit.testing.controllers;

import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.unit.testing.db.UserDao;
import com.unit.testing.models.User;


@Path("/users")
public class UserService {

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getUserById(@Context HttpServletRequest servletRequest, @PathParam("id") Integer id) {

        HttpSession session = servletRequest.getSession();
        boolean authenticated = false;
        authenticated = (boolean) session.getAttribute("auth");
        UserDao dao = getUserDao();
        User user = new User();

        if (id == null || id < 1) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (!authenticated) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Map<String, Object> results = dao.getUserById(id);
        String status = (String) results.get("status");
        if (status.equals("OK")) {
            user = (User) results.get("user");
            return Response.status(Response.Status.OK).entity(user).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public UserDao getUserDao() {
        return new UserDao();
    }
}
