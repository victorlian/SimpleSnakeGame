import java.awt.EventQueue;

public class SnakeApp {
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
					Game g= Game.getInstance();
					g.startGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}


}
