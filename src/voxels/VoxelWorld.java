package voxels;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import voxels.map.Chunk;
import voxels.map.Coord3;
import voxels.map.Direction;
import voxels.map.TerrainMap;
import voxels.meshconstruction.BlockMeshUtil;
import voxels.meshconstruction.MeshSet;

import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;

/**
 * Created by didyouloseyourdog on 8/10/14.
 */
public class VoxelWorld extends SimpleApplication
{
    MaterialLibrarian materialLibrarian;
    TerrainMap map = new TerrainMap();

    @Override
    public void simpleUpdate(float secondsPerFrame) {}

    @Override
    public void simpleInitApp() {
        materialLibrarian = new MaterialLibrarian(assetManager);
        setUpTheCam();
        makeSomeChunks();
        attachCoordinateAxes(Vector3f.ZERO);
    }

    private void makeSomeChunks() {
    	// TODO: actually make at least one chunk.
    	Coord3 chunkCoord = new Coord3(0,0,0);
    	Chunk chunk = map.createOrLookupChunkAt(chunkCoord);
    	chunk.chunkBrain.meshDirty = true; // THIS IS STEP 4 OF 'PHASE 2.1'
    	rootNode.attachChild(chunk.chunkBrain.getGeometry()); // THIS IS STEP 5 
    	// TODO: implement chunk brain and getGeometry()
    }
    /*
     * TEST METHOD. THIS CODE WILL MOVE!!
     */
    private void addTestBlockFace() {
    	
    	MeshSet mset = new MeshSet(); // 1
    	Coord3 pos = new Coord3(0,0,0); // 2
    	int triIndex = 0;
    	for(int i = 0; i <= Direction.ZPOS; ++i) {
	    	BlockMeshUtil.AddFaceMeshData(pos, mset, i, triIndex); // 3
	    	triIndex += 4;
    	}
    	
    	Mesh testMesh = new Mesh(); // 4
    	ApplyMeshSet(mset, testMesh); // 5
    	Geometry someGeometry = new Geometry("test geom", testMesh); // 6
    	someGeometry.setMaterial(materialLibrarian.getBlockMaterial()); // 7
    	rootNode.attachChild(someGeometry); // 8
    	
    	//new section. show same mesh with a texture material
    	rootNode.attachChild(someGeometry); 
    	Mesh texturedTestMesh = new Mesh(); 
    	ApplyMeshSet(mset, texturedTestMesh);
    	Geometry someTexturedGeometry = new Geometry("test geom", testMesh); 
    	someTexturedGeometry.setMaterial(materialLibrarian.getTexturedBlockMaterial());
    	rootNode.attachChild(someTexturedGeometry); 
    }
    /*
     * TEST METHOD. THIS CODE WILL MOVE!!
     */
    public static void ApplyMeshSet(MeshSet mset, Mesh bigMesh)
    {
        if (bigMesh == null) {
            System.out.println("Something is not right. This mesh is null. We really be throwing an exception here.");
            return;
        }
		
		bigMesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(mset.vertices.toArray(new Vector3f[0])));
		bigMesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(mset.uvs.toArray(new Vector2f[0])));

		/* google guava library helps with turning Lists into primitive arrays
		* "Ints" and "Floats" are guava classes.
		* */ 
		bigMesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(Ints.toArray(mset.indices)));
			
//		bigMesh.clearBuffer(Type.Color);
//		bigMesh.setBuffer(Type.Color, 4, Floats.toArray(mset.colors));

//        BoundingBox bbox = new BoundingBox(new Vector3f(0,0,0), new Vector3f(Chunk.XLENGTH, Chunk.YLENGTH, Chunk.ZLENGTH));
//        bigMesh.setBound(bbox);
//		bigMesh.updateBound();

	}
    
    private void makeADemoMeshAndAdditToTheRootNode() {
        Mesh m = new Cylinder(12,24,5,11);
        Geometry g = new Geometry("demo geom", m);
        g.setMaterial(materialLibrarian.getBlockMaterial());
        rootNode.attachChild(g);
        attachCoordinateAxes(Vector3f.ZERO);
    }
    private void attachCoordinateAxes(Vector3f pos){
		 Arrow arrow = new Arrow(Vector3f.UNIT_X);
		 arrow.setLineWidth(4); // make arrow thicker
		 putShape(arrow, ColorRGBA.Red).setLocalTranslation(pos);
		 
		 arrow = new Arrow(Vector3f.UNIT_Y);
		 arrow.setLineWidth(4); // make arrow thicker
		 putShape(arrow, ColorRGBA.Green).setLocalTranslation(pos);
		 
		 arrow = new Arrow(Vector3f.UNIT_Z);
		 arrow.setLineWidth(4); // make arrow thicker
		 putShape(arrow, ColorRGBA.Blue).setLocalTranslation(pos);
    }
    	 
	private Geometry putShape(Mesh shape, ColorRGBA color){
	  Geometry g = new Geometry("coordinate axis", shape);
	  Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	  mat.getAdditionalRenderState().setWireframe(true);
	  mat.setColor("Color", color);
	  g.setMaterial(mat);
	  rootNode.attachChild(g);
	  return g;
	}
    
    private void setUpTheCam() {
        flyCam.setMoveSpeed(30);
    }
    
    public enum Mood {
    	HAPPY, SAD, IRKED;
    }

    /*******************************
     * Program starts here... ******
     *******************************/
    public static void main(String[] args) {
        VoxelWorld app = new VoxelWorld();
        ScreenSettings(app, false);
        app.start(); // start the game
    }
    private static void ScreenSettings(VoxelWorld app, boolean fullScreen) {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();
        int SCREEN_MODE=0; // note: there are usually several, let's pick the first
        AppSettings settings = new AppSettings(true);
        float scale_screen = fullScreen ? 1f : .6f;
        Vector2f screenDims = new Vector2f((int)(modes[SCREEN_MODE].getWidth() * scale_screen ),(int)(modes[SCREEN_MODE].getHeight() * scale_screen ));
        settings.setResolution((int)screenDims.x,(int) screenDims.y);
        settings.setFrequency(modes[SCREEN_MODE].getRefreshRate());
        settings.setBitsPerPixel(modes[SCREEN_MODE].getBitDepth());
        if (fullScreen) {
            settings.setFullscreen(device.isFullScreenSupported());
        }
        app.setSettings(settings);
        app.setShowSettings(false);
    }

    public class MaterialLibrarian
    {
        private Material blockMaterial;
        private Material texturedBlockMaterial;
        private AssetManager _assetManager;

        public MaterialLibrarian(AssetManager assetManager_) {
            _assetManager = assetManager_;
        }

        public Material getBlockMaterial() {
            if (blockMaterial == null) {
                Material wireMaterial = new Material(assetManager, "/Common/MatDefs/Misc/Unshaded.j3md");
                wireMaterial.setColor("Color", ColorRGBA.Green);
                wireMaterial.getAdditionalRenderState().setWireframe(true);
                wireMaterial.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
                blockMaterial = wireMaterial;
            }
            return blockMaterial;
        }
        public Material getTexturedBlockMaterial() {
            if (texturedBlockMaterial == null) {
            	Material mat = new Material(assetManager, "BlockTex2.j3md");
                Texture blockTex = assetManager.loadTexture("dog_64d_.jpg");
                blockTex.setMagFilter(Texture.MagFilter.Nearest);
                blockTex.setWrap(Texture.WrapMode.Repeat);
                mat.setTexture("ColorMap", blockTex);
                texturedBlockMaterial = mat;
            }
            return texturedBlockMaterial;
        }
    }


}
