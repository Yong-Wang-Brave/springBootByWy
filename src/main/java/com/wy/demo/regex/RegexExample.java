package com.wy.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        String input = "Hello, my name is John Doe. I am 25 years old.";
        String regex = "name is (\\w+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String group1 = matcher.group(1);
            System.out.println("Matched text: " + matcher.group());
            System.out.println("Group 1: " + group1);
        }
    }
}