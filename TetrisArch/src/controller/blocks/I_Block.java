package controller.blocks;

import controller.Block;

public class I_Block extends Block {
    //长条形方块
    public I_Block(){
        this.shade = new int[][][]{
                {
                        {1,1,2,1},
                        {1,1,2,1},
                        {1,1,2,1},
                        {1,1,2,1},
                },
                {
                        {1,1,1,1},
                        {1,1,1,1},
                        {2,2,2,2},
                        {1,1,1,1},
                },
                {
                        {1,2,1,1},
                        {1,2,1,1},
                        {1,2,1,1},
                        {1,2,1,1},
                },
                {
                        {1,1,1,1},
                        {2,2,2,2},
                        {1,1,1,1},
                        {1,1,1,1},
                }
        };
    }
}
