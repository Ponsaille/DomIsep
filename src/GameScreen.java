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


    public GameScreen(Partie partie) {
        this.partie = partie;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        List<Player> players = partie.getPlayers();
        this.game = game;
        this.partie.start();
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
            this.playerRenderers.add(new PlayerRenderer(partie.getPlayers().get(i), positions[i], i));
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (PlayerRenderer playerRenderer:this.playerRenderers) {
            playerRenderer.render(g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    public void keyReleased(int key, char c) {
    }

    @Override
    public int getID() {
        return ID;
    }
}
