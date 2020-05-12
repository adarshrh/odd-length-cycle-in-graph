/**
 * IDSA Short Project 6
 * Team members:
 * Adarsh Raghupati   axh190002
 * Keerti Keerti      kxk190012
 */

####Implementation of a BFS based algorithm to output an odd-length cycle of a graph.

###### Steps to run the code in IntelliJ IDE
* Create an empty java project
* Unzip the source code files and paste it under the location "Java Project Name"/src folder


###### Steps to run the tests
Run the OddLengthCycle.java file. Input can be either by command line input file or by the string variable in the java file.

###### Sample test run
Test run 1:
For input: 7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 -7   6 7 -1   7 6 -1 1
Output:
Graph is bipartite. There is no odd length cycle in the graph

Test run 2:
For input: 8 9   1 3 3   2 4 5   3 4 4   4 5 1   6 7 -1   7 6 -1   8 7 1   8 6 1   1 2 2 1
Output:
Vertices included in odd length cycle are:
8,6,7