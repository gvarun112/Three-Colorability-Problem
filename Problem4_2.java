package grandfinale;
import java.util.Random;
import java.util.Scanner;
public class Problem4_2
{    
    private int V, numOfColors;
    private int[] color; 
    private boolean[][] c;
    static Boolean flag = false;
    static int count = 0;
    public void graphColor(boolean[][] g, int noc) 
    {
        V = g.length;
        numOfColors = noc;
        color = new int[V];
        c = g;
        for ( int i = 0; i < numOfColors ; i++) { 
            color[i] = 0;
        }
        solution(0);
    }
    public void solution(int v) {
        loop:
        if (v == V) { //all vertices have been colored
            count++;
            System.out.println("Solution "+count+":");
            System.out.println("Colors : ");
            for (int i = 0; i < V; i++)
                System.out.print(color[i] +" "); //print all the solutions possible
            System.out.println();
            flag = true;
        }
        else {
            for (int c = 1 ; c <= numOfColors ; c++)
            {
                int a = Query(v, c); //check if the color c can be applied to vertix v
                if (a==1) {
                    color[v] = c; //color the node with color c
                    solution(v + 1); //recursive call with the next vertex
                    flag = true;
                }
                else if ( a == 0 ) {
                    break loop;
                }
            }
        }
        if(flag==false)
            System.out.println("Solution doesn't exist");
    }
    public int Query(int v, int x)
    {
        for (int i = 0; i < V; i++)
            if (c[v][i] == true && x == color[i] && v!=i) //if color c was already applied to a vertex connected to v
                return 0;
        return 1; //if color c can be applied to vertex v
    }
    public static void main (String[] args) 
    {   
        Problem4_2 GraphColouring = new Problem4_2(); //create an object
        long initialTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        Random gen = new Random();
        System.out.println("Enter number of vertices:");
        int numberofVertices = sc.nextInt();
        boolean[][] graph = new boolean[numberofVertices][numberofVertices];
        for (int i = 0; i < numberofVertices; i++)
            for (int j = 0; j < numberofVertices; j++)
                graph[i][j] = graph[j][i] = gen.nextBoolean(); //if i is connected to j, value is true .. else false
        for(int i=0 ; i<numberofVertices; i++)
            graph[i][i] = true; //since all vertices are connected to themselves
        for (int i = 0; i < numberofVertices; i++) {
            for (int j = 0; j < numberofVertices; j++) {
                System.out.print(" "+graph[i][j]);
            }
            System.out.println();
        }
        System.out.print("Enter number of colors:");
        int c = sc.nextInt();
        GraphColouring.graphColor(graph, c);
        long finalTime = System.currentTimeMillis();
        long timeTaken = finalTime - initialTime;
        System.out.println("\nTime taken = " + timeTaken);
    }
}