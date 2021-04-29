package tk.tuxclient.util;

import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class TuxDrawUtils {

    public static void drawHollowRect(int x, int y, int w, int h, int color) {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);
        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }

    public static void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        Gui.drawRect(startX, y, endX + 1, y + 1, color);
    }

    public static void drawVerticalLine(int x, int startY, int endY, int color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        Gui.drawRect(x, startY + 1, x + 1, endY, color);
    }

	public static void drawRoundedRect(double x, double y, double x1, double y1, double radius, int color) {
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2;
        y *= 2;
        x1 *= 2;
        y1 *= 2;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        setColor(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + (Math.sin((i * Math.PI / 180)) * (radius * -1)),y + radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + (Math.sin((i * Math.PI / 180)) * (radius * -1)), y1 - radius + (Math.cos((i * Math.PI / 180)) * (radius * -1)));
        }

        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y1 - radius + (Math.cos((i * Math.PI / 180)) * radius));
        }

        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x1 - radius + (Math.sin((i * Math.PI / 180)) * radius), y + radius + (Math.cos((i * Math.PI / 180)) * radius));
        }

        GL11.glEnd();

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glScaled(2, 2, 2);

        GL11.glPopAttrib();
    }

    public static void drawBorderedRoundedRect(final float x, final float y, final float x1, final float y1, final float borderSize, final int borderC, final int insideC) {
        drawRoundedRect(x, y, x1, y1, borderSize, borderC);
        drawRoundedRect(x + 0.5f, y + 0.5f, x1 - 0.5f, y1 - 0.5f, borderSize, insideC);
    }

    public static void drawBorderedRoundedRect2(final float x, final float y, final float x1, final float y1, final float borderSize, final int insideC) {
        drawRoundedRect(x + 0.5f, y + 0.5f, x1 - 0.5f, y1 - 0.5f, borderSize, insideC);
    }

    public static void drawBorderedRoundedRect(final float x, final float y, final float x1, final float y1, final float radius, final float borderSize, final int borderC, final int insideC) {
        drawRoundedRect(x, y, x1, y1, radius, borderC);
        drawRoundedRect(x + borderSize, y + borderSize, x1 - borderSize, y1 - borderSize, radius, insideC);
    }

    public static void setColor(final int color) {
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glColor4f(r, g, b, a);
    }
    
}
