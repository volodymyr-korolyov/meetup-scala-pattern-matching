package org.fun.prog;

import static java.text.MessageFormat.format;

public class ExtractorCaseJava {

    public static void main(String[] args) {
        ExtractorCaseJava obj = new ExtractorCaseJava();

    }


    class Email {
        String from;
        String title;
        String message;
    }

    class Chat {
        String name;
        String message;
    }

    void processMessage(Object message) {
        if (message instanceof Email) {
            Email e = (Email) message;
            audit("email", e.from, e.title);
        } else if (message instanceof Chat) {
            Chat chat = (Chat) message;
            audit("chat", chat.name, "n/a");
        }
    }

    void processMessageWithCheck(Object message) {
        if (message instanceof Email) {
            Email e = (Email) message;
            if (isSpam(e.message)) {
                report(e.from);
            } else {
                audit("email", e.from, e.title);
            }
        }
    }

    void audit(String msgType, String sender, String title) {
        System.out.println(format("Audit: {0}, {1}, {2}", msgType, sender, title));
    }

    boolean isSpam(String text) {
        return text.contains("spam");
    }

    void report(String sender) {
        System.out.println(format("Received spam from {0}", sender));
    }

}
