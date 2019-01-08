import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;
import java.io.File;
import java.util.*;

public class GameScreen extends BasicGameState {
    public static final int ID = 2;
    private Partie partie;
    private StateBasedGame game;
    private List<PlayerRenderer> playerRenderers;
    private MiddleRenderer middleRenderer;
    private int gameState = 0;


    public GameScreen(Partie partie) {
        this.partie = partie;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        List<Player> players = partie.getPlayers();
        this.game = game;
        this.playerRenderers = new ArrayList<>();
    }

    public void upgrade() {
        int[][] positions = {
                {0,0},
                {600, 0},
                {0, 400},
                {600, 400}
        };
        for(int i = 0; i < partie.getPlayers().size(); i++) {
            System.out.println(this.playerRenderers);
            this.playerRenderers.add(new PlayerRenderer(partie.getPlayers().get(i), positions[i]));
        }
        this.partie.start();
        this.middleRenderer = new MiddleRenderer(partie.getMiddle(), this.playerRenderers);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (PlayerRenderer playerRenderer:this.playerRenderers) {
            playerRenderer.render(g);
        }
        this.middleRenderer.render(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        //this.middleRenderer.update(container, game, delta, gameState);
        if(this.gameState == 0) {
            if(this.middleRenderer.nullStateEnded()) {
                this.gameState = 1;
            }
        }
        for (PlayerRenderer playerRenderer:this.playerRenderers) {
            playerRenderer.update(mouseX, mouseY);
        }
    }

    public void keyReleased(int key, char c) {
        for (PlayerRenderer playerRenderer:this.playerRenderers) {
            playerRenderer.keyReleased(key, c);
        }
    }

    public void mousePressed(int button, int x, int y) {
        if(button == 0) {
            if(gameState == 0) {
                this.middleRenderer.updateDominos(x, y);
            } else if(gameState == 1) {
                this.middleRenderer.updateDominos(x,y);
                this.gameState = 2;
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
