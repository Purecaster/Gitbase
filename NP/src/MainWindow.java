import javax.swing.JFrame;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1049840817772719800L;

	MainWindow(){
		
	JFrame myWindow = new JFrame("Трекинг посылок Нова Пошта");
	myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	myWindow.setSize(400, 300);
	myWindow.setLocationRelativeTo(null);	
	
	}
	
}
