import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PlayGround {
    private final int width;
    private final int height;
    private final int mines;
    private char[][] grid;
    private boolean [][] visibility;
    private List<Pair<Integer>> minesPositions;
    Random random = new Random();
    public PlayGround(int width, int height, int mines) {
        this.width = width;
        this.height = height;
        this.mines = mines;
        this.grid = new char[height][width];
        this.visibility = new boolean[height][width];
        this.minesPositions = new ArrayList<>();
    }

    public void setPlayGround(){
       for(int i = 0; i <height; i++){
           for(int j = 0; j < width; j++){
               grid[i][j] = '.';
           }
       }
       List<Integer> positions = new ArrayList<>();
         for(int i = 0; i < height * width; i++){
              positions.add(i);
         }

        Collections.shuffle(positions);

        for(int i = 0; i < mines; i++) {
            int position = positions.get(i);
            int row = position / width;
            int col = position % width;
            grid[row][col] = 'X';
            minesPositions.add(new Pair<>(row, col));
        }
        setNumbers();
        setVisibility();
    }

    public void printPlayGround() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");
        for (int i = 0; i < height; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < width; j++) {
                if (visibility[i][j]) {
                    System.out.print(grid[i][j]);
                } else {
                    System.out.print('.');
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("—│—————————│");
    }

    public void setVisibility(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(grid[i][j] != 'X'){
                    visibility[i][j] = true;
                }else{
                    visibility[i][j] = false;
                }
            }
        }
    }

    public void setNumbers(){
        for(int i = 0; i < height; i ++){
            for(int j = 0; j < width; j++){
                if(grid[i][j] == '.'){
                    checkCorners(i, j);
                    if((i != 0 || j != 0) && (i != 0 || j != width-1) && (i != height-1 || j != 0) && (i != height-1 || j != width-1)){
                        for(int k = i-1; k <= i+1; k++){
                            for(int l = j-1; l <= j+1; l++){
                                try {
                                    if (grid[k][l] == 'X') {
                                            if (grid[i][j] == '.') {
                                                grid[i][j] = '1';
                                            } else {
                                                grid[i][j]++;
                                        }
                                    }
                                }catch(ArrayIndexOutOfBoundsException e){
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    private void checkCorners(int i, int j) {
        if(i == 0 && j == 0){
            if(grid[i +1][j] == 'X'){
                grid[i][j] = '1';
            }
            if(grid[i][j +1] == 'X'){
                if(grid[i][j] == '1'){
                    grid[i][j]++;
                }else{
                    grid[i][j] = '1';
                }
            }
            if(grid[i +1][j +1] == 'X'){
                if(grid[i][j] == '.'){
                    grid[i][j] = '1';
                }else{
                    grid[i][j]++;
                }
            }
        }

        if(i == 0 && j == width-1){
            if(grid[i][j-1] == 'X'){
                grid[i][j] = '1';
            }
            if(grid[i+1][j] == 'X'){
                if(grid[i][j] == '1'){
                    grid[i][j]++;
                }else{
                    grid[i][j] = '1';
                }
            }
            if(grid[i +1][j-1] == 'X'){
                if(grid[i][j] == '.'){
                    grid[i][j] = '1';
                }else{
                    grid[i][j]++;
                }
            }
        }

        if(i == height-1 && j == 0){
            if(grid[i-1][j] == 'X'){
                grid[i][j] = '1';
            }
            if(grid[i][j+1] == 'X'){
                if(grid[i][j] == '1'){
                    grid[i][j]++;
                }else{
                    grid[i][j] = '1';
                }
            }
            if(grid[i-1][j+1] == 'X'){
                if(grid[i][j] == '.'){
                    grid[i][j] = '1';
                }else{
                    grid[i][j]++;
                }
            }
        }

        if(i == height-1 && j == width-1){
            if(grid[i-1][j] == 'X'){
                grid[i][j] = '1';
            }
            if(grid[i][j-1] == 'X'){
                if(grid[i][j] == '1'){
                    grid[i][j]++;
                }else{
                    grid[i][j] = '1';
                }
            }
            if(grid[i-1][j-1] == 'X'){
                if(grid[i][j] == '.'){
                    grid[i][j] = '1';
                }else {
                    grid[i][j]++;
                }
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public boolean[][] getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean[][] visibility) {
        this.visibility = visibility;
    }

    public List<Pair<Integer>> getMinesPositions() {
        return minesPositions;
    }
}
