import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Arrays;
import java.util.List;

public class MiddleRenderer {
    private Middle middle;
    private Partie partie;
    private List<PlayerRenderer> playerRenderers;
    private int startX;
    private int startY;

    public MiddleRenderer(Middle middle, List<PlayerRenderer> playerRenderers, Partie partie) {
        this.startX = 300;
        this.startY = 250;
        this.middle = middle;
        this.playerRenderers = playerRenderers;
        this.partie = partie;
    }

    public void render(Graphics g) {
        Domino[][] middle = this.middle.getMiddle();
        renderColumn(g, middle[0], startX, startY);
        renderColumn(g, middle[1], startX + 100, startY);
        King[][] kings = this.middle.getKings();
        renderKingsColumn(g, kings[0], startX, startY, 20);
        renderKingsColumn(g, kings[1], startX + 100, startY, 20);
    }

    public void renderColumn(Graphics g, Domino[] column, int x, int y) {
        for (int i = 0; i < column.length; i++) {
            if (column[i] != null) {
                renderDomino(g, column[i], x, y + i * (20 + 5), 40, 20);
            }
        }
    }

    public void renderDomino(Graphics g, Domino domino, int x, int y, int width, int height) {
        renderSide(g, domino.getLeftSide(), x, y, width / 2, height);
        renderSide(g, domino.getRightSide(), x + width / 2, y, width / 2, height);
    }

    public void renderSide(Graphics g, Side side, int x, int y, int width, int height) {
        Color color;
        switch (side.getType()) {
            case "Champs":
                color = Color.yellow;
                break;
            case "Prairie":
                color = Color.decode("#00FF00");
                break;
            case "Foret":
                color = Color.decode("#006400");
                break;
            case "Mer":
                color = Color.blue;
                break;
            case "Montagne":
                color = Color.decode("#800000");
                break;
            case "Mine":
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
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawString(Integer.toString(side.getCrowns()), x + 1, y + 1);
    }

    public void renderKingsColumn(Graphics g, King[] kings, int x, int y, int radius) {
        for (int i = 0; i < kings.length; i++) {
            if (kings[i] != null) {
                renderKing(g, kings[i], x - radius - 2, y + i * (radius + 5), radius);
            }
        }
    }

    public void renderKing(Graphics g, King king, int x, int y, int radius) {
        g.setColor(king.getColor());
        g.fillOval(x, y, radius, radius);
        g.setColor(Color.white);
        g.drawOval(x, y, radius, radius);
    }

    public void update(GameContainer container, StateBasedGame game) {

        if(this.partie.getGameStage() == 0 || this.partie.getGameStage() == 1 || this.partie.getGameStage() == 3) {
            for(King king : this.middle.getKings()[0]) {
                if(king != null) {
                    if(king.getPlayer().getIsAI()) {
                        this.handleAI(king);
                    }
                    break;
                }
            }
        }
    }

    private void handleAI(King king) {
        AI player = (AI) king.getPlayer();
        List<Domino> unusedDominos = middle.getUnUsedDominos();
        Domino bestDomino = player.bestDomino(unusedDominos);
        Domino dominoToPlace = this.middle.moveKing(king, Arrays.asList(this.middle.getMiddle()[1]).indexOf(bestDomino));
        if(dominoToPlace != null) {
            int[] data = player.getBestPosition(dominoToPlace);
            int[] position = {data[0], data[1]};
            player.placeDomino(dominoToPlace, position, data[2]);
            /*if(this.partie.getGameStage() == 3) {
                this.partie.setGameStage(4);
                this.partie.getMiddle().removeFirstKing();
            } else {*/
                this.nullStateEnded();
            /*}*/
        }
    }


    public void updateDominos(int mouseX, int mouseY) {
        Domino[][] middle = this.middle.getMiddle();
        updateColumn(mouseX, mouseY, middle[0], startX, startY);
        updateColumn(mouseX, mouseY, middle[1], startX + 100, startY);
    }

    public void updateColumn(int mouseX, int mouseY, Domino[] column, int x, int y) {
        for (int i = 0; i < column.length; i++) {
            if (column[i] != null) {
                updateDomino(mouseX, mouseY, column[i], x, y + i * (20 + 5), 40, 20);
            }
        }
    }

    public void updateDomino(int mouseX, int mouseY, Domino domino, int x, int y, int width, int height) {
        if (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height) {
            // Déplacer seulement le premier roi
            for(King king : this.middle.getKings()[0]) {
                if(king != null) {
                    Domino dominoToPlace = this.middle.moveKing(king, Arrays.asList(this.middle.getMiddle()[1]).indexOf(domino));
                    this.playerRenderers.get(king.getPlayer().getId()).setDominoToPlace(dominoToPlace);
                    break;
                }
            }
        }
    }

    public void nextPlayer() {
        this.partie.setGameStage(3);
        for (int i =0; i < this.middle.getKings()[1].length; i++) {
            King king = this.middle.getKings()[1][i];
            if(king != null) {
                Domino dominoToPlace = this.middle.getMiddle()[1][i];
                System.out.println(dominoToPlace);
                //this.playerRenderers.get(king.getPlayer().getId()).setDominoToPlace(dominoToPlace);

                if(king.getPlayer().getIsAI()) {
                    int[] data = ((AI) king.getPlayer()).getBestPosition(dominoToPlace);
                    int[] position = {data[0], data[1]};
                    king.getPlayer().placeDomino(dominoToPlace, position, data[2]);
                    this.partie.setGameStage(4);
                    this.partie.getMiddle().removeFirstKing();
                } else {
                    this.playerRenderers.get(king.getPlayer().getId()).setDominoToPlace(dominoToPlace);
                }
                break;
            }
        }
    }

    public boolean nullStateEnded() {
        for (King king : this.middle.getKings()[0]) {
            if(king != null) {
                return false;
            }
        }
        if (this.middle.isEmpty()) {
            System.out.println("Deck fini");
            nextPlayer();
            return false;
        } else {
            this.middle.pick();
            this.middle.sort();
        }
        return true;
    }
}
