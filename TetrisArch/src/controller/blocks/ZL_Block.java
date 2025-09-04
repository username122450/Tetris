package controller.blocks;

import controller.Block;

public class ZL_Block extends Block {
    //z字方块（左边低）
    public ZL_Block() {
        this.shade = new int[][][]{
                {
                        {1, 1, 2, 2},
                        {1, 2, 2, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 2, 1},
                        {1, 1, 2, 2},
                        {1, 1, 1, 2},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 2, 2},
                        {1, 2, 2, 1},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 2, 1},
                        {1, 1, 2, 2},
                        {1, 1, 1, 2},
                        {1, 1, 1, 1}
                }
        };
    }
}
