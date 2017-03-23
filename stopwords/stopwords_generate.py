#!/usr/bin/python3

file1 = "stopwords_part1.txt"
file2 = "stopwords_part2.txt"
file3 = "stopwords_part3.txt"
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
lista = []
for sw in stopwords:
	if sw!="":
		lista.append(sw)

lista.sort()
for w in lista:
	writer.write(w+"\n")
writer.close()

