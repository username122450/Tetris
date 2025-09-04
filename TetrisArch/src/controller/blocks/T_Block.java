package controller.blocks;

import controller.Block;

public class T_Block extends Block{
    //T形方块
    public T_Block() {
        this.shade = new int[][][]{
                {
                        {1, 2, 1, 1},
                        {1, 2, 2, 2},
                        {1, 2, 1, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 2, 2, 2},
                        {1, 1, 2, 1},
                        {1, 1, 2, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 2},
                        {1, 2, 2, 2},
                        {1, 1, 1, 2},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 2, 1},
                        {1, 1, 2, 1},
                        {1, 2, 2, 2},
                        {1, 1, 1, 1}
                }
        };
    }
}
