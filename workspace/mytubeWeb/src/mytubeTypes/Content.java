package mytubeTypes;

public class Content {

    protected String username;

    protected String filename;
    
    protected String title;
    
    protected String topic;
    
    protected String serverAddress;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFileName() {
        return filename;
    }

    public void setFileName(String fileName) {
        this.filename = fileName;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serveraddress) {
        this.serverAddress = serveraddress;
    }
}
