
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import views.Iluminacion;


public class Programa {
	public static void main(String[] args) {
		
		/* Look and feel */
		try {

			for (var info : UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {

					UIManager.setLookAndFeel(info.getClassName());
					break;

				}
			}
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {

		}

		EventQueue.invokeLater(new Iluminacion());
		
	}
}
