import java.io.*;
import java.util.*;
import java.nio.file.*;


public class PointSpace{
	
	private Point[] p;
	private double gridSize;
	
	//---------------------------------------------------------------------------------------------
	//constructors
	
	public PointSpace(int size, double gridSize) {
		this.p = new Point[size];
		this.gridSize = gridSize;
		randomPoints();
	}
	
	//---------------------------------------------------------------------------------------------
	//fills the point space with random points
	
	public void randomPoints() {
		for (int i = 0; i < this.p.length; i++) {
			this.p[i] = new Point(this.gridSize);
		}
	}
	
	//---------------------------------------------------------------------------------------------
	//getters
	
	public Point getPoint(int i) {
		if (i < 0 || i >= this.p.length) {
			System.out.println("Not a valid point");
			return new Point();
		}
		
		return this.p[i];
	}
	
	//---------------------------------------------------------------------------------------------
	//k means clustering algorithm
	
	public static Point[] kMeansCluster(Point[] q, int c, double s) {
		int numCentroids = c; //number of clusters
		double epsilon = 0.01; //centroid movement threshold
		double centroidMoveDistance = 0;
		
		//points you want clustered
		Point[] points = new Point[q.length]; 
		for (int i = 0; i < q.length; i++) {
			points[i] = q[i];
		}
		
		//centroids are an array of points
		Point[] centroids = new Point[numCentroids]; 
		//keeps in memory where the previous iteration of centroids were
		Point[] oldCentroids = new Point[numCentroids];
		
		//fills the centroid array with random starting points, and labels them from 1 to c
		for (int i = 0; i < centroids.length; i++) {
			centroids[i] = new Point(s);
			centroids[i].setLabel(i+1); 
			//System.out.println(centroids[i].toString());
		}
		
		//assigns all points a centroid
		for (int i = 0; i < q.length; i++) {
			double minDistance = q[i].dist(centroids[0]) + 1; //initialises the value of the min distance threshold
			for (int j = 0; j < numCentroids; j++) {
				if (q[i].dist(centroids[j]) < minDistance) { //if the distance between the jth centroid and the point is less than the min distance, the point gets relabelled
					minDistance = q[i].dist(centroids[j]); //updtaes the minimum distance
					q[i].setLabel(j+1); //gives the point its new cluster label
					//System.out.println(q[i].toString());
				}
			}
		}
		
		//fills the old centroid array with current centroids
		for (int i = 0; i < numCentroids; i++) {
			oldCentroids[i] = centroids[i];
		}
		
		//calculates the new centroid locations
		for (int i = 0; i < numCentroids; i++) {
			Point num = new Point(0,0,i+1); //calculates the numerator of the new centroid equation
			int denom = 0; //calculates the denominator of the mew centroid equation
			
			//calculates Num and Denom
			for(int j = 0; j < q.length; j++) {
				if (q[j].getLabel() == i+1) {
					num.addOn(q[j]); //adds to the numerator
					
					denom++; //iterates denom
					//System.out.println(denom + ","+ i);
				}
			}
			
			//System.out.println(num.toString());
			//System.out.println("denom = " + denom);
			
			//updates the centroids
			if (denom == 0) { //if no points were in that cluster, we make the centroid a different random point
				centroids[i] = new Point(s);
				centroids[i].setLabel(i+1);
				//System.out.println("crumbs,"+i);
			} else {
				double d = denom;
				double sc = 1 / d;
				//System.out.println("sc = " + sc);
				centroids[i] = num.scale(sc);
				
			}
			
		}
		
		//System.out.println(centroids[0].toString());
		
		//calculates the total distance that the centroids have moved
		for (int i = 0; i < numCentroids; i++) {
			centroidMoveDistance += centroids[i].dist(oldCentroids[i]);
		}
		//System.out.println(centroidMoveDistance);
		
		if (centroidMoveDistance < epsilon) {
			return q;
		}
		
	

		//repeats the above until the move distance is below the threshold
		while (centroidMoveDistance > epsilon){
			centroidMoveDistance = 0;
			
			//assigns all points a centroid
			for (int i = 0; i < q.length; i++) {
				double minDistance = q[i].dist(centroids[0]) + 1; //initialises the value of the min distance threshold
				for (int j = 0; j < numCentroids; j++) {
					if (q[i].dist(centroids[j]) < minDistance) { //if the distance between the jth centroid and the point is less than the min distance, the point gets relabelled
						minDistance = q[i].dist(centroids[j]); //updtaes the minimum distance
						q[i].setLabel(j+1); //gives the point its new cluster label
						//System.out.println(q[i].toString());
					}
				}
			}
		
			//fills the old centroid array with current centroids
			for (int i = 0; i < numCentroids; i++) {
				oldCentroids[i] = centroids[i];
			}
		
			//calculates the new centroid locations
			for (int i = 0; i < numCentroids; i++) {
			Point num = new Point(0,0,i+1); //calculates the numerator of the new centroid equation
			int denom = 0; //calculates the denominator of the mew centroid equation
			
			//calculates Num and Denom
			for(int j = 0; j < q.length; j++) {
				if (q[j].getLabel() == i+1) {
					num.addOn(q[j]); //adds to the numerator
					
					denom++; //iterates denom
					//System.out.println(denom + ","+ i);
				}
			}
			
			//System.out.println(num.toString());
			//System.out.println("denom = " + denom);
			
			//updates the centroids
			if (denom == 0) { //if no points were in that cluster, we make the centroid a different random point
				centroids[i] = new Point(s);
				centroids[i].setLabel(i+1);
				//System.out.println("crumbs,"+i);
			} else {
				double d = denom;
				double sc = 1 / d;
				//System.out.println("sc = " + sc);
				centroids[i] = num.scale(sc);
				
			}
			
		}
		
			//calculates the total distance that the centroids have moved
			for (int i = 0; i < numCentroids; i++) {
				centroidMoveDistance += centroids[i].dist(oldCentroids[i]);
			}
			//System.out.println(centroidMoveDistance);
	
		}
		
		
		
		for (int i = 0; i < numCentroids; i++) {
			System.out.println(centroids[i].toString());
		}
		
		return q;
	}
	
	
	//---------------------------------------------------------------------------------------------
	//DBSCAN clustering algorithm
	
