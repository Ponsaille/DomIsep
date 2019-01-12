import model.Side;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.io.File;

public class Renderer {
    private TrueTypeFont titleFont;

    public Renderer() {
        this.titleFont = new TrueTypeFont(new java.awt.Font("TimesRoman", java.awt.Font.PLAIN, 30),false);
    }

    protected void renderSide(Graphics g, Side side, int x, int y, int width, int height) {
        Color color;
        Image icon = null;
        switch (side.getType()) {
            case "Champs":
                color = Color.yellow;
                try {
                    icon = new Image(new File("data/images/types/champs.jpg").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case "Prairie":
                color = Color.decode("#00FF00");
                try {
                    icon = new Image(new File("data/images/types/prairie.jpg").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case "Foret":
                color = Color.decode("#006400");
                try {
                    icon = new Image(new File("data/images/types/foret.jpg").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case "Mer":
                try {
                    icon = new Image(new File("data/images/types/mer.jpg").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                color = Color.blue;
                break;
            case "Montagne":
                color = Color.decode("#800000");
                try {
                    icon = new Image(new File("data/images/types/montagne.png").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case "Mine":
                color = Color.gray;
                try {
                    icon = new Image(new File("data/images/types/mine.jpg").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            case "Chateau":
                color = Color.white;
                try {
                    icon = new Image(new File("data/images/types/chateau.jpg").getAbsolutePath());
                } catch (SlickException e) {
                    e.printStackTrace();
                }
                break;
            default:
                color = Color.black;
                break;
        }
        if(icon != null) {
            icon.draw(x, y, width, height);
        } else {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawString(Integer.toString(side.getCrowns()), x + 1, y + 1);
    }

    protected void renderInstructions(String text) {
        titleFont.drawString(340, 10, text, Color.white);
    }
}
