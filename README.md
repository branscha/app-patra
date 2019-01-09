# Password Tracker
## History
Password tracker is an application to manage your passwords. It was originally developed on Sourceforge, and I reconstructed the build in GitHub.

It is compatible with the original Password Safe from Bruce Schneier it supports versions 1 and 2 of the original application.

## Build Instructions

```
mvn clean install assembly:single
```

## Packaging Instructions

Using JDK 11 or higher you can generate an application bundle using jpackager.

First look up the module dependencies:

```
mvn clean install assembly:single
```

Install the jpackager, you can use a backport. https://mail.openjdk.java.net/pipermail/openjfx-dev/2018-September/022500.html

Finally run the packager.

```
jpackager create-image \
   --class com.sdi.pws.gui.Pws \
   --input ./target \
   --files patra-application.jar \
   --main-jar patra-application.jar \
   --output ./target \
   --identifier com.sdi.pws \
   --name Patra \
   --add-modules java.base,java.datatransfer,java.desktop,java.logging,java.prefs,java.rmi,java.sql,java.xml \
   --module-path $JAVA_HOME/jmods \
   --singleton --strip-native-commands
```