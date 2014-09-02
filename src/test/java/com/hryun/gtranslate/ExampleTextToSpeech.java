/**
 * Created by vegidio on 9/2/14.
 */

package com.hryun.gtranslate;

import java.io.File;

public class ExampleTextToSpeech
{
    public static void main(String[] args)
    {
        // Putting the parameters in the constructor
        TextToSpeech tts1 = new TextToSpeech("My name is Groot", Language.ENGLISH);

        // Downloading the audio to a MP3 file
        File mp3File = new File("/Users/vegidio/tts.mp3");
        tts1.downloadAudio(mp3File);

        // Initialize the TextToSpeech class and set the text and language through methods.
        TextToSpeech tts2 = new TextToSpeech();
        tts2.setText("Meu nome é Vinícius");
        tts2.setLanguage(Language.PORTUGUESE);

        /*
         * Put the audio in a byte array. This byte array can be used in web application, for example, to play the
         * audio in a website. Just send the byte array output to the browser with the MIME type audio/mpeg.
         */
        byte[] bytes = tts2.getAudioByteArray();
    }
}