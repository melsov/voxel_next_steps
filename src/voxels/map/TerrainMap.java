package voxels.map;

import java.util.HashMap;

public class TerrainMap {
	
	//Chunk storage: 14*14*4 is a capacity hint for HashMap (a guess of the size of the visible world in chunks)
	private HashMap<Coord3, Chunk> chunks = new HashMap<>(14 * 14 * 4);  
	
	/*
	 * Return null if chunkCo's y is outside of the world's vertical limits (less than 0 or greater than worldHeight)
	 * Check if we already have a chunk at chunkCo.
	 * if not, make a new chunk at chunkCo and put it in 'chunks'
	 * return the chunk at chunkCo.
	 */
	public Chunk createOrLookupChunkAt(Coord3 chunkCo) {
		Chunk chunk = chunks.get(chunkCo);
		// if the chunk is null {
		// chunk = a new chunk with this chunk coord and this terrainMap)
		//}
		// return the chunk.
		return null; // TODO: actually implement this.
	}
	
	public byte createOrLookupBlockAt(Coord3 globalCo) {
		return 0; // TODO: actually implement this. 
	}
}
