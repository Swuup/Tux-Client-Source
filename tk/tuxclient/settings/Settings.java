package tk.tuxclient.settings;

import java.awt.Color;

public class Settings {

    int textColor = -1;
    int backgroundColor = Color.black.getRGB();
    float scale = 1;
    boolean background = false;

    public void setTextColor(int color) {
        this.textColor = color;
    }

    public void setTextColor(Color color) {
        this.textColor = color.getRGB();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color.getRGB();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public void toggleBackground() {
        background = !background;
    }

    public boolean shouldRenderBackground() {
        return background;
    }

}
