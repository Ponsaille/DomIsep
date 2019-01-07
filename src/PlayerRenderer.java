import org.newdawn.slick.*;
import org.newdawn.slick.Color;

public class PlayerRenderer {
    private Player player;
    private int x;
    private int y;
    private int id;

    public PlayerRenderer(Player player, int[] position, int id) {
        this.player = player;
        this.x = position[0];
        this.y = position[1];
        this.id = id;
    }

    private int getRelativeX(int x) {
        return x + this.x;
    }

    private int getRelativeY(int y) {
        return y + this.y;
    }

    public void render(Graphics g) {
        this.renderBoard(g);
        this.renderName(g);
        this.renderPoints(g);
    }

    public void renderBoard(Graphics g) {
        int width = 150;
        int height = 150;

        Side[][] kingdom = player.getKingdom();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                renderSide(g, kingdom[x][y], getRelativeX(x*width/9), getRelativeY(y*height/9), width/9, height/9);
            }
        }
    }

    public void renderSide(Graphics g, Side side, int x, int y, int width, int heigth) {
        Color color;
        switch (side.getType()) {
            case "Champs":
                color = Color.yellow;
                break;
            case "Prairies":
                color = Color.decode("#00FF00");
                break;
            case "Foret":
                color = Color.decode("#006400");
                break;
            case "Mers":
                color = Color.blue;
                break;
            case "Montagnes":
                color = Color.decode("#800000");
                break;
            case "Mines":
                color = Color.gray;
                break;
            case "Chateau":
                color = Color.white;
                break;
            default:
                color = Color.black;
                break;
        }
        g.setColor(color);
        g.fillRect(x,y,width,heigth);
        g.setColor(Color.white);
        g.drawRect(x,y,width,heigth);
    }

    private void renderName(Graphics g) {
        g.drawString("Player " + this.id, getRelativeX(0), getRelativeY(175));
    }

    private void renderPoints(Graphics g) {
        g.drawString(Integer.toString(this.player.countPoints()), getRelativeX(175), getRelativeY(0));
    }
}
