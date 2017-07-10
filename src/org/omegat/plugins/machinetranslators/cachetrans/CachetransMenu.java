package org.omegat.plugins.machinetranslators.cachetrans;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.omegat.core.Core;
import org.omegat.core.CoreEvents;
import org.omegat.core.events.IApplicationEventListener;
import org.omegat.plugins.machinetranslators.cachetrans.preferences.CachetransPreferencesDialog;

public class CachetransMenu {
   
//
    /** Option for activating the geometric recommender. */    
    final JMenuItem cachetransMenu;

   /**
     * Constructor of the class, which initialises the control variables in the
     * class and menus.
     * @param match_coloring Object that controls the coloring in the matcher.
     */
    public CachetransMenu() {

        cachetransMenu = new JMenuItem("Cachetrans configuration");
        cachetransMenu.setEnabled(true);
        cachetransMenu.addActionListener(geometricRecommendingMenuItemActionListener);
        
        CoreEvents.registerApplicationEventListener(new IApplicationEventListener(){
            public void onApplicationStartup() {
            	Core.getMainWindow().getMainMenu().getOptionsMenu().add(cachetransMenu);
            }

            public void onApplicationShutdown() {
            }
        });
    }

    
    protected ActionListener geometricRecommendingMenuItemActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	new CachetransPreferencesDialog().setVisible(true);
        }
    };

}
