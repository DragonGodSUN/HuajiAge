package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.item.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemLoader {
	public static Item secondFoil = new ItemSecondFoil();
	public static Item secondFoilEntity = new ItemSecondFoilEntity();
	public static Item expendedView = new ItemExpendedView();
	public static Item huaji = new ItemHuaji();
	public static Item huajiSword = new ItemHuajiSword();
	public static Item starSword = new ItemHuajiStarSword();
	public static Item huajiFragmnet = new ItemHuajiFragment();
	public static Item huajiStar = new ItemHuajiStar();
	public static Item huajiStarSky = new ItemHuajiStarSky();
	public static Item huajiStarUniverse = new ItemHuajiStarUniverse();
	public static Item huajiIngot = new ItemHuajiIngot(); 
	public static Item ether = new ItemEther();
	public static Item heroBow = new ItemHeroBow();
	public static Item redstoneDruse = new ItemRedstoneDruse();
	public static Item netronStarFragment = new ItemNeutronStarFragment();
	public static Item latiaoSword = new ItemHuajiLatiaoSword();
	public static Item loottest = new ItemLootTableTest();
	public static Item exglutenbur = new ItemExglutenbur();
	public static Item flashFlour = new ItemFlashFlour();
	public static Item rawGluten = new ItemRawGluten();
	public static Item bakingGluten= new ItemBakingGluten();
	public static Item arrowRequiem= new ItemArrowRequiem();
	public static Item orgaRequiem=new ItemOrgaRequiem();
	public static Item orgaRunning=new ItemOrgaRunning();
	public static Item orgaHairKnife=new ItemOrgaHairKnife();
	public static Item hopeElement=new ItemHopeElement();
	public static Item hopeFlower=new ItemHopeFlower();
	public static Item waveCrystal=new ItemWaveCrystal();
	public static Item waveKnife=new ItemWaveKnife();
	public static Item infiniteCharm=new ItemInfiniteCharm();
	public static Item dioBread=new ItemDioBread();
	public static Item multiKnife=new ItemMultiKnife();
	public static Item roadRoller=new ItemRoadRoller();
	public static Item blackCar=new ItemBlackCar();
	public static Item orgaFlag=new ItemOrgaFlag();
	public static Item lordCore=new ItemLordCore();
	public static Item lordKey=new ItemLordKey();
	public static Item etherC=new ItemEtherCircumfluxBoard();
	public static Item huajiStarPoly=new ItemHuajiStarPoly();
	public static Item tarot=new ItemTarot();
	public static Item disc=new ItemDiscStand();
	public static Item camera=new ItemExpensiveCamera();
	public static Item singularity=new ItemSingularity();
	public static Item arrowStand=new ItemArrowStand();
	public static Item killerQueenTrigger=new ItemKillerQueenTrigger();
    public static Item yinYangBall =new ItemYinYangBall();
    public static Item vehicleKey =new ItemVehicleKey();
    public static Item vehiclePack =new ItemVehiclePack();
    public static Item discMind =new ItemDiscMind();
    public static Item discMemory =new ItemDiscMemory();
    public static Item discCommand =new ItemDiscCommand();
	public static ItemFood eggRice = new ItemEggRice();
	public static ItemFood eggRiceU = new ItemEggRiceU();
	public static ItemFood reoCherry = new ItemReoCherry();
    public static ItemArmor huajiHelmet = new ItemHuajiArmor.Helmet();
    public static ItemArmor huajiChestplate = new ItemHuajiArmor.Chestplate();
    public static ItemArmor huajiLeggings = new ItemHuajiArmor.Leggings();
    public static ItemArmor huajiBoots = new ItemHuajiArmor.Boots();
    public static ItemArmor blanceHelmet = new ItemBlancedHelmet();
    public static ItemArmor orgaHair = new ItemOrgaHair();
    public static ItemArmor orgaChestplate = new ItemOrgaArmorBase.Chestplate();
    public static ItemArmor orgaLeggings = new ItemOrgaArmorBase.Leggings();
    public static ItemArmor orgaBoots = new ItemOrgaArmorBase.Boots();
    
	
    public ItemLoader(FMLPreInitializationEvent event)
    {
        register(secondFoil,"secondFoil","second_foil");
        register(secondFoilEntity,"secondFoilEntity","entity_second");
        register(expendedView,"expendedView","expended_view");
        register(huaji,"huaji","huaji");
        register(huajiFragmnet,"huajiFragment","huaji_fragment");
        register(netronStarFragment,"neutronStarFragment","neutron_star_fragment");
        register(huajiStar,"huajiStar","huaji_star");
        register(huajiStarSky,"huajiStarSky","huaji_star_sky");
        register(huajiStarUniverse,"huajiStarUniverse","huaji_star_universe");
        register(huajiStarPoly,"huajiStarPoly","huaji_star_poly");
        register(eggRice,"eggRice","egg_rice");
        register(eggRiceU,"eggRiceU","egg_rice_u");
        register(huajiIngot,"huajiIngot","huaji_ingot");
        register(huajiSword,"huajiSword","huaji_sword");
        register(starSword,"starSwordH","huaji_star_sword");
        register(huajiHelmet,"huajiHelmet","huaji_helmet");
        register(huajiChestplate,"huajiChestPlate","huaji_chestplate");
        register(huajiLeggings,"huajiLeggings","huaji_leggings");
        register(huajiBoots,"huajiBoots","huaji_boots");
        register(blanceHelmet,"blanceHelmet","blance_helmet");
        register(latiaoSword,"huajiLatiaoSword","huaji_latiao_sword");
        register(ether,"ether","ether");
        register(redstoneDruse,"redstoneDruse","redstone_druse");
        register(heroBow,"heroBow","hero_bow");
        register(loottest,"loot","loot");
        register(exglutenbur,"exglutenbur","exglutenbur");
        register(flashFlour,"flashFlour","flash_flour");
        register(rawGluten,"rawGluten","raw_gluten");
        register(bakingGluten,"bakingGluten","baking_gluten");
        register(orgaHair,"orgaHair","orga_hair");
        register(orgaChestplate,"orgaChestplate","orga_chestplate");
        register(orgaLeggings,"orgaLeggings","orga_leggings");
        register(orgaBoots,"orgaBoots","orga_boots");
        register(arrowRequiem,"arrowRequiem","arrow_requiem");
        register(orgaRequiem,"orgaRequiem","orga_requiem");
        register(orgaRunning,"orgaRunning","orga_running");
        register(orgaHairKnife,"orgaHairKnife","orga_hair_knife");
        register(hopeElement,"hopeElement","hope_element");
        register(hopeFlower,"hopeFlower","hope_flower");
        register(waveCrystal,"waveCrystal","wave_crystal");
        register(waveKnife,"waveKnife","wave_knife");
        register(infiniteCharm,"infiniteCharm","infinite_charm");
        register(dioBread,"dioBread","dio_bread");
        register(multiKnife,"multiKnife","multi_knife");
        register(roadRoller,"roadRoller","road_roller");
        register(blackCar,"blackCar","black_car");
        register(orgaFlag,"orgaFlag","orga_flag");
        register(etherC,"etherCircumfluxBoard","ether_circumflux_board");
        register(lordCore,"lordCore","lord_core");
        register(lordKey,"lordKey","lord_key");
        register(tarot,"tarot","tarot");
        register(disc,"disc","disc");
        register(camera,"expensiveCamera","expensive_camera");
        register(singularity,"singularity","singularity");
        register(arrowStand,"arrowStand","arrow_stand");
        register(killerQueenTrigger,"killerQueenTrigger","killer_queen_trigger");
        register(vehicleKey,"vehicleKey","vehicle_key");
        register(vehiclePack,"vehiclePack","vehicle_pack");
        register(reoCherry,"reoCherry","reo_cherry");
        register(discMind,"discMind","disc_mind");
        register(discMemory,"discMemory","disc_memory");
        register(discCommand,"discCommand","disc_command");
        if(CommonProxy.ModsLoader.isTouhouMaidLoaded()){
        register(yinYangBall,"yinYangBall","yin_yang_ball");
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(secondFoil);
        registerRender(secondFoilEntity);
        registerRender(expendedView);
        registerRender(huaji);
        registerRender(huajiFragmnet);
        registerRender(huajiStar);
        registerRender(huajiStarSky);
        registerRender(huajiStarUniverse);
        registerRender(eggRice);
        registerRender(eggRiceU);
        registerRender(huajiIngot);
        registerRender(huajiSword);
        registerRender(starSword);
        registerRender(netronStarFragment);
        registerRender(huajiHelmet);
        registerRender(huajiChestplate);
        registerRender(huajiLeggings);
        registerRender(huajiBoots);        
        registerRender(blanceHelmet);  
        registerRender(latiaoSword); 
        registerRender(ether); 
        registerRender(redstoneDruse); 
        registerRender(heroBow); 
        registerRender(loottest); 
        registerRender(exglutenbur); 
        registerRender(flashFlour); 
        registerRender(rawGluten); 
        registerRender(bakingGluten); 
        registerRender(orgaHair); 
        registerRender(orgaChestplate); 
        registerRender(orgaLeggings); 
        registerRender(orgaBoots); 
        registerRender(orgaRequiem); 
        registerRender(orgaRunning); 
        registerRender(orgaHairKnife); 
        registerRender(arrowRequiem); 
        registerRender(hopeElement);
        registerRender(hopeFlower); 
        registerRender(waveCrystal); 
        registerRender(waveKnife); 
        registerRender(infiniteCharm); 
        registerRender(dioBread);
        registerRender(multiKnife);
        registerRender(roadRoller);
        registerRender(blackCar);
        registerRender(orgaFlag);
        registerRender(etherC);
        registerRender(lordCore);
        registerRender(lordKey);
        registerRender(huajiStarPoly);
        registerRender(tarot);
        registerRender(camera);
        registerRender(singularity);
        registerRender(arrowStand);
        registerRender(killerQueenTrigger);
        if(CommonProxy.ModsLoader.isTouhouMaidLoaded()){
        registerRender(yinYangBall);
        }
        registerRender(reoCherry);
        registerRender(vehicleKey);
        registerRender(vehiclePack);
        registerRender(discMind);
        registerRender(discMemory);
        registerRender(discCommand);
//        registerRender(disc);
//        for(StandBase stand : StandLoader.STAND_LIST) {
//        	String name = disc.getRegistryName()+"_"+stand.getName();
//        	ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
//    	    ModelLoader.setCustomModelResourceLocation(disc, StandLoader.STAND_LIST.indexOf(stand)+1, model);
//        }
//        	ModelLoader.setCustomModelResourceLocation(disc,0, new ModelResourceLocation(disc.getRegistryName()+"_"+"null", "inventory"));
    }
    public void init(FMLInitializationEvent event)
    {
    	 OreDictionary.registerOre("huaji", huaji);
    }
    private static void register(Item item ,String name1,String name2)
    {
    	item.setTranslationKey(HuajiAge.MODID+"."+name1);
    	item.setRegistryName(name2);
    	ForgeRegistries.ITEMS.register(item);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item,int meta)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }


}
