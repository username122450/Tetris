package controller.blocks;

import controller.Block;

public class LR_Block extends Block {
    //L形方块（L的镜像）
    public LR_Block() {
        this.shade = new int[][][]{
                {
                        {1, 1, 2, 1},
                        {1, 1, 2, 1},
                        {1, 2, 2, 1},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1},
                        {1, 2, 1, 1},
                        {1, 2, 2, 2},
                        {1, 1, 1, 1}
                },
                {
                        {1, 1, 1, 1},
                        {1, 2, 2, 1},
                        {1, 2, 1, 1},
                        {1, 2, 1, 1}
                },
                {
                        {1, 1, 1, 1},
                        {2, 2, 2, 1},
                        {1, 1, 2, 1},
                        {1, 1, 1, 1}
                }
        };
    }
}
