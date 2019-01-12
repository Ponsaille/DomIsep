import model.Partie;
import model.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

import java.io.File;
import java.util.List;

public class EndScreen extends BasicGameState {
    public static final int ID = 3;
    private GameContainer container;
    private Partie partie;
    private StateBasedGame game;
    private Image background;
    private TrueTypeFont titleFont;
    private TrueTypeFont font;

    public EndScreen(Partie partie) {
        this.partie = partie;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.game = game;
        this.background = new Image(new File("data/images/home_background.png").getAbsolutePath());
        this.titleFont = new TrueTypeFont(new Font("TimesRoman", Font.PLAIN, 50),false);
        this.font = new TrueTypeFont(new Font("TimesRoman", Font.PLAIN, 30),false);;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        titleFont.drawString(250, 10, "Resultats", Color.black);
        g.setColor(Color.white);
        g.fillRect(200, 280, 400, 200);

        List<Player> players = partie.getPlayers();
        for (int i = 0; i<players.size(); i++) {
            font.drawString(250, 300 + i*35, (players.get(i).getIsAI() ? "AI " : "Player "), Color.black);
            font.drawString(550, 300 + i*35, Integer.toString(this.partie.getPlayers().get(i).countPoints()), Color.black);
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
