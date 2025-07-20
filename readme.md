## Gunging Ootilities' Suite

(Insert maven and gradle dependency and repo blocks here)

A collection of utilities for minecraft scripting, focused on inventory management which is my specialty. At the very least these carry my promise, that they will never break. My design prioritizes low-to-no maintenance and unmatched backward compatibility.

The GooSuite is, at the moment, being implemented into my GooPlugin, the plugin release of it to use in spigot and paper spigot minecraft java servers. However, the aim of this complicated modular maven project is that it can be implemented into the GooMod too.

### The levels of GooSuite

1. GooBase, where the real calculations and operations of take place. GooBase depends on no minecraft or third party libraries, which allow it to work across all platforms and versions when paired with the higher levels of GooS.
2. GooFoundation, platform-dependent wrappers for GooBase to do what it needs to do. Essentially, the minecraft platform and version.
3. GooCompatibility, optional third-party plugin additions and extended functionality. Plugins or other mods for that minecraft version.
4. GooPlugin, which only job is to build scan the environment of the java server it is loaded in to put the lower levels together. Essentially, the GooP is simply a shell that gathers the applicable GooCs and GooFs and loads them into GooB.
5. GooMod, same thing as GooP but gathers the forge version of the GooCs and GooFs to load them into GooB.