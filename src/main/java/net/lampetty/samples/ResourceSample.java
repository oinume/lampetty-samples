package net.lampetty.samples;

import java.net.URL;

public class ResourceSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = new ResourceSample().getClass().getResource("/config/hoge.txt");
		System.out.println("url = " + url);
	}

}
