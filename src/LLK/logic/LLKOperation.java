package LLK.logic;

public class LLKOperation{
    int u = 0;

    //String[][] _tab = new String[99][99];

    public static void show(int dimension, int[][] tab) {
        for (int i = 1; i <= dimension; i++) {
            System.out.print("   ");
            String temp = "";

            for (int j = 1; j <= dimension; j++) {
                //if (tab[j][i] == -1) System.out.print(" ");
                //else System.out.print(_tab[j][i]);
                switch (tab[j][i]) {
                    case -1: case 0:
                        temp = " ";break;
                    case 1:
                        temp = "🍇";break;
                    case 2:
                        temp = "🍉";break;
                    case 3:
                        temp = "🍎";break;
                    case 4:
                        temp = "🌶️";break;
                    case 5:
                        temp = "🍌";break;
                    case 6:
                        temp = "🍐";break;
                    case 7:
                        temp = "🍑";break;
                    case 8:
                        temp = "🐛";break;
                }
                System.out.print(temp);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
    /*
    public static void clear() throws AWTException
    {
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R);                    // 按下R键
        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
        r.delay(100);       
    }//スクリンを切り消し
    */

    public void shokika(int[][] tab) {//地図を初期化        
        for (int i = 0; i <= 13; i++) {
            for (int j = 0; j <= 13; j++)
                tab[i][j] = -1;
        }
    }
    //ゼロロルも最後のロルも地図外のアクセサリーです。

    public void tsukuru(int dimension, int[][] tab) {//地図を作る
        int katachi = 0;
        int zettaipos1 = 0; int zettaipos2 = 0; int mark = 0;
        for (int i = dimension * dimension; i > 0; i -= 2) {
            zettaipos1 = (int) (Math.random() * i) + 1;
            zettaipos2 = (int) (Math.random() * (i - 1)) + 1;
            katachi = (int) (Math.random() * 9) + 1;
            mark = 0;
            /*
            System.out.print(i + " ");
            System.out.print(zettaipos1 + " ");
            System.out.print(zettaipos2 + " ");
            System.out.println(katachi);
            */
            for (int j = 1; j <= dimension; j++) {
                for (int k = 1; k <= dimension; k++) {
                    if (tab[j][k] == -1) {
                        zettaipos1--; zettaipos2--;
                    }
                    else continue;
                    if (zettaipos1 == 0) {
                        tab[j][k] = katachi;
                        if (++mark == 2) break;
                        zettaipos2++;
                    }
                    if (zettaipos2 == 0) {
                        tab[j][k] = katachi;
                        if (++mark == 2) break;
                    }
                    if (mark == 2) break;
                    //System.out.print(k + " " + j + " " + zettaipos1 + " " + zettaipos2 + "/");
                }
            }
            //System.out.println("");
            //show(dimension);
        }
    }

    public void henka(int dimension, int cnt, int[][] tab) {//地図を作る
        int katachi = 0;
        int zettaipos1 = 0; int zettaipos2 = 0; int mark = 0;
        int[][] marktab = new int[dimension + 1][dimension + 1];
        for (int i = 1; i <= dimension; i++) {
            for (int j = 1; j <= dimension; j++) {
                if (tab[i][j] == -1) 
                    marktab[i][j] = -1;
                else marktab[i][j] = 0;
            }
        }
        for (int i = cnt; i > 0; i -= 2) {
            zettaipos1 = (int) (Math.random() * i) + 1;
            zettaipos2 = (int) (Math.random() * (i - 1)) + 1;
            katachi = (int) (Math.random() * 8) + 1;
            mark = 0;
            for (int j = 1; j <= dimension; j++) {
                for (int k = 1; k <= dimension; k++) {
                    if (marktab[j][k] == 0) {
                        zettaipos1--; zettaipos2--;
                    }
                    else continue;
                    if (zettaipos1 == 0) {
                        zettaipos2++;
                        tab[j][k] = katachi;
                        marktab[j][k] = -1;
                        mark++;
                        System.out.println(i + " row:" + j + " col:" + k);
                        if (mark == 2) break;
                    }
                    if (zettaipos2 == 0) {
                        tab[j][k] = katachi;
                        marktab[j][k] = -1;
                        mark++;
                        System.out.println(i + " row:" + j + " col:" + k);
                        if (mark == 2) break;
                   }
                }
                if (mark == 2) break;
            }
        }
		//System.out.println(u);
    }

    public boolean handan(int x1, int y1, int x2, int y2, int dimension, int[][] tab) {
    		if (x1 == x2 && y1 == y2) return false;
    	
        if (tab[x1][y1] != tab[x2][y2]) return false;
        if (tab[x1][y1] == -1 || tab[x2][y2] == -1) return false;
        int col1up = y1; int col1down = y1; 
        int row1left = x1; int row1right = x1;
        int col2up = y2; int col2down = y2; 
        int row2left = x2; int row2right = x2;
        boolean mark = false;
        while (--col1up >= 0 && tab[x1][col1up] == -1); col1up++;
        while (--col2up >= 0 && tab[x2][col2up] == -1); col2up++;
        while (++col1down <= dimension + 1 && tab[x1][col1down] == -1); col1down--;
        while (++col2down <= dimension + 1 && tab[x2][col2down] == -1); col2down--;
        while (--row1left >= 0 && tab[row1left][y1] == -1); row1left++;
        while (--row2left >= 0 && tab[row2left][y2] == -1); row2left++;
        while (++row1right <= dimension + 1 && tab[row1right][y1] == -1); row1right--;
        while (++row2right <= dimension + 1 && tab[row2right][y2] == -1); row2right--;

        int left = row1left > row2left ? row1left : row2left;
        int right = row1right < row2right ? row1right : row2right;
        int up = col1up > col2up ? col1up : col2up;
        int down = col1down < col2down ? col1down : col2down;
        int x_1 = x1 < x2 ? x1 : x2; int x_2 = x1 + x2 - x_1;
        int y_1 = y1 < y2 ? y1 : y2; int y_2 = y1 + y2 - y_1;

        //System.out.println("l:" + left + " r:" + right + " u:" + up + " d:" + down);
        //System.out.println("x1:" + x1 +" x2:" + x2 + " y1:" + y1 + " y2:" + y2);

        for (int i = left; i <= right; i++) {
            mark = true;
            for (int j = y_1 + 1; j < y_2; j++) {
                if (tab[i][j] != -1) {
                    mark = false;
                    break;
                }
            }
            if (mark == true) return true;
        }
        for (int i = up; i <= down; i++) {
            mark = true;
            for (int j = x_1 + 1; j < x_2; j++) {
                if (tab[j][i] != -1) {
                    mark = false;
                    break;
                }
            }
            if (mark == true) return true;
        }
        return false;
    }

    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please insert the dimension you need");
        int dimension = in.nextInt();
        while (dimension % 2 == 1) {
            System.out.println("You should insert a even number under 100");
            dimension = in.nextInt();
        }
        int[][] tab = new int[dimension + 2][dimension + 2];
        int cnt = dimension * dimension;
        int x1 = 0; int y1 = 0; int x2 = 0; int y2 = 0;
        int message = 0;
        boolean result;
        
        shokika(tab);
        tsukuru(dimension, tab);

        while (cnt > 0) {
            //clear();
            show(dimension, tab);
            switch (message) {
                case 0:                
                    System.out.println("Please insert your choice");
                    break;
                case 1:
                    System.out.println("You gave out a right pair, try the next");
                    break;
                case 2:
                    System.out.println("Wrong Answer, try again");
                    break;
                case 3:
                    System.out.println("Wrong format. You should insert x1, y1, x2, y2 respectively, with a space between each two words");
                    break;
                default:
                    break;
            }
            x1 = in.nextInt(); y1 = in.nextInt();
            x2 = in.nextInt(); y2 = in.nextInt();
            if (x1 == -1 && y1 == -1 && x2 == -1 && y2 == -1) {
                do {
                    x1 = in.nextInt();
                    y1 = in.nextInt();
                    System.out.println(tab[x1][y1]);
                } while (x1 != 0 && y1 != 0);
            }
            if (x1 == 0 && y1 == 0 && x2 == 0 && y2 == 0) {
                message = 0;
                henka(dimension, cnt, tab);
                continue;
            }
            if ((x1 < 0 || x1 > dimension) || (x2 < 0 || x2 > dimension) || (y1 < 0 || y1 > dimension) || (y2 < 0 || y2 > dimension)) {
                message = 3;
                continue;
            }
            result = handan(x1, y1, x2, y2, dimension, tab);
            if (result == false) message = 2;
            else {
                message = 1;
                tab[x1][y1] = -1;
                tab[x2][y2] = -1;
                cnt -= 2;
            }
        }
        System.out.println("You Win!");
        in.close();
    }
*/}