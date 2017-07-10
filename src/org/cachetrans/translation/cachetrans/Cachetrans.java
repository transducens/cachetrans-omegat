package org.cachetrans.translation.cachetrans;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cachetrans.translation.SourceSegment;
import org.cachetrans.utils.UtilsConsole;

public class Cachetrans {

	static ArrayList<Integer> segmentLengths = new ArrayList<Integer>();
	static HashMap<String, String> segments = new HashMap<String, String>();
	static String configFile = "";
	static boolean itsLoaded = false;

	public static void setConfigFile(String c) {
		if (!c.equals(configFile)) {
			configFile = c;
			init();
		}
		configFile = c;
	}

	public static ArrayList<Integer> getSegmentLengths() {
		return segmentLengths;
	}

	public static void addSegmentLenght(int length) {
		segmentLengths.add(length);
	}

	public static void init() {
		segments = new HashMap<String, String>();
		itsLoaded = false;
		try {
			loadSegments();
		} catch (Exception e) {
			System.err.println("Problem with cached translation files: incorrect ini file");
			e.printStackTrace();
		}
		itsLoaded = true;
	}

	public static List<String> getTranslation(List<SourceSegment> sSegments) {

		if (!itsLoaded) {
			System.out.println("###ERROR: Cachetrans not loaded");
			return new ArrayList<String>();
		}

		ArrayList<String> ret = new ArrayList<String>();

		String sSegment, tSegment;

		for (SourceSegment s : sSegments) {
			sSegment = s.getSourceSegmentText();// .toLowerCase();
			tSegment = segments.get(sSegment);
			tSegment = (tSegment != null) ? tSegment : "";
			ret.add(matchUpper(s.getSourceSegmentText(), tSegment.trim()));
		}

		return ret;
	}

	public static String matchUpper(String source, String target) {
		StringBuilder sb = new StringBuilder();

		String[] sourceSplit = source.split(" ");
		String[] targetSplit = target.split(" ");

		int sourceLength = sourceSplit.length;

		String sourceWord;
		String targetWord;

		for (int i = 0; i < targetSplit.length; i++) {
			if (sb.length() > 0)
				sb.append(" ");

			if (i >= sourceLength) {
				sb.append(targetSplit[i]);
				continue;
			}
			sourceWord = sourceSplit[i];
			targetWord = targetSplit[i];

			if (Character.isUpperCase(sourceWord.charAt(0))) {
				if (sourceWord.length() > 1 && Character.isUpperCase(sourceWord.charAt(1))
						|| targetWord.length() == 0) {
					sb.append(targetWord.toUpperCase());
				} else {
					sb.append(Character.toUpperCase(targetWord.charAt(0)));
					sb.append(targetWord.substring(1));
				}
			} else {
				sb.append(targetWord);
			}
		}

		return sb.toString();
	}

	public static void loadSegments() throws Exception {
		InputStream sSource = null, tSource = null;
		String sSeg, tSeg;
		BufferedReader sBr, tBr;

		InputStream config = null;
		try {
			config = UtilsConsole.openFile(configFile);
		} catch (Exception ex) {
			System.err.println("Problem with cached translation files: ini file " + configFile +  " could not be loaded");
			ex.printStackTrace();
			return;
		}

		BufferedReader configReader = new BufferedReader(new InputStreamReader(new DataInputStream(config)));
		String configLine;
		while ((configLine = configReader.readLine()) != null) {
			String[] files = configLine.split("\t");

			for (int i = 0; i < files.length; i += 2) {
				// System.out.println(files[i] + " " + files[i + 1]);
				sSource = UtilsConsole.openFile(files[i]);
				tSource = UtilsConsole.openFile(files[i + 1]);
				sBr = new BufferedReader(new InputStreamReader(new DataInputStream(sSource)));
				tBr = new BufferedReader(new InputStreamReader(new DataInputStream(tSource)));
				while ((sSeg = sBr.readLine()) != null && (tSeg = tBr.readLine()) != null) {
					segments.put(sSeg, tSeg);
				}
			}
		}

		System.out.println("Segments loaded " + segments.keySet().size());

	}
}
