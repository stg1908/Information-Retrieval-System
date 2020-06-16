# Information-Retrieval-System
Same as any IR system. This project's objective is to retrieve documents which are relevant to the query asked by the user.
In this project, I have used Tf-idf method to calculate the importance of every document according to every query.
## Instruction to run the Project
First, open the directory in terminal
then compile the Project.java file by running the command:
```
javac Project.java
```
then run the class file by:
```
java Project
```
## Output of Project


- output.txt -  This file will stores scores of every documents for every query.
- topscore.txt - This file will stores top 10 document's scores for every query.
- eval1.txt - This file stores Precision for very Query.
## Information about every file


- Project.java - main file contain the methods to calculate term frequency and Document frequency
		and also contain methods to read Docs and Query.
- Dis.java - This file is responsible for output.txt and eval1.txt. This class is used to write calculated score in file.  
- Eval.java - This file is responsible for eval1.txt. This class is used to evaluate the results.
- Binv.java - This files has information about top 6 documents being relevant or not. 
- dcheck - This folder contains collection of documents
- qcheck - This folder contains collection of query


## Note

Binv.java contains random data to check whether the evaluation programme is working correctly or not.  
-----STG
 
