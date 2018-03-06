package listeners;

import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Cell;
import model.Pos;
import view.GameView;

public class MouseListener implements EventHandler<MouseEvent>{

	private GameView gameView;
	
	public MouseListener(GameView gameView) {
		this.gameView = gameView;
	}
	
	@Override
	public void handle(MouseEvent event) {
		if(event.getEventType() == MouseEvent.MOUSE_CLICKED && !this.gameView.getGame().isGameActive()){
			EventTarget o = event.getTarget();
			if(o instanceof Rectangle){
				Rectangle rect = (Rectangle)o;
				Pos pos = gameView.findRect(rect);
				Cell cell = gameView.getGame().getCell(pos.x, pos.y);
				if(cell.isLive()){
					cell.setLive(false);
					rect.setFill(Color.GRAY);
				}else{
					cell.setLive(true);
					rect.setFill(Color.BLUE);
				}
			}
		}
	}

}
