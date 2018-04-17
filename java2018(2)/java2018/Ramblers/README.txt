README for java2018/Ramblers

This is the code for reading and writing TerrainMaps from/to files.
For use in the Ramblers problem assignment
See the assignment spec for an explanation of the TerrainMap format (.pgm)

To display a path on a TerrainMap you have loaded, use TerrainMap.showPath(path)
path is an ArrayList of Coords (class defined in this directory)
every pixel on the path will be set to 255 (the max, plotted as white).
**this is destructive**
then write to file with writeTMap and display with Irfanview.

pdg

