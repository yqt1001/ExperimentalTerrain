# ExperimentalTerrain
Ideally, this should be a pretty awesome terrain generator when I'm done. We'll see how it goes!
Popular demand dictated that I release somewhat regular videos during it's development, so this repository is split into branches showing progress based on episodes.
Could be compatible with all versions of Spigot following MC 1.2, depending on the blocks used. I don't see myself using any 1.9+ blocks.

## Installation
Uses Maven for dependency management. Clone the branch `git clone -b ep2 --single-branch https://github.com/yqt1001/ExperimentalTerrain.git` and package it into a usable jar with `mvn package`.

## Episode 2
In this episode some more code was written, however, the majority of the episode consisted of me explaining the basics of terrain generation. 
First, we created a very simple terrain generator that only consisted of one noise generator. While this was functional, it produced a bland, repetitive result. 
Secondly, we made a terrain generator with 3 overlapping noise generators with different scales. One generator for overall elevation (somewhat representing biomes), another generator for terrain roughness (allowing for areas of plains and hills), and lastly a generator for small regional details.