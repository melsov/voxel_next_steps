package voxels.meshconstruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import voxels.map.Coord3;
import voxels.map.TerrainMap;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class BlockMeshUtil {
	/*
	 * Make four verts,
	 * 6 indices and 4 UV vector2s
	 * add them to mesh Set
	 */
	public static void AddFaceMeshData(Coord3 pos, MeshSet mset, int direction, int triIndexStart)
	{
		FaceVertices(mset, pos, direction);
		UVsForDirection(mset, direction);
		IndicesForDirection(mset, triIndexStart);
	}
	
	/*
	 * returns an array of vertices that describe one of the 6 faces of a 3D box shaped column
	 * decide which face based on @param dir (direction) 
	 */
	private static void FaceVertices(MeshSet mset, Coord3 position, int dir ) {
		for (int i = 0; i < 4; ++i) {
			mset.vertices.add(faceVertices[dir][i].add(position.toVector3f())); 
		}
	}
	
	private static void IndicesForDirection(MeshSet mset, int triIndexStart) {
		for (int i : FaceIndices) {
			mset.indices.add(i + triIndexStart);
		}
	}
	
	// DELETE THIS!!
//	private static List<Vector2f> uvs = Arrays.asList(new Vector2f(0,0),new Vector2f(0,1),new Vector2f(1,1),new Vector2f(1,0));
	
	private static void UVsForDirection(MeshSet mset, int dir) {
//		mset.uvs.addAll(uvs); // DELETE THIS!
		/*
		 * CHANGE THE X AND Y OF OFFSETSTART. X AND Y CAN EACH BE 0f, .25f, .5f, or .75f 
		 * TRY ANY OF THE 16 COMBINATIONS: (.25f, .25f) for stone, (0f, .75f) for side grass
		 * LOOK AT THE TEXTURE "dog_64d_.jpg" TO SEE THAT YOUR CHOICE CORRESPONDS TO THE 
		 * TILE OFFSET INDICATED BY X AND Y. YOU MUST EDIT THE FRAGMENT SHADER, AS DESCRIBED HERE:
		 * http://voxel.matthewpoindexter.com/class/block-faces-part-2-1-fixing-the-annoyingly-mis-aligned-texture/
		 * FOR THIS TO WORK (WELL). 
		 */
		Vector2f offsetStart = new Vector2f(0f,0f);
		mset.uvs.addAll(Arrays.asList(
				offsetStart ,
				new Vector2f(offsetStart.x, offsetStart.y +.25f),
				new Vector2f(offsetStart.x + .25f, offsetStart.y + .25f),new Vector2f(offsetStart.x + .25f, offsetStart.y)
				));
	}

	/* 
	 * NOTE: this is a member variable, not a method.
	 * SIDE NOTE: one should usually avoid large 2D arrays in Java. (arrays with two subscripts [][]) 
	 * This array is small, so not a problem.  */
	public static Vector3f[][] faceVertices = new Vector3f[][] {
		//Xneg
		new Vector3f[] {
			new Vector3f(-0.5f, -0.5f, -0.5f),
			new Vector3f(-0.5f,  0.5f, -0.5f),
			new Vector3f(-0.5f,  0.5f,  0.5f),
			new Vector3f(-0.5f, -0.5f,  0.5f),	
		},
		//Xpos
		new Vector3f[] {
			new Vector3f(0.5f, -0.5f,  0.5f),
			new Vector3f(0.5f,  0.5f,  0.5f),
			new Vector3f(0.5f,  0.5f, -0.5f),
			new Vector3f(0.5f, -0.5f, -0.5f),
		},
		//Yneg
		new Vector3f[] {
			new Vector3f(-0.5f, -0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f,  0.5f),
			new Vector3f( 0.5f, -0.5f,  0.5f),
			new Vector3f( 0.5f, -0.5f, -0.5f),
		},
		//Ypos
		new Vector3f[] {
			new Vector3f( 0.5f, 0.5f, -0.5f),
			new Vector3f( 0.5f, 0.5f,  0.5f),
			new Vector3f(-0.5f, 0.5f,  0.5f),
			new Vector3f(-0.5f, 0.5f, -0.5f),
		},
		//Zneg
		new Vector3f[] {
			new Vector3f( 0.5f, -0.5f, -0.5f),
			new Vector3f( 0.5f,  0.5f, -0.5f),
			new Vector3f(-0.5f,  0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f, -0.5f),
		},
		//Zpos
		new Vector3f[] {
			new Vector3f(-0.5f, -0.5f, 0.5f),
			new Vector3f(-0.5f,  0.5f, 0.5f),
			new Vector3f( 0.5f,  0.5f, 0.5f),
			new Vector3f( 0.5f, -0.5f, 0.5f),
		},
	};
	private static final int[] FaceIndices = new int[] {0,3,2, 0,2,1};
	

}
