package mytubeTypes;

public class User {
	
    protected String username;

    protected String password;
    
    protected String serverAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serveraddress) {
        this.serverAddress = serveraddress;
    }
}
