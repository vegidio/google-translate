/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * @since Apr 28th 2014
 * v1.0
 */

package com.hryun.gtranslate;

public class ExampleTranslateSimpleText
{
	/*
	 * Translating several phrases...
	 */
	public static void main(String[] args)
	{
		Translate translate;
		String text;
		
		// Putting all parameters in the execute() method
		translate = new Translate();
		text = translate.execute("I love cookies", Language.ENGLISH, Language.PORTUGUESE);
		
		System.out.println("Original text..: " + translate.getSourceText());
		System.out.println("Translated text: " + text);
		
		// Setting the parameters through setters and calling execute() without parameters
		translate.setSourceLang(Language.ENGLISH);
		translate.setDestLang(Language.SPANISH);
		translate.setSourceText("My name is Vinicius");
		text = translate.execute();
		
		System.out.println("Original text..: " + translate.getSourceText());
		System.out.println("Translated text: " + text);
	}
}