	public static Point[] DBSCAN(Point[] q) {
		int noClusters = 0; //keeps track of the number of clusters
		boolean allClustered = false; //says if all of the points belong to a cluster or not
		double clusterRange = 1; //the range that points will be considered for a cluster
		int clusterNo = 1; //the label of the each cluster: 0 means unclustered, 1 means in a cluster of size < 3
		int howMany = 1; //how many new points you need in your radiuus to add them to your cluster
		
		
		do {
			int i = 0; //resets the starting point for the search to find next cluster start point
			Point[] currentCluster = new Point[q.length]; //creates a second point array whose leading entries are the points in the cluster
			
			do { //finds the starting point for the next cluster
				if (q[i].getLabel() == 0) { //if there is an untouched point, it becomes the start of the new cluster
					clusterNo++; //the cluster number then iterates for the next cluster
					q[i].setLabel(clusterNo); //this point gets labelled in the new cluster
					currentCluster[0] = q[i]; //the new point is then added to the current cluster
					//System.out.println("New cluster with label:" + clusterNo);
					break; //can leave the searching algorithm once a starting point is found
				}
				i++;
				if(i == q.length) 
					allClustered = true; //if there are no unclustered points, then the algorithm stops
			} while (!allClustered);
			
			int clusterSize = 0; //keeps track of the size of new clusters
			
			if (!allClustered) {
				boolean clusterFinished = false; //says whether this cluster is finished
			
				int j = 0; //j will be use to index the points around which we search for new cluster points
				int k = 1; //k will be used to index where the new cluster points will be added to current cluster
				
				do { //first sees if there are enough points in the cluster, then adds them if there are, and continues adding until there are fewer than 2 points to add
					int newPoints = 0;
					
					for (int l = 0; l < q.length; l++) { //finds whether there are enough points to add to the cluster
						if (q[l].getLabel() == 0 || q[l].getLabel() == 1) { //checks if each point is already in a cluster/can be reclustered
							if (currentCluster[j].dist(q[l]) < clusterRange) { //if the point is in the cluster range, it has potential to be added
								newPoints++;
								clusterSize++;
							}
						}
					}
				
					if (newPoints > howMany - 1) { //if there are enough new points to add, then they are added to the cluster
						for (int l = 0; l < q.length; l++) { //searches for the points that we can add using the same method as before
							if (q[l].getLabel() == 0 || q[l].getLabel() == 1) { 
								if (currentCluster[j].dist(q[l]) < clusterRange) { 
									q[l].setLabel(clusterNo); //gives the point the colour for its cluster
									//System.out.println(l);
									currentCluster[k] = q[l]; //adds it to the current cluster array
									k++; //iterates k so next point can be added to the next place
								}
							}
						}
						j++;
					} else { //if there are not enough points to add to the cluster, then the cluster is finished
						clusterFinished = true;
					}
					
					if (currentCluster[j].getLabel() == 0)
						clusterFinished = true;
					
				} while (!clusterFinished);
			
			}
			
			if (clusterSize < 5) { //allows points to be reclustered if they belong to a cluster with 4 or fewer points
				//System.out.println("Cluster too small");
				for (int l = 0; l < q.length; l++) {
					if (q[l].getLabel() == clusterNo) {
						q[l].setLabel(1);
					}
				}
				
			}
			
		} while (!allClustered);
		
		return q;
		
		
	}
	
	//---------------------------------------------------------------------------------------------
	//writes a text file with the points and their labels
	
	private static void writeFiles(Point[] q, String fileName, Point[] Q, String FILENAME) {
        
		String dataOne = "x-value	y-value	label \n";
		String dataTwo = "x-value	y-value	label \n";
		
		for (int i = 0; i < q.length; i++) {
			dataOne += q[i].getX();
			dataOne += "	";
			dataOne += q[i].getY();
			dataOne += "	";
			dataOne += q[i].getLabel();
			dataOne += "\n";
		}
		
		for (int i = 0; i < Q.length; i++) {
			dataTwo += Q[i].getX();
			dataTwo += "	";
			dataTwo += Q[i].getY();
			dataTwo += "	";
			dataTwo += Q[i].getLabel();
			dataTwo	+= "\n";
		}
		
		
		try {
            Files.write(Paths.get("*DIRECTORY NAME*" + fileName + ".txt"), dataOne.getBytes());
			Files.write(Paths.get("*DIRECTORY NAME*" + FILENAME + ".txt"), dataTwo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//---------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------
	
	//---------------------------------------------------------------------------------------------
	//main method
	
	public static void main(String[] args) {
		int numPoints = 100;
		double grid = 10;		
		
		Point[] P = new Point[numPoints];
		Point[] Q = new Point[numPoints];
		
		for (int i = 0; i < numPoints; i++) {
			P[i] = new Point(grid);
			Q[i] = new Point(grid);
		}
		
		Point[] p = kMeansCluster(P,5,grid);
		Point[] q = DBSCAN(Q);
		
		
		writeFiles(q, "DBSCAN", p,"kMeans");

		
		
		
		
	}
	
	
	
}


















