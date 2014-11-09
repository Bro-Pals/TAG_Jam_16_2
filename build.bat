javac -d class src/bropals/tag16_2/*.java src/bropals/creature/tag16_2/*.java
PAUSE
jar cfm IroncladGame.jar MANIFEST.mf -C class .