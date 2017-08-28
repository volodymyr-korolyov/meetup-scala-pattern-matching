package org.fun.prog;

public class ValueJava {

    public static void main(String[] args) {
        ValueJava obj = new ValueJava();

        System.out.println(obj.intToString(5));

        System.out.println(obj.anyToString(false));
        System.out.println(obj.anyToString(1));
        System.out.println(obj.anyToString("Many"));
    }

    public String intToString(int value) {
        // Number to string
        switch (value) {
            case 1: return "One";
            case 2: return "Two";
            default: return "Many";
        }
    }

    public String intToStringMulti(int value) {
        // Number to string
        switch (value) {
            case 1: return "One";
            case 2: return "Two";
            case 3:
            case 4:
            case 5: return "Many";
        }
        return null;
    }

    public String anyToString(Object value) {
        // Any to string
        if (value.equals(false)) {
            return "Zero";
        }
        if (value.equals(1)) {
            return "One";
        }
        if (value.equals("Many")) {
            return "Infinity";
        }
        return "";
    }

    public Integer anyToInt(Object value) {
        // Any to number
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof String) {
            String text = (String) value;
            return Integer.valueOf(text);
        }
        return null;
    }
}
