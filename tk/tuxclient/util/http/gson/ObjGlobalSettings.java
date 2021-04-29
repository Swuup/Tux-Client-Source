package tk.tuxclient.util.http.gson;

public class ObjGlobalSettings {

    private boolean isWhitelisted;
    private String latestVersion;

    public String getLatestVersion() {
        return latestVersion;
    }

    public boolean isWhitelisted() {
        return isWhitelisted;
    }
}
