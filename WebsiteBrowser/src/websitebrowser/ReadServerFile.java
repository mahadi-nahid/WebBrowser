
package websitebrowser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author MAHADI HASAN NAHID
 */

public class ReadServerFile extends JFrame {
    
    private JTextField enterField;
    private JEditorPane contentsArea;
    
    public ReadServerFile() {
        
        super("Simple Web Browser");
        
        enterField = new JTextField("Enetr File URL Here");
        
        enterField.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                getThePage(ae.getActionCommand());
            }
        });
        
        add(enterField, BorderLayout.NORTH);
        
        contentsArea = new JEditorPane();
        
        contentsArea.setEditable(false);
        
        contentsArea.addHyperlinkListener(
                new HyperlinkListener() {

            @Override
            public void hyperlinkUpdate(HyperlinkEvent he) {
                if(he.getEventType() ==
                         HyperlinkEvent.EventType.ACTIVATED) {
                    
                    getThePage(he.getURL().toString());
                }
            }
        });
        
        
        add(new JScrollPane(contentsArea), BorderLayout.CENTER);
        
        
        setSize(800, 500);
        setLocation(300, 100);
        setVisible(true);
        
    }
    
    
    private void getThePage(String location) {
        
        try {
            
            contentsArea.setPage(location);
            enterField.setText(location);
            
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(this, 
                    "Error retrieving specified URL", "Bad URL", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
   public static void main(String[] args) {
        
        ReadServerFile application = new ReadServerFile();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
