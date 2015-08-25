package com.mondes.zx3d.jmonkey;

import gui.JSpeccy;
import gui.PixelView;
import gui.PixelWritingListener;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainLodControl;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.SkyFactory;

public class Zx3dLauncher extends SimpleApplication
{
    TerrainQuad terrain;
    Material terrainMat;
    private static Map<Point, Spatial> createdSprites= new HashMap();
    private Stack<Spatial> boxes= new Stack<Spatial>();
    private boolean initialized;
    
    

    /** Fill space with random static cubes. */
    private void makeCubes(int number)
    {
	for (int i= 0; i < number; i++)
	{
	    // randomize 3D coordinates
	    int j= 20;
	    int k= 80;
	    Vector3f loc= new Vector3f(FastMath.nextRandomInt(j, k), FastMath.nextRandomInt(j, k), FastMath.nextRandomInt(j, k));
	    rootNode.attachChild(createBox("Cube" + i, loc, ColorRGBA.randomColor()));
	}
    }

    public Spatial createBox(String name, Vector3f loc, ColorRGBA color)
    {
	Box mesh= new Box(2.5f, 2.5f, 2.5f);
	Spatial geom= new Geometry(name, mesh);

	Material mat= new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
	mat.setColor("Color", color);
	geom.setMaterial(mat);
	geom.setLocalTranslation(loc);

	//	geom= createWilly(loc.x, loc.y, loc.z);

	return geom;
    }

    @Override
    /** initialize the scene here. */
    public void simpleInitApp()
    {
	//	for (int i= 0; i < 500; i++)
	//	{
	//	    Spatial box= createBox("box"+i, new Vector3f(0,  0,  0), ColorRGBA.Green);
	//	    boxes.push(box);
	//	    rootNode.attachChild(box);
	//	}

	mouseInput.setCursorVisible(true);

	flyCam.setMoveSpeed(300f);
	cam.setLocation(new Vector3f(251.82283f, 120.149345f, 269.3746f));
	cam.setRotation(new Quaternion(-0.017585559f, 0.98510635f, -0.065076314f, -0.15818128f));

	//	makeCubes(5);

		createTerrain();

	AmbientLight ambient= new AmbientLight();
	ambient.setColor(ColorRGBA.White);
	rootNode.addLight(ambient);

	//	DirectionalLight sun= new DirectionalLight();
	//	sun.setDirection(new Vector3f(-2.9f, -1.2f, -5.8f));
	//	sun.setColor(ColorRGBA.White.clone().multLocal(1.7f));
	//	rootNode.addLight(sun);

	Spatial sky= SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", false);
	rootNode.attachChild(sky);

	//	Texture jswTexture= assetManager.loadTexture("Models/js10/js10_texture0.png");
	//	Material material= new Material();
	//	material.setTexture("NormalMap",jswTexture);

	Texture jswTexture= assetManager.loadTexture("Models/js10/js10_texture0.png");
	//	Material material= new Material();
	//	material.setColor("red", ColorRGBA.Red);

	//	Spatial mymodel= createWilly(100, 100, 100);

	//	mymodel.setMaterial(material);

	//	Material loadMaterial= assetManager.loadMaterial("Models/js10/js10.j3m");
	//	loadMaterial.setTexture("DiffuseMap", jswTexture);
	//	mymodel.setMaterial(loadMaterial);
	//	rootNode.attachChild(mymodel);

	DirectionalLight sun= new DirectionalLight();
	sun.setDirection(new Vector3f(1, 0, -2));
	sun.setColor(ColorRGBA.Yellow);
	rootNode.addLight(sun);
    }

    private Spatial createWilly(float x, float y, float z)
    {
	Spatial mymodel= assetManager.loadModel("Models/js10/js10.j3o"); // j3o
	mymodel.move(x, y, z);

	Material spherePlainMat= new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
	spherePlainMat.setFloat("Shininess", 0f); // [1,128]
	spherePlainMat.setBoolean("UseMaterialColors", true);
	spherePlainMat.setColor("Ambient", ColorRGBA.Black);
	spherePlainMat.setColor("Diffuse", ColorRGBA.Cyan);
	spherePlainMat.setColor("Specular", ColorRGBA.White);
	mymodel.setMaterial(spherePlainMat);
	return mymodel;
    }

    @Override
    /** This update loop controls the game and moves the cube. */
    public void simpleUpdate(float tpf)
    {
	//	SpriteEvent spriteEvent= spritesEventsQueue.poll();
	//
	//	if (spriteEvent != null)
	//	{
	//	    int bitMemoryAddress= spriteEvent.getBitMemoryAddress();
	//
	//	    Point point= new Point(spriteEvent.getX(), spriteEvent.getY());
	//	    Spatial geometry= createdSprites.get(point);
	//	    if (geometry == null)
	//	    {
	//		Vector3f loc= new Vector3f(spriteEvent.getX() + 50, 150 - spriteEvent.getY(), 60);
	//		ColorRGBA color= ColorRGBA.Green;
	//
	//		if (bitMemoryAddress == 454040)
	//		    color= ColorRGBA.Yellow;
	//
	//		if (bitMemoryAddress >= 320512 && bitMemoryAddress <= 321535)
	//		    color= ColorRGBA.Red;
	//
	//		Spatial myBox= createBox("Cube:" + bitMemoryAddress, loc, color);
	////		Spatial myBox= boxes.pop();
	////		myBox.setLocalTranslation(loc);
	//		rootNode.attachChild(myBox);
	//		createdSprites.put(point, myBox);
	//	    }
	//	}

	//		System.out.println("Distance: " + cam.getLocation());

	//	System.out.println("Distance: " + cam.getLocation().distance(scaredCube.getLocalTranslation()));
	//	System.out.println("Distance: " + cam.getRotation());
	// If camera is closer than 10 units to myCube...
	//	if (cam.getLocation().distance(scaredCube.getLocalTranslation()) < 10)
	//	{
	//	    // ... then move myCube away, in the direction that camera is facing.
	//	    scaredCube.move(cam.getDirection());
	//	}
    }

