package voxels.map.blockdata;

import com.sudoplay.joise.module.Module;

/*
 * This class's job is to tell
 * whoever wants to know
 * what block-type exists
 * at a given x, y and z
 */

public class TerrainDataProvider {
    private static long seed = -21234;
    private Module module;

    public TerrainDataProvider() {
            setupModule();
    }

    public int getBlockDataAtPosition(int xin, int yin, int zin) {
        return 2;
    }
    
    private void setupModule() {
    	// does nothing at the moment
    }
}
