package bropals.tag16_2.creature;

/**
	The player's boat thing
*/
public class Ironclad extends BaseCreature {
	
	public Ironclad(float x, float y, float w, float h) {
		super(x, y, w, h);
	}
	
	public void update() {
		super.update();
		
		Point mousePos = GameWindow.getGameWindow().getMousePosition();
	}
	
}

