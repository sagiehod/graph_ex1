


Them main goal : develop undirected weighted graph 

general description : 
in order to build undirected weighted graph I use algorthim to create nodes 
that connected by edge, between two eadge connected edges we calculate unique whight
In addition, I calculateed by dijkstra algo the shortest distanc betwenn two node


WGraph_DS


In this class, the algo use node_info - it builds node that has uniqe key , this class use getter and setter methods  
those methods set the values and get the node value, for example I check if the we visited the node the algo use setTag.
I use two diffrerntof data stucture hash map by name getNi() - holds keys, neighborss's values ,and algo akso use getwi() that holds the wight between to nodes


WGraph_DS class 
this class builds the graph itself., the class gets the key value and put it in the node and connected the two nodes ,In addation
,getEdage function add to eatch weight to two nodes
I used  in this algo, data stucture to add, remove and to check if edge is exsites in efficency O{1} algo




WGraph_Algo
this class use the graph we created in class WGraph_DS
this intilazed, copy , checks if the graph is connected by useing BFS algo.
BFS- (Is an algorithm used to go over graph nodes, usually while searching for a node that maintains a particular feature.
in addtion, in this class we check the path and the shortest distance by name functions : shortessPath , shortestDis
to use those two functions we use dijkstra algo 


dijkstra-Solves the problem of finding the easiest route from a point on the graph to a destination in a weighted graph. 
Because using this algorithm you can find, at the same time, the fast paths to all the points in the graph  


last thing, in this class we use load and save to save and load the file(graph)





=



