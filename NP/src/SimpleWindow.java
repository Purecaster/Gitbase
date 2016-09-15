import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleWindow extends JFrame {
	private JButton button1 = new JButton("Найти");
	private JLabel label1 = new JLabel("Введите номер посылки:");
	private JLabel label2 = new JLabel("");
	private JTextField textfield1 = new JTextField("",10);
SimpleWindow(){
super("Трекинг посылок Нова Пошта");
JPanel panel = new JPanel();
panel.setLayout(new FlowLayout());
panel.add(label1);
panel.add(textfield1); 
panel.add(button1);
panel.add(label2);
setContentPane(panel);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(500, 300);
setResizable(false);
setLocationRelativeTo(null);
button1.addActionListener(new ButtonEventListener());
}

class ButtonEventListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		String invnumber;
		String status;
		//invnumber="59000197201343";
		invnumber=textfield1.getText();
		status=tracking.trackpackage(invnumber);		
		label2.setText(status);	    
	}
}


}