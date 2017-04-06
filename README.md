# Binary-Search-on-Census-Names
//This is a binary search comparison between a naive iteration through the frequency-sorted last name, and and binary tree. Not very pretty, but the goal was to show the time-efficiency of the tree versus the space usage. Over 100 runs, the tree was about 150-170 times faster in looking up the name keyed to a random int. But seems to take up about 4.6 MB for 116k names. 

run 
/Binary-Search-on-Census-Names/RNG/bin/com/mitchell/niall/randomSurname/test/TestNameGenerator

example output
NameGenerator() constructor
	Free Memory: 63144936
	NameGenerator().load()
	Using Names_Census2010.csv
	Reading Names_Census2010.csv
	Closing Names_Census2010.csv
	NameGenerator().load() exits
	Free Memory after Load: 61180544
	List uses 1964392
NameGenerator contains 162254 entries
Loaded in 201453958 nanoseconds
	NameGenerator().verify()
	No low mismatches
	No high mismatches
	No ID mismatches
	NameGenerator().verify() exits
Verified
Verified in 36381589 nanoseconds
Tree() Constructor	Free Memory: 61180544
	Free Memory after Tree() constructor: 56503480
	Tree uses 4677064
Tree() finished in 29746888 nanoseconds
Tree() Constructor exitsNameGenerator() constructor exits
Found random entry in 45383 nanoseconds
	31119|SANSOUCIE|746|233366459|233367204|
Found random entry in 9465 nanoseconds
	80546|PHILLIPE|236|253360876|253361111|
Run 0	RandomIndex = 275958216
Found random entry in 1798 nanoseconds
Binary: 33908	iterative: 15703694	162253|ALL OTHER NAMES|29312001|265667228|294979228|	162253|ALL OTHER NAMES|29312001|265667228|294979228|
Run 1	RandomIndex = 157117063
Found random entry in 5855 nanoseconds
Binary: 32477	iterative: 93939	2759|LASSITER|13066|157111794|157124859|	2759|LASSITER|13066|157111794|157124859|
Run 2	RandomIndex = 174050907
Found random entry in 6688 nanoseconds
Binary: 32533	iterative: 98937	4432|SLEDGE|8019|174043812|174051830|	4432|SLEDGE|8019|174043812|174051830|
Run 3	RandomIndex = 55067497
Found random entry in 2917 nanoseconds
Binary: 30672	iterative: 2879	118|HAMILTON|201746|54975944|55177689|	118|HAMILTON|201746|54975944|55177689|
