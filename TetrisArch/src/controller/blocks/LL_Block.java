package controller.blocks;

import controller.Block;

public class LL_Block extends Block {
    //L形方块
    public LL_Block() {
        this.shade = new int[][][]{
                {
                        {1, 2, 1, 1},
                        {1, 2, 1, 1},
                        {1, 2, 2, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1},
                        {1, 2, 2, 2},
                        {1, 2, 1, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 2, 2, 1},
                        {1, 1, 2, 1},
                        {1, 1, 2, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1},
                        {1, 1, 2, 1},
                        {2, 2, 2, 1},
                        {1, 1, 1, 1}
                }
        };
    }
}
