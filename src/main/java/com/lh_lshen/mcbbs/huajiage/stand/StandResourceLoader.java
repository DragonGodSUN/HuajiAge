package com.lh_lshen.mcbbs.huajiage.stand;

import com.github.tartaricacid.touhoulittlemaid.proxy.CommonProxy;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustomInfo;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandStateInfo;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Objects;

import static net.minecraft.network.status.server.SPacketServerInfo.GSON;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public class StandResourceLoader {

    public static final Map<String, StandCustomInfo> CUSTOM_STAND_CLIENT = Maps.newHashMap();
    public static final Map<String, StandCustomInfo> CUSTOM_STAND_SERVER = Maps.newHashMap();

    public static final Map<String, StandStateInfo> CUSTOM_STATE_CLIENT = Maps.newHashMap();
    public static final Map<String, StandStateInfo> CUSTOM_STATE_SERVER = Maps.newHashMap();

    private static final Path CONFIG_FOLDER = Paths.get("config", HuajiAge.MODID, "custom_stand");
    private static final Path CONFIG_STATE_FOLDER = Paths.get("config", HuajiAge.MODID, "custom_stand/states");
    private static final String ACCEPTED_STAND_SUFFIX = ".json";
    private static final String ACCEPTED_STATE_SUFFIX = ".js";
    private static final ResourceLocation DISC_DEFAULT = new ResourceLocation(HuajiAge.MODID, "textures/items/disc_null.png");

    public static void loadCustomStand(){
        CUSTOM_STAND_SERVER.clear();
        CUSTOM_STATE_SERVER.clear();
        HuajiAge.LOGGER.info("Loaded Stand:----------------------------------");
        checkStandFolder();
        loadStand(CONFIG_FOLDER,ACCEPTED_STAND_SUFFIX);
        loadStandStates(CONFIG_STATE_FOLDER,ACCEPTED_STATE_SUFFIX);

        HuajiAge.LOGGER.info("Loaded Stand Size: {} ", CUSTOM_STAND_SERVER.size());
        HuajiAge.LOGGER.info("Loaded Stand State Size: {} ", CUSTOM_STATE_SERVER.size());
        for(String stand :CUSTOM_STAND_SERVER.keySet()){
            HuajiAge.LOGGER.info("Loaded Stand: {} ", stand);
        }
        for(String state :CUSTOM_STATE_SERVER.keySet()){
            HuajiAge.LOGGER.info("Loaded Stand: {} ", state);
        }

    }

    private static void checkStandFolder() {
        if (!Files.isDirectory(CONFIG_STATE_FOLDER)) {
            try {
                Files.createDirectories(CONFIG_STATE_FOLDER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!Files.isDirectory(CONFIG_FOLDER)) {
            try {
                Files.createDirectories(CONFIG_FOLDER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadStand(Path path, String suffix) {
        File[] files = path.toFile().listFiles((dir, name) -> name.endsWith(suffix));
        if (files == null || files.length < 1) {
            return;
        }
        HuajiAge.LOGGER.info("files: {}", files.length);
        for (File file : files) {
            try {
                StandCustomInfo info = loadStand(file);
                if (info != null) {
                    if (CUSTOM_STAND_SERVER.containsKey(info.getStand())) {
                        HuajiAge.LOGGER.warn("Have a stand of the same id: {}", info.getStand());
                    } else {
                        CUSTOM_STAND_SERVER.put(info.getStand(), info);
                    }
                }
            } catch (NullPointerException e) {
                HuajiAge.LOGGER.error("Exception while loading custom stands in {}:", file);
                HuajiAge.LOGGER.catching(e);
            }
        }
    }

    public static void loadStandStates(Path path, String suffix){
        File[] files = path.toFile().listFiles((dir, name) -> name.endsWith(suffix));
        if (files == null || files.length < 1) {
            return;
        }
        for (File file : files) {
            try {
                StandStateInfo info = loadStates(file);
                if (info != null) {
                    if (CUSTOM_STATE_SERVER.containsKey(info.getStand())) {
                        HuajiAge.LOGGER.warn("Have a stand state of the same id: {}", info.getStand());
                    } else {
                        CUSTOM_STATE_SERVER.put(info.getStand(), info);
                    }
                }
            } catch (NullPointerException e) {
                HuajiAge.LOGGER.error("Exception while loading custom stands in {}:", file);
                HuajiAge.LOGGER.catching(e);
            }
        }
    }

    private static StandCustomInfo loadStand(File file) {
        try {
            InputStream input = Files.newInputStream(file.toPath(), StandardOpenOption.READ);
            StandCustomInfo info = GSON.fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<StandCustomInfo>(){}.getType());
            info.decorate();
            HuajiAge.LOGGER.info("Loaded Stand Info: {} ", info.getStand());
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static StandStateInfo loadStates(File file) {
        try {
            InputStream input = Files.newInputStream(file.toPath(), StandardOpenOption.READ);
            Bindings bindings = CommonProxy.NASHORN.createBindings();
            Object scriptObject = CommonProxy.NASHORN.eval(IOUtils.toString(input, StandardCharsets.UTF_8), bindings);
            HuajiAge.LOGGER.info("script-{}",scriptObject);
            return transObjectToEntry(scriptObject);
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static StandStateInfo transObjectToEntry(Object scriptObject) {
        Map<String, Object> scriptMaps = (Map<String, Object>) scriptObject;
        String id = (String) scriptMaps.get(stateArgs.STAND.getName());
        Objects.requireNonNull(id);
        String stateId ;
        String stateKey ;
        String modelId ;
        boolean hand = true;
        int stage = 0;

        if (scriptMaps.containsKey(stateArgs.STATE.getName())) {
            stateId = (String) scriptMaps.get(stateArgs.STATE.getName());
        }else{
            stateId = "default";
        }

        if (scriptMaps.containsKey(stateArgs.STATEKEY.getName())) {
            stateKey = (String) scriptMaps.get(stateArgs.STATEKEY.getName());
        }else {
            stateKey = String.format("stand.%s.%s.name", id.replace(":","."), stateId);
        }

        if (scriptMaps.containsKey(stateArgs.MODEL.getName())) {
            modelId = (String) scriptMaps.get(stateArgs.MODEL.getName())+"_"+stateId;
        }else{
            modelId = id+"_"+stateId;
        }

        if (scriptMaps.containsKey(stateArgs.HAND.getName())) {
            hand = (Boolean) scriptMaps.get(stateArgs.HAND.getName());
            HuajiAge.LOGGER.info("Loaded Stand hand: {} ", hand);

        }

        if (scriptMaps.containsKey(stateArgs.STAGE.getName())) {
            stage = (Integer) scriptMaps.get(stateArgs.STAGE.getName());
        }

        return new StandStateInfo(id,stateId,stateKey,modelId,scriptObject,hand,stage);
        }

    enum stateArgs {
        STAND("stand"),
        STATE("stateId"),
        STATEKEY("stateKey"),
        MODEL("modelId"),
        HAND("hand"),
        STAGE("stage")
        ;

        stateArgs(String name) {
            this.name = name;
        }
        private final String name;

        public String getName() {
            return name;
        }
    }
}

