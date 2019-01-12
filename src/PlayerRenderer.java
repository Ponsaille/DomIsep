import org.newdawn.slick.*;
import org.newdawn.slick.Color;

import java.io.File;
import java.util.Arrays;

public class PlayerRenderer {
    private Player player;
    private Partie partie;
    private int x;
    private int y;
    private Domino dominoToPlace;
    private int orientation;
    private int mouseX;
    private int mouseY;

    public PlayerRenderer(Player player, Partie partie, int[] position) {
        this.player = player;
        this.partie = partie;
        this.x = position[0];
        this.y = position[1];
        this.dominoToPlace = null;
        this.orientation = 0;
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
        this.renderDominoToPlace(g);
    }

    public void renderBoard(Graphics g) {
        int width = 200;
        int height = 200;

        Side[][] kingdom = player.getKingdom();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                renderSide(g, kingdom[x][y], getRelativeX(x*width/9), getRelativeY(y*height/9), width/9, height/9);
            }
        }
    }

    public void renderDomino(Graphics g, Domino domino, int x, int y, int width, int height, int orientation) {
        renderSide(g, domino.getLeftSide(), x, y, width / 2, height);
        switch (orientation) {
            case 0:
                renderSide(g, domino.getRightSide(), x + width / 2, y, width / 2, height);
                break;
            case 1:
                renderSide(g, domino.getRightSide(), x, y - height, width / 2, height);
                break;
            case 2:
                renderSide(g, domino.getRightSide(), x - width / 2, y, width / 2, height);
                break;
            case 3:
                renderSide(g, domino.getRightSide(), x, y + height, width / 2, height);
                break;
        }
    }

    public void renderSide(Graphics g, Side side, int x, int y, int width, int height) {
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
        g.setColor(color);
        g.fillRect(x, y, width, height);
        if(icon != null) {
            icon.draw(x, y, width, height);
        }
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawString(Integer.toString(side.getCrowns()), x + 1, y + 1);
    }

    private void renderName(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Player " + this.player.getId(), getRelativeX(0), getRelativeY(225));
    }

    private void renderPoints(Graphics g) {
        g.setColor(Color.white);
        g.drawString(Integer.toString(this.player.countPoints()), getRelativeX(225), getRelativeY(0));
    }

    private void renderDominoToPlace(Graphics g) {
        if(dominoToPlace != null) {
            renderDomino(g, this.dominoToPlace, this.mouseX, this.mouseY, 40, 20, this.orientation);
        }
    }

    public void setDominoToPlace(Domino domino) {
        System.out.println("Player " + player.getId());
        if(domino != null && this.player.canPlay() && this.player.canPlace(domino)) {
            this.dominoToPlace = domino;
            this.orientation = 0;
        } else {
            System.out.println("Vous ne pouvez plus jouer");
        }
    }

    public void update(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }

    public void keyReleased(int key, char c) {
        if(dominoToPlace != null) {
            if (key == 19) {
                this.orientation = (this.orientation+1)%4;
            }
        }
    }

    public void moussePressed(int button, int x, int y) {
        if (dominoToPlace != null) {
            this.mouseBoardTrack(x, y);
            //this.player.countPoints();
            //System.out.println("End points");
        }
    }

    private void mouseBoardTrack(int mouseX, int mouseY) {
        int width = 200;
        int height = 200;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if(mouseX > getRelativeX(x*width/9) && mouseX < getRelativeX((x+1)*width/9) && mouseY > getRelativeY(y*height/9) && mouseY < getRelativeY((y+1)*height/9)) {
                    this.placeDomino(x, y);
                }
            }
        }
    }

    public boolean hasADominoToPlace() {
        return !(dominoToPlace == null);
    }

    private void placeDomino(int x, int y) {
        int[] position = {x, y};
        System.out.println(dominoToPlace);
        System.out.println(Arrays.toString(position));
        System.out.println(this.orientation);
        if(this.player.placeDomino(this.dominoToPlace, position, this.orientation)) {
            this.dominoToPlace = null;
            if(this.partie.getGameStage() == 3) {
                this.partie.setGameStage(4);
                this.partie.getMiddle().removeFirstKing();
            } else {
                this.partie.setGameStage(1);
            }
        }
    }
}
