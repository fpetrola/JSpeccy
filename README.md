# ZX3D

A ZX Spectrum emulator that converts old games to 3D

This emulator aims to convert 2d old zx spectrum games to 3d with no code modification involved.

## How it works
The idea behind ZX3D is to make an automatic sprites usage detection, and use this information to represent the same game in other environment or view.

To acomplish this it uses a modified z80 emulator written in Scala (converted from z80.java source) that can intercept arithmetic and logical operations over memory bits. Storing every memory access location associated to each cpu operation, when the pixel is displayed in screen memory all this data can be inspected to obtain the original bit location that is "creating" the pixel itself. 

## 3D representation
Using all the collected data and applying some algorithms sprites type and location can be determined for each game screen in real time. This sprite information is used by a jmonkeyengine application to create an equivalent 3d avatar object located in a 3D environment.
These 3D avatars can handle new constraints that are exclusive of this new environment such as z-index position, new movement axis, lights, weather effects, etc.

## Sound
A similar aproach could be used to make a modern representation of old games sounds and music


-----

# Development progress:

**First step**: Detecting sprites original address while they are shown.

[![First step](http://img.youtube.com/vi/6fVBvpyX8jI/0.jpg)](https://www.youtube.com/watch?v=6fVBvpyX8jI)

-----

**Second step**: Displaying sprites as boxes in 3d environment

[![Second step](http://img.youtube.com/vi/wugZ3E-shP0/0.jpg)](https://www.youtube.com/watch?v=wugZ3E-shP0)


