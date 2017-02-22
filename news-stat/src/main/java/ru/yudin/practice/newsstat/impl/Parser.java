/*
 * Parser
 * 
 * 1.0
 * 
 * Реализация получения данных с новостного ресурса,
 * извлечения заголовков новостей,
 * подсчёта десяти наиболее высокочастотных слов
 * 
 */

package ru.yudin.practice.newsstat.impl;

import ru.yudin.practice.newsstat.able.IParser;

import org.osgi.framework.BundleContext;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser implements IParser{
	BundleContext bundleContext;
	private static HashMap<String, Integer> words;
	static final String[] PREPS = { "без", "безо", "близ", "в", "во", "вместо", "вне", "для", "до", "за", "из", "изо",
			"из-за", "из-под", "к", "ко", "кроме", "между", "меж", "на", "над", "надо", "о", "об", "обо", "от", "ото",
			"перед", "передо", "пред", "предо", "по", "под", "подо", "при", "про", "ради", "с", "со", "сквозь", "среди",
			"у", "через", "чрез", "а", "и", "как", "или", "не", "-", "–", "—" };//конструкции, исключающиеся из рейтинга

	public Parser(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void parse(String url) {//получение rss-структуры, вывод результата
		words = new HashMap<>();		
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream((new URL(url)).openStream());
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bufferedInputStream);
			visit(doc);
		} catch (Exception e) {
			System.out.println("aif-parser: network error");
			return;
		}
		
		ArrayList list = new ArrayList(words.entrySet());		
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
				return b.getValue() - a.getValue();
			}
		});
		
		for (int i = 0; i < 10; i++) {
			System.out.println(list.get(i));
		}
	}

	public static void visit(Node node) {//рекурсивный проход rss-нод, извлечение заголовков, разбиение на слова
		NodeList childList = node.getChildNodes();
		ArrayList preps = new ArrayList(Arrays.asList(PREPS));
		for (int i = 0; i < childList.getLength(); i++) {
			if (childList.item(i).getNodeName() == "title" && node.getNodeName() == "item") {
				String textContent = childList.item(i).getTextContent().toLowerCase();
				String[] wordsInString = textContent.split("[ +()<>,;:.!?\\s0123456789]+");
				for (String word : wordsInString) {
					if (!preps.contains(word)) {
						Integer count = words.get(word);
						words.put(word, count == null ? 1 : count + 1);
					}
				}
				return;
			}
			visit(childList.item(i));
		}
	}
}
