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
        System.out.println("Set/unset mines marks (x and y coordinates) or claim a cell as free: ");
        int x = scanner.nextInt()-1;
        int y = scanner.nextInt()-1;
        String action = scanner.next();

        if(x < 0 || x > width || y < 0 || y > height){
            System.out.println("Invalid coordinates");
            playGame();
        }
        else{
            if(action.equals("mine")){
                if(playGround.getMarkedPositions().contains(new Pair<>(y, x))){
                    playGround.getMarkedPositions().remove(new Pair<>(y, x));
                    playGround.getVisibility()[y][x] = false;
                    playGround.printPlayGround();
                    playGame();
                }else if(!playGround.getVisibility()[y][x]) {
                    playGround.getMarkedPositions().add(new Pair<>(y, x));
                    playGround.printPlayGround();
                    if(checkWinByMarking()){
                        return;
                    }
                    playGame();
                }
            }else if(action.equals("free")){
                if(playGround.getGrid()[y][x] == 'X'){
                    playGround.getVisibility()[y][x] = true;
                    playGround.printPlayGround();
                    System.out.println("You stepped on a mine and failed!");
                    return;
                }else if(playGround.getGrid()[y][x] == '/'){
                    playGround.getVisibility()[y][x] = true;
                    playGround.adjustVisibility(x, y);
                    playGround.printPlayGround();
                    if(checkWinByFree()){
                        System.out.println("Congratulations! You found all the mines!");
                        return;
                    }
                    playGame();
                }else if(playGround.getGrid()[y][x] == '*'){
                    System.out.println("You stepped on a mine and failed!");
                    return;
                }else{
                    playGround.getVisibility()[y][x] = true;
                    playGround.printPlayGround();
                    if(checkWinByFree()){
                        System.out.println("Congratulations! You found all the mines!");
                        return;
                    }
                    playGame();
                }
            }
        }
    }

    public boolean checkWinByMarking(){
        int count = 0;
        for(int i = 0; i < playGround.getMinesPositions().size(); i++){
            Pair<Integer> pair = playGround.getMinesPositions().get(i);
            if(playGround.getMarkedPositions().contains(pair)){
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

    public boolean checkWinByFree(){
        for(int i = 0; i < playGround.getVisibility().length; i++){
            for(int j = 0; j < playGround.getVisibility()[i].length; j++){
                if(!playGround.getVisibility()[i][j] && playGround.getMinesPositions().contains(new Pair<>(i, j))){
                    return false;
                }
            }
        }
        return true;
    }

    public void startGame(){
        setPlayGround();
        playGame();
    }
}
