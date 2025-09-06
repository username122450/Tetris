package controller.blocks;

import controller.Block;

public class O_Block extends Block {
    //正方行方块
    public O_Block() {
        this.shade = new int[][][]{
                {
                        {1,2,2,1},
                        {1,2,2,1},
                        {1,1,1,1},
                        {1,1,1,1},
                },
                {
                        {1,2,2,1},
                        {1,2,2,1},
                        {1,1,1,1},
                        {1,1,1,1},
                },
                {
                        {1,2,2,1},
                        {1,2,2,1},
                        {1,1,1,1},
                        {1,1,1,1},
                },
                {
                        {1,2,2,1},
                        {1,2,2,1},
                        {1,1,1,1},
                        {1,1,1,1},
                }
        };
    }
}
