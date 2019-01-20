package mytubeWeb;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mytubeTypes.Content;

@RequestScoped
@Path("/contents")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class Contents {
	
	static ArrayList<ArrayList<String>> contentsCredentials = new ArrayList<>();
	
	@POST
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
    public Response save(Content ContentCredentials) {
		ArrayList<String> cred = new ArrayList<>();
        cred.add(ContentCredentials.getUsername());
        cred.add(ContentCredentials.getFileName());
        cred.add(ContentCredentials.getTitle());
        cred.add(ContentCredentials.getTopic());
        cred.add(ContentCredentials.getServerAddress());
        contentsCredentials.add(cred);
        return Response.ok().build();
    }
	
	@GET
	@Path("/contains/title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getContentsByTitle(@PathParam("title") String Title) {
		ArrayList<String> contentsAllTitles = new ArrayList<>();
		for(ArrayList<String> credentials : contentsCredentials) {
			if(credentials.get(2).toLowerCase().contains(Title.toLowerCase())) {
				contentsAllTitles.add(credentials.get(2));
			}
		}
		return contentsAllTitles; 
	}
	
	@GET
	@Path("/contains/topic/{topic}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getContentsByTopic(@PathParam("topic") String topic) {
		ArrayList<String> contentsAllTopics = new ArrayList<>();
		for(ArrayList<String> credentials : contentsCredentials) {
			if(credentials.get(3).toLowerCase().contains(topic.toLowerCase())) {
				contentsAllTopics.add(credentials.get(2));
			}
		}
		return contentsAllTopics; 
	}
	
	@GET
	@Path("/contains/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getContentsByAll() {
		ArrayList<String> contentsAllTopics = new ArrayList<>();
		for(ArrayList<String> credentials : contentsCredentials) {
			contentsAllTopics.add(credentials.get(2));
		}
		return contentsAllTopics; 
	}
	
	@GET
	@Path("/all/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<String> getContentCredentials(@PathParam("username") String userName) {
		ArrayList<String> contentsUserTitles = new ArrayList<>();
		for(ArrayList<String> credentials : contentsCredentials) {
			if(credentials.get(0).equals(userName)) {
				contentsUserTitles.add(credentials.get(2));
			}
		}
		return contentsUserTitles; 
	}
	
	
	@DELETE
    @Path("/delete/{username}/{filename}")
    public Response delete(@PathParam("username") String userName, @PathParam("filename") String fileName){
		for(ArrayList<String> credentials : contentsCredentials) {
			if(credentials.get(0).equals(userName) && credentials.get(1).equals(fileName) ) {
				contentsCredentials.remove(credentials);
				return Response.ok().build();
			}
		}
		return Response.notModified().build();
	}
	
	@PUT
	@Path("/edit/{username}/{filename}/{newtitle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editTitle(@PathParam("username") String userName, @PathParam("filename") String fileName, @PathParam("newtitle") String newTitle) {
		
		for(ArrayList<String> credentials : contentsCredentials) {
			if(credentials.get(0).equals(userName) && credentials.get(1).equals(fileName) ) {
				credentials.set(2, newTitle);
				return Response.ok().build();
			}
		} 
  
		return Response.notModified().build();
	}
	
	
	@PUT
	@Path("title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ArrayList<String>> getContentTitle(@PathParam("title") String title) {
		ArrayList<ArrayList<String>> contentsByTitle = new ArrayList<>();
		for(ArrayList<String> content : contentsCredentials) {
			if(content.get(2).toLowerCase().contains(title.toLowerCase())) {
				contentsByTitle.add(content);
			}
		}
		return contentsByTitle; 
	}
	
	@PUT
	@Path("topic/{topic}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ArrayList<String>> getContentTopic(@PathParam("topic") String topic) {
		ArrayList<ArrayList<String>> contentsByTopic = new ArrayList<>();
		for(ArrayList<String> content : contentsCredentials) {
			if(content.get(2).toLowerCase().contains(topic.toLowerCase())) {
				contentsByTopic.add(content);
			}
		}
		return contentsByTopic; 
	}

}
