# Installing Java

If you don’t already have a Java&trade; Platform, Standard Edition Development Kit (JDK), you need it. The book assumes you're using Java 11 (apart from Appendix B, which requires Java 17). If you happen to be using Java 8, most of the examples will still work. If you don't know which version of Java to use, we recommend using Java 17, the current "long term support" release.

There are free builds of [OpenJDK](https://openjdk.java.net/) (the reference implementation of Java) available from a number of vendors, [summarised here](https://medium.com/@javachampions/java-is-still-free-3-0-0-ocrt-2021-bca75c88d23b#04b1). To simplify things we have picked two:
 - [Eclipse Adoptium](https://adoptium.net/): Free community-provided OpenJDK builds. You can download and install Java 8, 11 or 17, and whatever the latest version of Java happens to be, for your operating system.
 - [Oracle](https://www.oracle.com/java/technologies/downloads/): Many developers default to downloading a JDK from Oracle, but be aware that Oracle has more than one license for using Java, including a commercial license that you need to pay for. If you [download Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows) from the Oracle site, this is "free to use in production and free to redistribute, at no cost, under the [Oracle No-Fee Terms and Conditions](https://www.oracle.com/downloads/licenses/no-fee-license.html)."

(If you want more information about which versions of Java are free, and what "free" means, see [Java is Still Free (3.0.0)](https://medium.com/@javachampions/java-is-still-free-3-0-0-ocrt-2021-bca75c88d23b) by the [Java Champions](https://dev.java/community/jcs/).) 


The JDK includes everything you need to compile and run Java. The JDK does not include the API documentation, and you need that! Go and get the [Java SE API documentation](https://www.oracle.com/java/technologies/javase-jdk17-doc-downloads.html). You can also access the [API docs online](https://docs.oracle.com/en/java/javase/17/) without downloading them, but trust us, it’s worth the download.

You need a text editor. Virtually any text editor will do (vi, emacs), including the GUI ones that come with most operating systems. Notepad, Wordpad, TextEdit, etc. all work, as long as you're using plain text (not rich text) and make sure they don't append a ".txt" on to the end of your source code (".java") file.

Once you’ve downloaded and unpacked/installed/whatever (depends on which version and for which OS), you need to add an entry to your PATH environment variable that points to the /bin directory inside the main Java directory. The bin directory is the one you need a PATH to, so that when you type:
```
% javac
```
at the command-line, your terminal will know how to find the javac compiler.

Note: if you have trouble with your installation, we recommend you go to [javaranch.com](https://javaranch.com/), and join the Java-Beginning forum! Actually, you should do that whether you have trouble or not.



### A note on version numbers
The version numbers have varied a lot over the last 25+ years! Obviously we started with Java 1.0, and then came 1.1, 1.2, 1.3 and 1.4. For stunningly unclear marketing reasons, shortly before release, Sun renamed Java 1.5 to Java 5, even though technically it's still only Java 1.5. Same for 6 (1.6), 7 (1.7) and 8 (1.8).

In theory, Java is still on version 1.x because new versions are backwards compatible, all the way back to 1.0. However, it was a bit confusing having a version number that was different to the name everyone used, so the official version number from Java 9 onwards is just the number, without the "1" prefix - i.e. Java 9 really is version 9, not version 1.9. 