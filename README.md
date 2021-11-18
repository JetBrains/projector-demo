# projector-demo
[![JetBrains incubator project](https://jb.gg/badges/incubator.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![Builds status badge](https://github.com/JetBrains/projector-server/workflows/Builds/badge.svg)](https://github.com/JetBrains/projector-demo/actions)

A simple sample application demonstrating running Swing applications remotely.

[Documentation](https://jetbrains.github.io/projector-client/mkdocs/latest/) | [Issue tracker](https://youtrack.jetbrains.com/issues/PRJ)

## Building and running

We use Gradle. So you can run Gradle tasks as follows:

```console
# example of running `build` task:
./gradlew build
```

## App
There is `OriginalMain` class, which is an example of a custom Swing application. Also, there is `HeadlessSupportingMain` class, which demonstrates lines added to run the demo app in the headless mode.

## How to run projector-demo
Here are several ways to run this demo app. Using them, you can understand how to run your own app.

Run configuration <br> Gradle task | Main class | VM option
:---:|---|---
(UI) OriginalMain <br> `runUiOriginalMain` | `org.jetbrains.projector.demo.OriginalMain` | Blank
(UI) HeadlessSupportingMain <br> `runUiHeadlessSupportingMain` | `org.jetbrains.projector.demo.HeadlessSupportingMain` | Blank
(Headless) HeadlessSupportingMain <br> `runHeadlessHeadlessSupportingMain` | `org.jetbrains.projector.demo.HeadlessSupportingMain` | `-Dorg.jetbrains.projector.server.enable=true`
(Headless) ProjectorLauncher <br> `runHeadlessProjectorLauncher` | `org.jetbrains.projector.server.ProjectorLauncher` | `-Dorg.jetbrains.projector.server.classToLaunch=org.jetbrains.projector.demo.OriginalMain`

To access headlessly run app, you can use [Projector web client](https://github.com/JetBrains/projector-client/tree/master/projector-client-web). It will be available on <http://localhost:8887>. Or you can use any other way of connection, feel free to check the [corresponding documentation page](https://jetbrains.github.io/projector-client/mkdocs/latest/ij_user_guide/accessing/).

## Artifacts
There are two run configurations. Jars will be built in the `build/libs` dir.

### Build HeadlessSupporting jar
Gradle task: `jarHeadlessSupport`.

Its main class is `HeadlessSupportingMain` and it contains the `projector-server` module. To run headlessly, add a JVM option `-Dorg.jetbrains.projector.server.enable=true`.

### Build Original jar
Gradle task: `jarOriginal`.

Its main class is `OriginalMain`.

To launch the original version with UI, run:
```Shell Script
java \
-classpath projector-demo-original.jar \
org.jetbrains.projector.demo.OriginalMain
```

To launch the original version headlessly, place the classpath of `projector-server` into `libs` dir and run:
```Shell Script
java \
-classpath projector-demo-original.jar:libs/* \
-Dorg.jetbrains.projector.server.classToLaunch=org.jetbrains.projector.demo.OriginalMain \
org.jetbrains.projector.server.ProjectorLauncher
```

## License
[GPLv2](LICENSE.txt).
