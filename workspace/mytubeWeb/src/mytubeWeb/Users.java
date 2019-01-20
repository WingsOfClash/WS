package mytubeWeb;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mytubeTypes.User;

@RequestScoped
@Path("/users")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class Users {
	
	static ArrayList<ArrayList<String>> usersCredentials = new ArrayList<>();
	
	@POST
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
    public Response save(User UserCredentials) {
		ArrayList<String> cred = new ArrayList<>();
        cred.add(UserCredentials.getUsername());
        cred.add(UserCredentials.getPassword());
        cred.add(UserCredentials.getServerAddress());
        usersCredentials.add(cred);
        return Response.ok().build();
    }
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getAllUsers() {
		ArrayList<String> usersNames = new ArrayList<>();
		for(ArrayList<String> credentials : usersCredentials) {
			usersNames.add(credentials.get(0));
		}
		return usersNames; 
	}
	
	@GET
	@Path("/all/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getUserCredentials(@PathParam("username") String userName) {
		for(ArrayList<String> credentials : usersCredentials) {
			if(credentials.get(0).equals(userName)) {
				return credentials;
			}
		}
		return null; 
	}
	
	@DELETE
    @Path("/delete/{username}")
    public Response delete(@PathParam("username") String userName){
		for(ArrayList<String> credentials : usersCredentials) {
			if(credentials.get(0).equals(userName)) {
				usersCredentials.remove(credentials);
				return Response.ok().build();
			}
		}
		return Response.notModified().build();
	}

	
}

