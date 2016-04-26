/**
 * @author Vinícius Egidio (vegidio@gmail.com)
 * @since Apr 28th 2014
 * v1.0
 */

package com.hryun.gtranslate;

public enum Language
{
    ENGLISH("en"), PORTUGUESE("pt"), SPANISH("es");

    private final String value;

    Language(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }
}