    static Queue<SpriteEvent> spritesEventsQueue= new ConcurrentLinkedQueue<>();

    /**
     * Start the jMonkeyEngine application
     */
    public static void main(final String[] args)
    {
	final Zx3dLauncher app= new Zx3dLauncher();

	java.awt.EventQueue.invokeLater(new Runnable()
	{
	    public void run()
	    {
		new JSpeccy(args).setVisible(true);
	    }
	});

	PixelView.pixelWritingListener= new PixelWritingListener()
	{
	    public void pixelWrittenAtPositionUsingBitAt(int x, int y, int bitMemoryAddress)
	    {
		//		 	    System.out.println("x:" + x + " y:" + y + " address:" + bitMemoryAddress);

		if (app.initialized)
		    if (bitMemoryAddress == 453968 || bitMemoryAddress == 454040 /*|| (bitMemoryAddress >= 320512 && bitMemoryAddress <= 321535 && (bitMemoryAddress % 8) == 0)*/)
			try
			{
			    Point point= new Point(x, y);
			    Spatial geometry= createdSprites.get(point);
			    if (geometry == null)
			    {
				Vector3f loc= new Vector3f(x + 50, 150 - y, 60);
				ColorRGBA color= ColorRGBA.Green;

				if (bitMemoryAddress == 454040)
				    color= ColorRGBA.Yellow;

				if (bitMemoryAddress >= 320512 && bitMemoryAddress <= 321535)
				    color= ColorRGBA.Red;

				Spatial myBox= app.createBox("Cube:" + bitMemoryAddress, loc, color);
				//				Spatial myBox= boxes.pop();
				//				myBox.setLocalTranslation(loc);
				app.rootNode.attachChild(myBox);
				createdSprites.put(point, myBox);
			    }

			}
			catch (Exception e)
			{
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
	    }
	};

	app.start();

    }
    
    public void start()
    {
        super.start();
        initialized= true;
    }

    private void createTerrain()
    {
	Texture heightMapImage= assetManager.loadTexture("Textures/Terrain/heightmap.png");

	terrainMat= new Material(assetManager, "Common/MatDefs/Terrain/TerrainLighting.j3md");
	terrainMat.setBoolean("useTriPlanarMapping", false);
	terrainMat.setBoolean("WardIso", true);
	terrainMat.setTexture("AlphaMap", assetManager.loadTexture("Textures/Terrain/alphamap.png"));

	Texture grass= assetManager.loadTexture("Textures/Terrain/grass.jpg");
	grass.setWrap(WrapMode.Repeat);
	terrainMat.setTexture("DiffuseMap", grass);
	terrainMat.setFloat("DiffuseMap_0_scale", 64);
	Texture normalMap0= assetManager.loadTexture("Textures/Terrain/grass_normal.jpg");
	normalMap0.setWrap(WrapMode.Repeat);
	terrainMat.setTexture("NormalMap", normalMap0);

	Texture rock= assetManager.loadTexture("Textures/Terrain/rock.png");
	rock.setWrap(WrapMode.Repeat);
	terrainMat.setTexture("DiffuseMap_1", rock);
	terrainMat.setFloat("DiffuseMap_1_scale", 64);
	Texture normalMap1= assetManager.loadTexture("Textures/Terrain/rock_normal.png");
	normalMap1.setWrap(WrapMode.Repeat);
	terrainMat.setTexture("NormalMap_1", normalMap1);

	Texture road= assetManager.loadTexture("Textures/Terrain/road.png");
	road.setWrap(WrapMode.Repeat);
	terrainMat.setTexture("DiffuseMap_2", road);
	terrainMat.setFloat("DiffuseMap_2_scale", 64);
	Texture normalMap2= assetManager.loadTexture("Textures/Terrain/road_normal.png");
	normalMap2.setWrap(WrapMode.Repeat);
	terrainMat.setTexture("NormalMap_2", normalMap2);

	AbstractHeightMap heightmap= null;
	try
	{
	    heightmap= new ImageBasedHeightMap(heightMapImage.getImage(), .1f);
	    heightmap.load();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	heightmap.smooth(0.9f, 3);

	terrain= new TerrainQuad("terrain", 65, 513, heightmap.getHeightMap());
	TerrainLodControl lodControl= new TerrainLodControl(terrain, getCamera());
	terrain.addControl(lodControl);
	terrain.setMaterial(terrainMat);

	rootNode.attachChild(terrain);
    }

}
