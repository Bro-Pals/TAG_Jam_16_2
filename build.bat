javac -d class src/bropals/tag16_2/*.java src/bropals/tag16_2/creature/*.java
PAUSE
jar cfm IroncladGame.jar MANIFEST.mf -C class .