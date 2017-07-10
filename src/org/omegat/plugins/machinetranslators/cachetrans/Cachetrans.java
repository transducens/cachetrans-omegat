package org.omegat.plugins.machinetranslators.cachetrans;

import java.util.ArrayList;
import java.util.List;

import org.cachetrans.translation.SourceSegment;
import org.omegat.core.machinetranslators.BaseTranslate;
import org.omegat.plugins.machinetranslators.cachetrans.preferences.CachetransPreferences;
import org.omegat.util.Language;

public class Cachetrans extends BaseTranslate {

	public Cachetrans() {
		CachetransPreferences.init();
		new CachetransMenu();
	}

	@Override
	protected String translate(Language sLang, Language tLang, String text) throws Exception {
		SourceSegment ss = new SourceSegment(text, 0, false, 0, 0);
		List<SourceSegment> ssl = new ArrayList<SourceSegment>();
		ssl.add(ss);
		List<String> ret = org.cachetrans.translation.cachetrans.Cachetrans.getTranslation(ssl);
		return ret.get(0);
	}

	public List<String> massTranslate(Language sLang, Language tLang, List<String> texts) throws Exception {
		ArrayList<String> ret = new ArrayList<>();
		for (String text : texts) {
			SourceSegment ss = new SourceSegment(text, 0, false, 0, 0);
			List<SourceSegment> ssl = new ArrayList<SourceSegment>();
			ssl.add(ss);
			List<String> trans = org.cachetrans.translation.cachetrans.Cachetrans.getTranslation(ssl);
			for (String t : trans)
			{
				ret.add(t);
			}
		}
		return ret;
	}

	@Override
	protected String getPreferenceName() {
		return "Cachetrans";
	}

	@Override
	public String getName() {
		return "Cachetrans";
	}

}
