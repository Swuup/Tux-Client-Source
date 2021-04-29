package tk.tuxclient.util.http.gson;

import java.util.UUID;

public class ObjUserCosmetics {

    private String uuid;
    private String cape_style;
    private int wings;

    public UUID getUuid() {
        return UUID.fromString(uuid);
    }

    public boolean hasCape() {
        return cape_style != null;
    }

    public boolean hasWings() {
        return wings == 1;
    }

    public String getCapeStyle() {
        return cape_style;
    }
}
