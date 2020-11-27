package com.example.sapper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.graphics.*;
import java.util.Random;

public class SapperMain extends Activity implements OnClickListener, OnLongClickListener {

    private int WIDTH = 9;
    private int HEIGHT = 9;
    int num_mines = 10;

    private Button[][] cells;
    boolean[][] check;
    boolean[][] mark;

    boolean start = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);

        makeCells();
        restart();
        menu();
        win();
    }

    void generate(float tappedX, float tappedY) {   //Generation of mines
        Random rand = new Random();
        int[] x = new int[num_mines], y = new int[num_mines];

        for (int i = 0; i < num_mines; i++) {
            boolean again = false;

            x[i] = rand.nextInt(HEIGHT);
            y[i] = rand.nextInt(WIDTH);

            for (int j = i - 1; j >= 0; j--) {
                if ((x[i] == x[j] && y[i] == y[j]) || (x[i] == tappedX && y[i] == tappedY)) {
                    again = true;
                    break;
                }
            }

            if (again) {
                i--;
            }
        }

        boolean[][] map = new boolean[HEIGHT + 2][WIDTH + 2];
        for (int i = 0; i < num_mines; i++) {
            map[x[i] + 1][y[i] + 1] = true;
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int mines = 0;
                for (int k = -1; k <= 1; k++) {
                    for (int m = -1; m <= 1; m++) {
                        if (k != 0 || m != 0) {
                            if (map[i + k + 1][j + m + 1]) {
                                mines++;
                            }
                        }
                    }
                }
                if (mines != 0) {
                    cells[i][j].setText(mines + "");
                }
            }
        }

        for (int i = 0; i < num_mines; i++) {
            cells[x[i]][y[i]].setText("M");
        }
    }

    void setColor(int i, int j) {   //Sets different color for every num.
        cells[i][j].setBackgroundColor(Color.WHITE);
        int num = Integer.parseInt(cells[i][j].getText().toString());
        switch (num) {
            case 1:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum1));
                break;
            case 2:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum2));
                break;
            case 3:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum3));
                break;
            case 4:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum4));
                break;
            case 5:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum5));
                break;
            case 6:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum6));
                break;
            case 7:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum7));
                break;
            case 8:
                cells[i][j].setTextColor(getResources().getColor(R.color.forNum8));
                break;
            default:
                cells[i][j].setTextColor(Color.BLACK);
        }
    }

    void openEmptyCell(int y, int x) {  //Opens all space around empty cell.
        cells[y][x].setBackgroundColor(Color.WHITE);
        check[y][x] = true;

        if (y < HEIGHT - 1) {
            if (!check[y + 1][x] && cells[y + 1][x].getText() != "M") {
                if (cells[y + 1][x].getText() != "") {
                    setColor(y + 1, x);
                    check[y + 1][x] = true;
                } else {
                    openEmptyCell(y + 1, x);
                }
            }
        }
        if (y > 0) {
            if (!check[y - 1][x] && cells[y - 1][x].getText() != "M") {
                if (cells[y - 1][x].getText() != "") {
                    setColor(y - 1, x);
                    check[y - 1][x] = true;
                } else {
                    openEmptyCell(y - 1, x);
                }
            }
        }

        if (x < WIDTH - 1) {
            if (!check[y][x + 1] && cells[y][x + 1].getText() != "M") {
                if (cells[y][x + 1].getText() != "") {
                    setColor(y, x + 1);
                    check[y][x + 1] = true;
                } else {
                    openEmptyCell(y, x + 1);
                }
            }
        }
        if (x > 0) {
            if (!check[y][x - 1] && cells[y][x - 1].getText() != "M") {
                if (cells[y][x - 1].getText() != "") {
                    setColor(y, x - 1);
                    check[y][x - 1] = true;
                } else {
                    openEmptyCell(y, x - 1);
                }
            }
        }

        if (y < HEIGHT - 1 && x < WIDTH - 1) {
            if (!check[y + 1][x + 1] && cells[y + 1][x + 1].getText() != "M") {
                if (cells[y + 1][x + 1].getText() != "") {
                    setColor(y + 1, x + 1);
                    check[y + 1][x + 1] = true;
                } else {
                    openEmptyCell(y + 1, x + 1);
                }
            }
        }
        if (y > 0 && x > 0) {
            if (!check[y - 1][x - 1] && cells[y - 1][x - 1].getText() != "M") {
                if (cells[y - 1][x - 1].getText() != "") {
                    setColor(y - 1, x - 1);
                    check[y - 1][x - 1] = true;
                } else {
                    openEmptyCell(y - 1, x - 1);
                }
            }
        }

        if (y < HEIGHT - 1 && x > 0) {
            if (!check[y + 1][x - 1] && cells[y + 1][x - 1].getText() != "M") {
                if (cells[y + 1][x - 1].getText() != "") {
                    setColor(y + 1, x - 1);
                    check[y + 1][x - 1] = true;
                } else {
                    openEmptyCell(y + 1, x - 1);
                }
            }
        }
        if (y > 0 && x < WIDTH - 1) {
            if (!check[y - 1][x + 1] && cells[y - 1][x + 1].getText() != "M") {
                if (cells[y - 1][x + 1].getText() != "") {
                    setColor(y - 1, x + 1);
                    check[y - 1][x + 1] = true;
                } else {
                    openEmptyCell(y - 1, x + 1);
                }
            }
        }
    }

    void openCell(int y, int x) {
        if (cells[y][x].getText() == "M") {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    check[i][j] = true;
                    if (cells[i][j].getText() != "" && cells[i][j].getText() != "M") {
                        setColor(i, j);
                    } else {
                        cells[i][j].setBackgroundColor(Color.WHITE);
                        cells[i][j].setTextColor(Color.BLACK);
                    }
                }
            }
            cells[y][x].setBackgroundColor(Color.RED);
        } else if (cells[y][x].getText() == "") {
            openEmptyCell(y, x);
        } else {
            check[y][x] = true;
            setColor(y, x);
        }
    }

    void openCellsAround(int y, int x) {
        int mines = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (y + i < HEIGHT && x + j < WIDTH && y + i >= 0 && x + j >= 0 && (i != 0 || j != 0)) {
                    if (mark[y + i][x + j]) {
                        mines++;
                    }
                }
            }
        }

        if (mines == Integer.parseInt((cells[y][x].getText()).toString())) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (y + i < HEIGHT && x + j < WIDTH && y + i >= 0 && x + j >= 0 && (i != 0 || j != 0)) {
                        if (!check[y + i][x + j] && !mark[y + i][x + j]) {
                            openCell(y + i, x + j);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Button tappedCell = (Button) v;
        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        if(!start) {
            if (!check[tappedY][tappedX] && !mark[tappedY][tappedX]) {
                cells[tappedY][tappedX].setBackgroundColor(Color.RED);
                mark[tappedY][tappedX] = true;
            } else if (mark[tappedY][tappedX]) {
                cells[tappedY][tappedX].setBackgroundColor(getResources().getColor(R.color.lightBlue));
                mark[tappedY][tappedX] = false;
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;
        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        if (start) {
            check = new boolean[HEIGHT][WIDTH];
            mark = new boolean[HEIGHT][WIDTH];
            generate(tappedY, tappedX);
            start = false;
        }

        if (!check[tappedY][tappedX] && !mark[tappedY][tappedX]) {
            openCell(tappedY, tappedX);
        } else if (cells[tappedY][tappedX].getText() != "" && cells[tappedY][tappedX].getText() != "M") {
            openCellsAround(tappedY, tappedX);
        }
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
        }
    }

    void restart() {
        Button button = (Button) findViewById(R.id.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!start) {
                    for (int i = 0; i < HEIGHT; i++) {
                        for (int j = 0; j < WIDTH; j++) {
                            check[i][j] = false;
                            mark[i][j] = false;
                            start = true;
                            cells[i][j].setBackgroundColor(getResources().getColor(R.color.lightBlue));
                            cells[i][j].setTextColor(getResources().getColor(R.color.ghost));
                            cells[i][j].setText("");
                        }
                    }
                }
            }
        });
    }

    void menu() {
        Button button = (Button) findViewById(R.id.menu_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void win() {

    }
}