package com.yichen.cosmos.cloud.platform.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 验证类名和字段名是否符合语法
 * Created by thomas on 2017/3/22 14:53.
 */
public class JavaValidateUtil {

    private final static Set<String> keywords;

    static {
        Set<String> s = new HashSet<String>();
        String[] kws = {
                "abstract", "continue", "for", "new", "switch",
                "assert", "default", "if", "package", "synchronized",
                "boolean", "do", "goto", "private", "this",
                "break", "double", "implements", "protected", "throw",
                "byte", "else", "import", "public", "throws",
                "case", "enum", "instanceof", "return", "transient",
                "catch", "extends", "int", "short", "try",
                "char", "final", "interface", "static", "void",
                "class", "finally", "long", "strictfp", "volatile",
                "const", "float", "native", "super", "while",
                // literals
                "null", "true", "false"
        };
        for (String kw : kws)
            s.add(kw);
        keywords = Collections.unmodifiableSet(s);
    }


    /**
     * Returns whether or not {@code s} is a keyword or literal in the
     * latest source version.
     *
     * @param s the string to check
     * @return {@code true} if {@code s} is a keyword or literal, {@code false} otherwise.
     */
    public static boolean isKeyword(CharSequence s) {
        String keywordOrLiteral = s.toString();
        return keywords.contains(keywordOrLiteral);
    }

    public static boolean isIdentifier(CharSequence name) {
        String id = name.toString();

        if (id.length() == 0) {
            return false;
        }
        int cp = id.codePointAt(0);
        if (!Character.isJavaIdentifierStart(cp)) {
            return false;
        }
        for (int i = Character.charCount(cp);
             i < id.length();
             i += Character.charCount(cp)) {
            cp = id.codePointAt(i);
            if (!Character.isJavaIdentifierPart(cp)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 验证类名和字段名是否符合语法
     *
     * @param name
     * @return
     */
    public static boolean isName(CharSequence name) {
        String id = name.toString();

        for (String s : id.split("\\.", -1)) {
            if (!isIdentifier(s) || isKeyword(s))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        String key = "Long";
        System.out.println(isKeyword(key.toLowerCase()));
    }
}
