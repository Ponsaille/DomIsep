import model.Partie;
import model.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.util.*;

public class GameScreen extends BasicGameState {
    public static final int ID = 2;
    private Partie partie;
    private StateBasedGame game;
    private List<PlayerRenderer> playerRenderers;
    private MiddleRenderer middleRenderer;
    private Image background;


    public GameScreen(Partie partie) {
        this.partie = partie;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        background = new Image(new File("data/images/board.jpg").getAbsolutePath());
        List<Player> players = partie.getPlayers();
        this.game = game;
        this.playerRenderers = new ArrayList<>();
    }

    public void upgrade() {
        //On s'assure que la partie puisse être jouée sinon on reviens à l'écrant d'accceuil
        if(!this.partie.start()) {
            this.game.enterState(MainScreen.ID);
        }

        int[][] positions = {
                {0,0},
                {550, 0},
                {0, 350},
                {550, 350}
        };
        for(int i = 0; i < partie.getPlayers().size(); i++) {
            this.playerRenderers.add(new PlayerRenderer(partie.getPlayers().get(i), partie, positions[i]));
        }
        this.middleRenderer = new MiddleRenderer(partie.getMiddle(), this.playerRenderers, this.partie);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0,0);
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
        //this.middleRenderer.update(container, game, delta);
        if(this.partie.getGameStage() == 0 || this.partie.getGameStage() == 2) {
            if(this.middleRenderer.nullStateEnded()) {
                this.partie.setGameStage(1);
            }
        }
        for (PlayerRenderer playerRenderer:this.playerRenderers) {
            playerRenderer.update(mouseX, mouseY);
            if(playerRenderer.hasADominoToPlace() && this.partie.getGameStage() != 3 && this.partie.getGameStage() != 4) {
                this.partie.setGameStage(2);
            }
        }

        if(this.partie.getGameStage() == 4) {
            this.middleRenderer.nextPlayer();
        }

        if(this.partie.getGameStage() == 3 && this.partie.getMiddle().isNoKing()) {
            this.game.enterState(EndScreen.ID);
        }

        this.middleRenderer.update(container, game);
    }

    public void keyReleased(int key, char c) {
        for (PlayerRenderer playerRenderer:this.playerRenderers) {
            playerRenderer.keyReleased(key, c);
        }
    }

    public void mousePressed(int button, int x, int y) {
        if(button == 0) {
            if(this.partie.getGameStage() == 0  || this.partie.getGameStage() == 1) {
                this.middleRenderer.updateDominos(x, y);
            }
        }
        if(this.partie.getGameStage() == 2 || this.partie.getGameStage() == 3) {
            for (PlayerRenderer playerRenderer:this.playerRenderers) {
                playerRenderer.moussePressed(button, x, y);
            }
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
