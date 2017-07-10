package org.omegat.plugins.machinetranslators.cachetrans.preferences;

import org.cachetrans.translation.cachetrans.Cachetrans;
import org.omegat.util.Preferences;

public class CachetransPreferences {
	public static String FORECAT_CACHED_FILE = "forecat_cached_file";
	
	public static void init()
	{
		if (!Preferences.existsPreference(FORECAT_CACHED_FILE))
		{
			System.out.println("Cachetrans cache file does not exist, creating deafault");
			Preferences.setPreference(FORECAT_CACHED_FILE, "/resources/en-es.ini");
		}
		System.out.println("Setting Cachetrans file to " + Preferences.getPreference(CachetransPreferences.FORECAT_CACHED_FILE));
		Cachetrans.setConfigFile(Preferences.getPreference(CachetransPreferences.FORECAT_CACHED_FILE));
	}
}
