package voxels.map;

/*
 * 
 */
public class Chunk {
	public final static int XLENGTH = 16;
	public final static int YLENGTH = 16;
	public final static int ZLENGTH = 16;
	
	public final Coord3 position;
	public final TerrainMap terrainMap;
	public final ChunkBrain chunkBrain;
	
	public Chunk(Coord3 _position, TerrainMap _map) {
		position = _position; 
		terrainMap = _map;
		chunkBrain = new ChunkBrain(this);
	}
	
	/*
	 * Chunk Position Methods
	 * Chunk Positions are always the same as 
	 * the position of the first block in the chunk (block at lower, xneg, zneg corner with local pos: (0,0,0))
	 * divided by their lengths (16 in other words)
	 * example: if ChunkPos is (1, 0, 0)
	 * its "first" block is at world pos (16, 0, 0)
	 * (16, 0, 0) would also be the chunk's "WorldPosition"
	 * Conversely, a block with world pos (16, 0, 2)
	 * would have chunk local pos (0, 0, 2)
	 */
	public static Coord3 ToChunkPosition(int pointX, int pointY, int pointZ) {
		int chunkX = // divide pointX by XLENGTH
		int chunkY = // '' Y '' YLENGTH
		int chunkZ = // '' Z '' ZLENGTH
		return new Coord3(chunkX, chunkY, chunkZ);
	}
    public static Coord3 toChunkLocalCoord(Coord3 woco) {
		return toChunkLocalCoord(woco.x, woco.y, woco.z);
	}
	public static Coord3 toChunkLocalCoord(int x, int y, int z) {
		// TRICKY FOR NEGATIVE NUMBERS!
		// FOR NOW, USE MOD TO MAKE A SOLUTION THAT 
		// WORKS FOR POSITIVE NUMBERS
		int xlocal = // x % XLENGTH; etc.
		return new Coord3(xlocal, ylocal, zlocal);
	}
	
	public static Coord3 ToWorldPosition(Coord3 chunkPosition) {
		return // HINT: use the method below to find the world position of a block at localPosition = (0,0,0)
	}
	public static Coord3 ToWorldPosition(Coord3 chunkPosition, Coord3 localPosition) {
		/*
		 * Opposite of ToChunkPosition
		 */
		int worldX =
		int worldY =
		int worldZ =
		return new Coord3(worldX, worldY, worldZ);
	}
}
