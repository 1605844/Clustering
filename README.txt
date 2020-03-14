Code, plotting tools and diagrams used to create Figures 4.1,4.2 and 4.3.

**DBSCAN ALTERATION**
I found when testing the DBSCAN algorithm, that on small or sparse datasets, that a lot of small clusters (clusters of size less than four)
  were created. I therefore decided to alter the algorithm, by re-classifying points in small clusters as pseudo-clustered (seen in the
  code with a label of 1), allowing them to be added to other clusters if they were in the cluster radius in the algorithm.
This returned much fewer small clusters in the trials that I carried out, and hence was used in the final report to demonstrate DBSCAN in
  practice.

Java Classes:
- Point: Standard java point object, with added label feature
- PointSpace: Creates a space of points for clustering algorithms to be used on.
              Contains both clustering algorithms: k-means and DBSCAN
              
Matlab Code:
- DBSCAN: The plotting code for Figure 4.3
- kMeans: The plotting code for Figure 4.1
              
