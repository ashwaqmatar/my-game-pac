# my-game-pac
This program is an educational program.

It is broken down into several stages:

The 3D points, which will then be defined as GPS or as Cartesian.

The program is to make the conversion between cartesian is polar, as well as to make any kind of calculations between vectors and points GPS, to find the vector between two points GPS, to find the new point GPS after this displacer of a GPS points with a vectors ... The program is also read a csv file or a folder containing csv files and convert it to kml file.

Let's go to Package Gis To make it short:

Gis-element define a GPS point on the map
Gis-Layer define a Csv file (several Gis-element)
Gis-Project defined a set of Gis-layers
We also have a Meta-data that is common to the three classes is defined the data.

All this to arrive at a very interactive Gui Graphical interface program. The goal is very simple, we receive a satellite photo of a place with GPS coordinates of the edges of the photo. Then we add Pacmans and Fruits, and like the famous games, our pacman has to eat Fruits. Only that according to our version, the pacman know are way thanks to Algorithms start in the background which will define the trajectory of each Pacman (if there are several, each pacman will have his way to have the best time of treatment)

Then we have added some useful accessories:

Read and write the Paths on a Csv file
Write the Paths on a Kml file (on which we can also see the Pacman move on their target)
Possibility to change the speed and Raduis of Pacman
Possibility to change the weight of each Fruit
The tests of the function were carried out by Junit, all the functions have their verifications which are validated. The tests are located in the Tests Package.


Built With
Eclipse - is an integrated development environment (IDE) used in computer programming, and is the most widely used Java IDE

Google Earth is a computer program that renders a 3D representation of Earth based on satellite imagery.

The project includes class diagrams

Getting Started
There is a test object that contains demonstrations of all functions to illustrate how things work. In addition, it is recommended to go over the javadoc before and read on all functions.

Tutorial
To start the program: Import the Project then OOP_EX2-EX4-master -->OP_EX2-Ex4-->src-->GUI-->MyFrame.java

Then click in the menu on Add The user then enters the Pacmans and Fruits To start the game click in the menu on File-> Run

Acknowledgments
Wikipedia to understand more how to CSV File : https://fr.wikipedia.org/wiki/Comma-separated_values

Wikipedia to understand more how to Kml File : https://fr.wikipedia.org/wiki/Keyhole_Markup_Language

Wikipedia to understand more how to Polar Coordinate : https://en.wikipedia.org/wiki/Polar_coordinate_system

Wikipedia to understand more how to Pacman Game: https://fr.wikipedia.org/wiki/Pac-Man_(s%C3%A9rie)

Ntu Edu to understand more how to Gui Java: http://www.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html

Google Developer to understand more how to Time and Animation of Kml : https://developers.google.com/kml/documentation/time

Description
Package:
Algorithm

My Classes: ShortestPathAlgo

Coords:

Include 1 interface such as: coords_converter

My Classes: MyCoords,Map,test

File_format :

My Classes : CsvFileHelper , CsvFileHelper , Json_101  , csv2kml

GIS :

Include 4 interface such as: GIS_element , GIS_layer , GIS_project , Meta_data

My Classes: GIS_Layer_using , GIS_projet_using , Gis_element_using , Meta_Data_using

Game,Fruit.,Packman,Path.

GUI

My Classes: MyFarme

GEOM :

Include 1 interface such as: Geom_element

My Class: Point3D , Circle
