package battleship;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] allCordsP1 = new int[5][12];
        String skipString;
        int[][] shotenCordsP1 = new int[5][12];
        int[][] allCordsP2 = new int[5][12];
        int[][] shotenCordsP2 = new int[5][12];
        int countShipsP1 = 17;
        int countShipsP2 = 17;
        int[][] MapP1 = new int[12][12];
        int[][] MapP2 = new int [12][12];


        for(int i = 1;i < 11;i++) {
            for (int j = 1; j < 11; j++) {
                MapP1[i][j] = 0;
            }
        }
        for(int i = 1;i < 11;i++) {
            for (int j = 1; j < 11; j++) {
                MapP2[i][j] = 0;
            }
        }

        System.out.println("Player 1, place your ships on the game field");
        fillMap(MapP1,allCordsP1);
        printField(MapP1);
        System.out.println();
        System.out.println("Press Enter and pass the move to another player\n" +
                "...");
        skipString = scanner.nextLine();

        System.out.println("Player 2, place your ships on the game field");
        fillMap(MapP2,allCordsP2);
        printField(MapP2);
        System.out.println();
        System.out.println("Press Enter and pass the move to another player\n" +
                "...");
        skipString = scanner.nextLine();


        System.out.println("The game starts!");
        System.out.println();
        while (countShipsP1 != 0 && countShipsP2 != 0){
            countShipsP1 = 0;
            countShipsP2 = 0;
            for(int i = 1;i < 11;i++) {
                for (int j = 1; j < 11; j++) {
                    if(MapP1[i][j] == 1){
                        countShipsP1++;
                    }
                    if(MapP2[i][j] == 1){
                        countShipsP2++;
                    }
                }
            }


            printBtattleField(MapP2);
            System.out.println();
            System.out.println("---------------------");
            printField(MapP1);
            System.out.println();

            System.out.println("Player 1, it's your turn:");
            fight(MapP2,allCordsP2,shotenCordsP2,MapP1);
            System.out.println();

            System.out.println("Press Enter and pass the move to another player\n" +
                    "...");
            skipString = scanner.nextLine();

            printBtattleField(MapP1);
            System.out.println();
            System.out.println("---------------------");
            printField(MapP2);
            System.out.println();

            System.out.println("Player 2, it's your turn:");
            fight(MapP1,allCordsP1,shotenCordsP1,MapP2);
            System.out.println();



            System.out.println("Press Enter and pass the move to another player\n" +
                    "...");
            skipString = scanner.nextLine();


        }




    }
    public static void fillMap(int[][] x,int[][] allCords){
        String[] names = {"Aircraft Carrier ","Battleship ","Submarine ","Cruiser ","Destroyer ",""};
        int[] shipsL = {5,4,3,3,2,0};
        int skip  = 0;
        int k = 0;

        boolean isDone = true;
        int countShips = 0;
        int ii=  0;
        int[] cord = new int[4];

       while (countShips != 17){
            ii = 0;

            if(countShips == 17){
                break;
            }
            countShips = 0;
            for(int i = 1;i < 11;i++) {
                for (int j = 1; j < 11; j++) {
                    if(x[i][j] == 1){
                        countShips++;
                    }
                }
            }
            if(countShips == 17){
                break;
            }

            if (k > 4) {
                k--;
            }
            skip = 0;


            if(isDone){
                printField(x);
                isDone = false;
            }

            System.out.println();
            System.out.println("Input cords for next ship: " + names[k] + shipsL[k]);
            System.out.print(">  ");
            cord = readCords();

            if(cord.length == 0){
                System.out.println("Error! Wrong input");
                continue;
            }
            try {
                for (int i = cord[0]; i <= cord[2]; i++) {
                    for (int j = cord[1]; j <= cord[3]; j++) {
                        if (x[i + 1][j] == 1 || x[i - 1][j] == 1 || x[i][j + 1] == 1 || x[i][j - 1] == 1) {
                            skip = 1;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Error! Wrong input");
                continue;
            }
            if(skip != 0){
                System.out.print("Error!Ship cross another ship!");
                System.out.println();
                continue;
            }

            if(cord[0] == cord[2] && cord[3] - cord[1] != shipsL[k] - 1){
                System.out.println("Error! Wrong l");
                continue;
            }
            if(cord[1] == cord[3] && cord[2] - cord[0] != shipsL[k] - 1){
                System.out.println("Error! Wrong l");
                continue;
            }

            for (int i = cord[0] ;i <= cord[2] ;i++){
                for(int j = cord[1];j <= cord[3] ;j++){
                    x[i][j] = 1;
                }
            }
            if(cord[0] == cord[2]){
                for(int i = cord[1];i<=cord[3];i++){
                    allCords[k][ii] = cord[0];
                    allCords[k][ii + 1] = i;
                    ii+=2;
                }
            }else{
                for(int i = cord[0];i<=cord[2];i++){
                    allCords[k][ii] = i;
                    allCords[k][ii + 1] = cord[1];
                    ii+=2;
                }
           }
            //allCords[k][ii + 1] = 546;



            isDone = true;
            k++;

        }

    }
    public static void printField(int[][] x) {
        System.out.print("  1 2 3 4 5 6 7 8 9 10");
        String[] s = {"","A","B","C","D","E","F","G","H","I","J",""};
        for (int i = 1; i < 11; i++) {
            System.out.println();
            System.out.print(s[i] + " ");
            for (int j = 1; j < 11; j++) {
                switch (x[i][j]) {
                    case 0:
                        System.out.print("~ ");
                        break;
                    case 1:
                        System.out.print("O ");
                        break;
                    case 2:
                        System.out.print("X ");
                        break;
                    case 3:
                        System.out.print("M ");
                        break;
                }
            }
        }
    }
    public static int[] readCords(){
        int n1 = 0;

        int n2 = 0;
        int[] err = {};
        int[] ret = new int[4];
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        s1.trim();
        String s2 = scanner.next();
        s2.trim();

        if(s2.length() == 0){
            return err;
        }

        int x1 = s1.charAt(0) - 'A' + 1;
        int x2 = s2.charAt(0) - 'A' + 1;
        String ss1 = s1.substring(1);
        String ss2 = s2.substring(1);

        try {
            n1 = Integer.parseInt(ss1);
        } catch (NumberFormatException e){
            return err;
        } catch (Exception e){
            return err;
        }

        try{
            n2 = Integer.parseInt(ss2);
        } catch (NumberFormatException e) {
            return err;
        } catch (Exception e){
            return err;
        }
        if(x2 < x1){
            ret[0] = x2;
            ret[2] = x1;
        }else {
            ret[0] = x1;
            ret[2] = x2;
        }
        if(n2 < n1){
            ret[1] = n2;
            ret[3] = n1;
        }else {
            ret[1] = n1;
            ret[3] = n2;
        }

        if(ret[0] != ret[2] && ret[1] != ret[3]){
            return err;
        }

        return ret;

    }
    public static int[] fightCord(){
        Scanner scanner = new Scanner(System.in);
        int[] fightcoreds = new int[2];
        int[] err = {};
        int y = 0;
        String s = scanner.next();
        s.trim();
        if(s.length() > 3){
            return err;
        }
        int x = s.charAt(0) - 'A' + 1;
        String s1 = s.substring(1);
        try {
             y = Integer.parseInt(s1);
        } catch (NumberFormatException e){
            return err;
        } catch (Exception e){
            return err;
        }
        fightcoreds[0] = x;
        fightcoreds[1] = y;
        return fightcoreds;
    }
    public static void fight(int[][] x,int[][] allCords,int shotenCords[][],int[][] x2){
        int shoot = 0;
        int ships1 = 0;
        int ships2  = 0;
        boolean hasNoShoot = true;
        boolean shipWasSanked;
        int[] cord = {};
        while(hasNoShoot){
            shipWasSanked = false;

            cord = fightCord();
            try {
                if (cord[0] > 'J' - 'A' + 1 || cord[0] < 1) {
                    System.out.println("Error! Wrong input! ");

                    System.out.println();
                    continue;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Error! Wrong input! ");

                System.out.println();
                continue;
            } catch (Exception e){
                System.out.println("Error! Wrong input! ");

                System.out.println();
                continue;
            }
            if(cord.length == 0){
                System.out.println("Error! Wrong input! ");

                System.out.println();
                continue;
            }
            try{
                shoot = x[cord[0]][cord[1]];
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.print("Error! Wrong cord ");
                System.out.println();

                continue;
            }catch (Exception e){
                System.out.print("Error! Wrong cord ");
                System.out.println();

                continue;
            }
            if(shoot == 0){
                x[cord[0]][cord[1]] = 3;
                hasNoShoot = false;
                System.out.println("You missed! ");

            }
            if(shoot == 2 || shoot == 3){
                hasNoShoot = false;
                System.out.println("has already been shooten");

            }
            if(shoot == 1){
                x[cord[0]][cord[1]] = 2;
                hasNoShoot = false;
                for(int i = 0;i<5;i++){

                    for(int j = 0;j<10;j+=2){
                         if(allCords[i][j] == cord[0] && allCords[i][j + 1] == cord[1]){
                            shotenCords[i][j] = cord[0];
                            shotenCords[i][j+1] = cord[1];
                            break;
                        }

                    }

                }
                for(int i = 0;i < 5;i++){
                    shipWasSanked = Arrays.equals(allCords[i],shotenCords[i]);

                    if(shipWasSanked){
                        shotenCords[i][0] = 33;
                        break;
                    }

                }
                for(int i = 1;i < 11;i++) {
                    for (int j = 1; j < 11; j++) {
                        if(x[i][j] == 1){
                            ships1++;
                        }
                        if(x2[i][j] == 1){
                            ships2++;
                        }
                    }
                }
                if(ships1 == 0 || ships2 == 0){
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    break;
                }

                if(shipWasSanked){
                    System.out.println("You sank a ship!");
                }else {
                    System.out.println("You hit a ship!");
                }

            }
            ships2 = 0;
            ships1 = 0;
        }
    }
    public static void printBtattleField(int[][] x) {
        System.out.print("  1 2 3 4 5 6 7 8 9 10");
        String[] s = {"","A","B","C","D","E","F","G","H","I","J",""};
        for (int i = 1; i < 11; i++) {
            System.out.println();
            System.out.print(s[i] + " ");
            for (int j = 1; j < 11; j++) {
                switch (x[i][j]) {
                    case 0:
                        System.out.print("~ ");
                        break;
                    case 1:
                        System.out.print("~ ");
                        break;
                    case 2:
                        System.out.print("X ");
                        break;
                    case 3:
                        System.out.print("M ");
                        break;
                }
            }
        }
    }

}

