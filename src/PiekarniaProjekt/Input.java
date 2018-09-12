package PiekarniaProjekt;


import javax.swing.JOptionPane;

/**
 * klasa tworząca  JOptionPane
 */

public class Input{
    
    /**
     * metoda służąca do wywołania okna
     * @param message wiadomość przedstawiona przez program
     */
    public static void showMessage(String message){
        
        JOptionPane.showMessageDialog(null,message,"Message",
                                               JOptionPane.INFORMATION_MESSAGE);	
    }
			

}
	
