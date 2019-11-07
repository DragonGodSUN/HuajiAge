package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
	public static Item hopeElement=new ItemHopeElement();
	public static Item hopeFlower=new ItemHopeFlower();
	public static Item infiniteCharm=new ItemInfiniteCharm();
	public static Item dioBread=new ItemDioBread();
	public static Item multiKnife=new ItemMultiKnife();
	public static Item roadRoller=new ItemRoadRoller();
	public static Item orgaFlag=new ItemOrgaFlag();
	public static Item lordCore=new ItemLordCore();
	public static Item etherC=new ItemEtherCircumfluxBoard();
	public static Item huajiStarPoly=new ItemHuajiStarPoly();
	public static ItemFood eggRice = new ItemEggRice();
	public static ItemFood eggRiceU = new ItemEggRiceU();
    public static ItemArmor huajiHelmet = new ItemHuajiArmor.Helmet();
    public static ItemArmor huajiChestplate = new ItemHuajiArmor.Chestplate();
    public static ItemArmor huajiLeggings = new ItemHuajiArmor.Leggings();
    public static ItemArmor huajiBoots = new ItemHuajiArmor.Boots();
    public static ItemArmor blanceHelmet = new BlancedHelmet();
    public static ItemArmor orgaHair = new OrgaHair();
    public static ItemArmor orgaChestplate = new OrgaBase.Chestplate();
    public static ItemArmor orgaLeggings = new OrgaBase.Leggings();
    public static ItemArmor orgaBoots = new OrgaBase.Boots();
    
	
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
        register(hopeElement,"hopeElement","hope_element");
        register(hopeFlower,"hopeFlower","hope_flower");
        register(infiniteCharm,"infiniteCharm","infinite_charm");
        register(dioBread,"dioBread","dio_bread");
        register(multiKnife,"multiKnife","multi_knife");
        register(roadRoller,"roadRoller","road_roller");
        register(orgaFlag,"orgaFlag","orga_flag");
        register(etherC,"etherCircumfluxBoard","ether_circumflux_board");
        register(lordCore,"lordCore","lord_core");
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
        registerRender(arrowRequiem); 
        registerRender(hopeElement);
        registerRender(hopeFlower); 
        registerRender(infiniteCharm); 
        registerRender(dioBread);
        registerRender(multiKnife);
        registerRender(roadRoller);
        registerRender(orgaFlag);
        registerRender(etherC);
        registerRender(lordCore);
        registerRender(huajiStarPoly);
    }
    public void init(FMLInitializationEvent event)
    {
    	 OreDictionary.registerOre("huaji", huaji);
    }
    private static void register(Item item ,String name1,String name2)
    {
    	item.setUnlocalizedName(name1);
    	item.setRegistryName(name2);
    	ForgeRegistries.ITEMS.register(item);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }

}
