# drink-and-stretch
A Minecraft mod to remind you to drink water and stretch

![](./common/art/showcase/title.png)

Are you the type to forget to drink water or get up and stretch?
Do you want to be mildly healthier?

Drink and Stretch sends you periodic reminders to, well... Drink and Stretch! Inspired by the likes of bash
insults and sudo insults, it can also mildly insult you, if that's your thing!

![An example of a drink reminder saying "Hydration check", "Your kidneys will thank you"](./common/art/showcase/drink-toast.png)

![An example of a stretch reminder saying "Get up and move!", "It's healthy in small doses"](./common/art/showcase/stretch-toast.png)

By default, it will send you a reminder to drink water every 45 minutes, and to stretch every 2 hours.

The period is configurable, and it tries really hard to NOT bother you in combat, delaying the notification so you
can sip from your water bottle in peace. The timers run even when the game is paused, since you can't pause IRL
dehydration.

## Examples

![A drink reminder saying "Hydration check", "Your kidneys will thank you"](./common/art/showcase/drink-reminder.png)
![A stretch reminder saying "Get up and move!", "It's healthy in small doses"](./common/art/showcase/stretch-reminder.png)
![A insult drink reminder saying "Still no water?", "I've seen cacti more hydrated than you"](./common/art/showcase/drink-insult.png)
![The configuration screen](./common/art/showcase/config.png)


## Development

Use an IDE that supports .editorconfig, preferably Intellij. After that, it's standard, you probably want one of these:
```shell
./gradlew fabric:runClient
./gradlew neoforge:runClient
./gradlew forge:runClient
```

## Multi-Version

This mod uses Manifold to store code for multiple minecraft versions at once. All version specific properties must go
in [`versionProperties`](./versionProperties).

In the code, the version is exposed as a `MINECRAFT_VERSION` property with the version being mapped to an int, like so:
```
1.20.1  -> 1020001
1.21.1  -> 1021001
1.21.10 -> 1021010
```

Just install the Manifold plugin in your IDE and use it:
```java
#if MINECRAFT_VERSION >= 1021000
guiGraphics.blitSprite(resourceLocation, i, j, k, l, m, n, o, p);
#else
guiGraphics.blit(resourceLocation, m, n, k, l, o, p, i, j);
#endif
```

## Requesting a Version

Is the mod not available for the version of the game you play? Feel free to open an issue. I can't promise I will do it,
but the mod is quite simple, so there's a decent change it will happen, as long as it's a "modern" version.

## Contributing

Sure! Just open a Pull Request! But if it's a big change, please open an issue to discuss first.

Don't code, want to help translate? Open an issue, and I'll try my best to help out.
