#!/usr/bin/python3

file1 = "stopwords1.txt"
file2 = "stopwords2.txt"
file3 = "stopwords3.txt"
output = "stopwords.txt"

stopwords = set()

with open(file1) as f:
	for line in f:
		stopwords.add(line[0:len(line)-1])

with open(file2) as f:
	for line in f:
		stopwords.add(line[0: len(line)-1])

with open(file3) as f:
	for line in f:
		stopwords.add(line[0: len(line)-1])

print("total stopwords: "+str(len(stopwords)))
writer = open(output, "w")
for sw in stopwords:
	writer.write(sw+"\n")
writer.close()

