import java.util.Scanner;

public class Application {
    private final int width;
    private final int height;
    private final Scanner scanner;
    private PlayGround playGround;
    public Application() {
        width = 9;
        height = 9;
        scanner = new Scanner(System.in);
    }

    public void setPlayGround(){
        System.out.print("How many mines do you want on the field? ");
        int mines = scanner.nextInt();

        playGround = new PlayGround(width, height, mines);
        playGround.setPlayGround();
        playGround.printPlayGround();


    }

    public void playGame(){
        System.out.println("Set/delete mines marks (x and y coordinates): ");
        int x = scanner.nextInt()-1;
        int y = scanner.nextInt()-1;

        if(x < 0 || x > width || y < 0 || y > height){
            System.out.println("Invalid coordinates");
            playGame();
        }
        else{
            if(playGround.getGrid()[y][x] == '.' || !playGround.getVisibility()[y][x]){
                playGround.getGrid()[y][x] = '*';
                playGround.getVisibility()[y][x] = true;
                if(checkWin()){
                    return;
                }
                playGround.printPlayGround();
                playGame();
            }
            else if(playGround.getGrid()[y][x] == '*'){
                playGround.getGrid()[y][x] = '.';
                playGround.getVisibility()[y][x] = false;
                playGround.printPlayGround();
                playGame();
            }else{
                System.out.println("There is a number here!");
                playGame();
            }
        }
    }

    public boolean checkWin(){
        int count = 0;
        for(int i = 0; i < playGround.getMinesPositions().size(); i++){
            Pair<Integer> pair = playGround.getMinesPositions().get(i);
            if(playGround.getGrid()[pair.getFirst()][pair.getSecond()] == '*'){
                count++;
            }
        }
        if(count == playGround.getMinesPositions().size()){
            System.out.println("Congratulations! You found all the mines!");
            return true;
        }else{
            return false;
        }
    }

    public void startGame(){
        setPlayGround();
        playGame();
    }
}
