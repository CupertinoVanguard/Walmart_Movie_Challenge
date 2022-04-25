# Walmart Movie Challenge

## Name: Nikhil Satheesh Pillai 

## Problem Statement

Given a movie theater layout, find the maximal placement of the various reservation that additionally works to prioritize each reservation's satisfaction and safety based on the guidelines provided in the handout. 
## Assumption

1) The text file is not empty
2) Every entry in the input text includes valid characters and only strings or characters shown in the example instructions.
3) The number per reservation can't exceed the size of the theather. If such happens, only partial assignment will occur. 
4) Those seated on the aisles will not be needing a three seat accomadation towards the aisles.
5) Seats in the back row and front row are valid seats since the one row space exemption is considered to not be valid for those particular rows.
6) The output file directory is coded into the main executable file. 

## Approach

The approach utilized here was the Greedy algorithm. While the greedy algorithm is not perfect, it works well in a short-term set constraint problem such as this one. There are two greedy approaches working together:
  1) The first greedy approach is one that tries to assign seats while simultaneously checking availability. Assuming availability is no longer a constraint, this algorithm works from the back row down to prioritize customer satisfaction, and compresses the available space as it pushes to the front row mimicking the lower priority of the later reservations. 
  2) The second aspect of the greedy algorithm is in the case of the splitting of groups when one continguous region may not be available for seating. The algorithm, as drawn up, prioritizes keeping the group as large as possible leading to space utilization of single seats scatter throughout the theater. This provides a potential downfall of the greedy implementation where this approach potentially worsens rather than alleviating the situation. 

## Instructions to Run

The following are the instructions to run:
1) Download the WalmartFinal file. 
2) Change directory into the classes folder
3) Type - java WalmartFinal <input_file_name>
4) The output file currently has to be changed in the WalmartFinal.java class due to certain write permission issues. 
