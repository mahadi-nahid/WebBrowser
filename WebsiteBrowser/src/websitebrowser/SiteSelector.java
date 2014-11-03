
package websitebrowser;

import java.applet.AppletContext;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author MAHADI HASAN NAHID
 */


public class SiteSelector extends JApplet {
    
    private HashMap<String, URL> sites;
    private ArrayList<String> siteNames;
    private JList siteChooser;
    
    
    @Override
    public void init() {
        
        sites = new HashMap<>();
        siteNames = new ArrayList<>();
        
        getSitesFromHTMLParameters();
        
        add(new JLabel("Choose A Site To Browse"), BorderLayout.NORTH);
        
        siteChooser = new JList(siteNames.toArray());
        
        siteChooser.addListSelectionListener(
                (ListSelectionEvent lse) -> {
                    Object object = siteChooser.getSelectedValue();
                    
                    URL newDcument = sites.get(object);
                    
                    AppletContext browser = getAppletContext();
                    
                    browser.showDocument(newDcument);
        });
        
        add(new JScrollPane(siteChooser));
    }
    
    private void getSitesFromHTMLParameters() {
        
        String title;
        String location;
        
        URL url;
        int counter = 0;
        
        title = getParameter("title" +counter);
        
        while(title != null) {
            
            location = getParameter("location" +counter);
            
            try{
                
                url = new URL(location);
                sites.put(title, url);
                siteNames.add(title);
                
                
            } catch(MalformedURLException urlException) {
              
                urlException.printStackTrace();
            }
            
            ++counter;
            
            title = getParameter("title" +counter);
        }
        
    }
}
