package com.game;

import java.util.Random;

public class GameLife implements Cloneable{

    private final int size;

    private boolean[][] index;


    public GameLife(int size){
        this.size = size;
        index = new boolean[size][size];
    }

    private GameLife(GameLife gameLife){
        this(gameLife.size);
        for (int i = 0 ; i < gameLife.size; i++)
            this.index[i] = gameLife.getIndex()[i].clone();

    }

    public boolean[][] getIndex(){
        return this.index;
    }

    public int getSize(){
        return this.size;
    }

    public void setIndex(boolean[][] index){
        this.index = index;
    }

    /**
     * Set random position for our game
     * @param posib possibility of random
     * @throws IllegalArgumentException if input > 1 or input < 0
     */
    public void setRandomPosition(double posib){
        if(posib < 1 && posib > 0){
            Random random = new Random();

            for(int i = 0; i< size ; i++){
                for(int j = 0; j < size ; j++){

                    if(random.nextDouble() < posib){
                        index[i][j] = true;
                    }

                }
            }

        }else{
            throw new IllegalArgumentException();
        }

    }// End of method

    /**
     * go to next position
     */
    public void next(){
        boolean[][] newIndex = new boolean[size][size];


        for(int i = 0; i< size ; i++){
            for(int j = 0; j < size ; j++){
                int neighbors = getNeighborsCount(index, j, i);

                if((neighbors == 2 && index[i][j]) || (neighbors == 3))
                    newIndex[i][j] = true;
            }
        }

        this.index = newIndex;
    }

    /**
     * Get count of true neighbors
     * @param index current position
     * @param x position of x
     * @param y position of y
     * @return count of true neighbors
     */
    private int getNeighborsCount(boolean[][] index, int x, int y){
        int counter = 0;

        for(int i = y - 1; i <= y + 1 ; i++){
            for(int j = x - 1; j <= x + 1; j++){
                if((i > -1 && i < size) && (j > -1 && j < size)){
                    if(index[i][j])
                        counter++;
                }
            }
        }

        if(index[y][x])
            counter--;

        return counter;
    }

    public String toString(){
        StringBuilder out = new StringBuilder();

        for(int i = 0; i< size ; i++){
            for(int j = 0; j < size ; j++){
                if(index[i][j])
                    out.append("*");
                else
                    out.append(" ");
            }
            out.append("\n");
        }



        return out.toString();
    }

    @Override
    public Cloneable clone(){
        return new GameLife(this);
    }

    public static void main(String[] args) {

        GameLife game = new GameLife(15);
        game.setRandomPosition(.4);

        new Thread(() -> {

            while(true){

                System.out.println(game);

                try{
                    Thread.currentThread().sleep(1000);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                game.next();

            }
        }).start();

    }

}
