/*
 * IParser
 * 
 * 1.0
 * 
 * Интерфейс для метода, получающего URL-адрес новостного ресурса
 * и выводящего на экран 10 наиболее высокочастотных слов
 * 
 */

package ru.yudin.practice.newsstat.able;

public interface IParser {
	public void parse(String url);
}
