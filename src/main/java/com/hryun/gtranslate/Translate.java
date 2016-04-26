/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * @since Apr 28th 2014
 * v1.0
 */

package com.hryun.gtranslate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translate
{
    private Language sourceLang;
    private Language destLang;
    private String sourceText;
    private String destText;

    public Translate()
    {
        initialize();
    }

    private void initialize()
    {
        sourceLang = null;
        destLang = null;
        sourceText = "";
        destText = "";
    }

    public String execute()
    {
        destText = "";

        // Check if we have all variables set
        if(sourceLang == null || sourceText.isEmpty() || destLang == null)
        {
            System.err.println("Missing parameters; please set the Source Language, Destination Language and the " +
                    "Source Text first");
        }
        else
        {
            execute(sourceText, sourceLang, destLang);
        }

        return destText;
    }

    public String execute(String text, Language sl, Language dl)
    {
        String regex, temp, params = null;
        StringBuilder translated = new StringBuilder();
        Matcher matcher;
        Pattern pattern;

        // We initialize the variables first
        initialize();

        sourceLang = sl;
        destLang = dl;
        sourceText = text;

        // URL enconding the text
        try
        {
            params = "q=" + URLEncoder.encode(text, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        // Creating the URL
        String url = "https://translate.google.com/translate_a/single?client=t&sl=" + sourceLang.getValue() + "&tl=" +
                destLang.getValue() + "&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&dt=sw&ie=UTF-8&" +
                "oe=UTF-8&prev=btn&ssel=0&tsel=0";

        // Get the JS
        String js = sendPost(url, params);

        // Parse the JS
        regex = "\\[\\[(.*?)\\]\\]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(js);

        if(matcher.find())
        {
            temp = matcher.group(1);
            regex = "\\[\"(.*?)\",\"";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(temp);
            while (matcher.find()) translated.append(matcher.group(1));
            destText = translated.toString();

            // Removing unnecessary spaces
            destText = destText.replace(" .", ".")
                    .replace(" ,", ",")
                    .replace(" -", "-")
                    .replace(" ;", ";")
                    .replace(" :", ":")
                    .replace("( ", "(")
                    .replace(" )", ")");
        }

        return destText;
    }

    private String sendPost(String urlString, String params)
    {
        String line;
        StringBuffer html = new StringBuffer();

        try
        {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Fake the User-Agent
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");

            // Setting the post parameters
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            // Check the HTTP response code
            if(conn.getResponseCode() == 200)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

                // Reading the HTML
                while ((line = in.readLine()) != null)
                    html.append(line.trim());

                in.close();
            }

            // Close the connection
            conn.disconnect();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return html.toString();
    }

    /**
     * @return the sourceLang
     */
    public Language getSourceLang()
    {
        return sourceLang;
    }

    /**
     * @param sourceLang the sourceLang to set
     */
    public void setSourceLang(Language sourceLang)
    {
        this.sourceLang = sourceLang;
    }

    /**
     * @return the destLang
     */
    public Language getDestLang()
    {
        return destLang;
    }

    /**
     * @param destLang the destLang to set
     */
    public void setDestLang(Language destLang)
    {
        this.destLang = destLang;
    }

    /**
     * @return the sourceText
     */
    public String getSourceText()
    {
        return sourceText;
    }

    /**
     * @param sourceText the sourceText to set
     */
    public void setSourceText(String sourceText)
    {
        this.sourceText = sourceText;
    }

    /**
     * @return the destText
     */
    public String getDestText()
    {
        return destText;
    }
}