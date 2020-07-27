package com.lh_lshen.mcbbs.huajiage.client.resources;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.pojo.CustomJsAnimationManger;
import com.lh_lshen.mcbbs.huajiage.client.model.pojo.CustomModelPOJO;
import com.lh_lshen.mcbbs.huajiage.client.model.pojo.EntityModelJson;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.CustomModelPack;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static net.minecraft.network.status.server.SPacketServerInfo.GSON;


public class CustomResourceLoader {
    public static final CustomStandModelResources MAID_MODEL = new CustomStandModelResources("stand_model.json", Lists.newArrayList(), Maps.newHashMap(), Maps.newHashMap(), Maps.newHashMap());

    private static final Logger LOGGER = HuajiAge.LOGGER;
    private static final Marker MARKER = MarkerManager.getMarker("ResourcesLoader");
    private static final String OLD_BEDROCK_VERSION = "1.10.0";

    private static IResourceManager manager = Minecraft.getMinecraft().getResourceManager();

    public static void reloadResources() {
        CustomJsAnimationManger.clearAll();
        MAID_MODEL.clearAll();
        // 重载数据
        loadStandModelPack();
    }

    private static void loadStandModelPack() {
        LOGGER.info(MARKER, "Stand Models Loading...");
        // 遍历所有的资源包，获取到模型文件
        for (String domain : manager.getResourceDomains()) {
            InputStream input = null;
            try {
                // 获取所有资源域下的指定文件
                ResourceLocation res = new ResourceLocation(domain, MAID_MODEL.getJsonFileName());
                input = manager.getResource(res).getInputStream();
                // 将其转换为 pojo 对象
                // 这个 pojo 是二次修饰的过的对象，所以一部分数据异常已经进行了处理或者抛出
                CustomModelPack<StandModelInfo> pack = GSON.fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), new TypeToken<CustomModelPack<StandModelInfo>>() {
                }.getType());
                pack.decorate();
                for (StandModelInfo maidModelItem : pack.getModelList()) {
                    // 尝试加载模型
                    EntityModelJson modelJson = loadModel(maidModelItem.getModel());
                    // 加载动画
                    @Nullable List<Object> animations = CustomJsAnimationManger.getCustomAnimation(maidModelItem);
                    if (modelJson != null) {
                            putMaidModelData(maidModelItem, modelJson, animations);
                        // 打印日志
                        LOGGER.info(MARKER, "Loaded model: {}", maidModelItem.getModel());
                    }
                }
                MAID_MODEL.addPack(pack);
            } catch (IOException ignore) {
                // 忽略错误，因为资源域很多
            } catch (JsonSyntaxException e) {
                LOGGER.warn(MARKER, "Fail to parse model pack in domain {}", domain);
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(input);
            }
        }
        LOGGER.info(MARKER, "Touhou little maid mod's model is loaded");
    }

    private static void putMaidModelData(StandModelInfo model, EntityModelJson modelJson, List<Object> animations) {
        String id = model.getModelId().toString();
        // 如果加载的模型不为空
        MAID_MODEL.putModel(id, modelJson);
        MAID_MODEL.putInfo(id, model);
        if (animations != null && animations.size() > 0) {
            MAID_MODEL.putAnimation(id, animations);
        }
    }

    @Nullable
    public static EntityModelJson loadModel(ResourceLocation modelLocation) {
        InputStream input = null;
        try {
            input = manager.getResource(modelLocation).getInputStream();
            CustomModelPOJO pojo = CommonProxy.GSON.fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), CustomModelPOJO.class);

            // 先判断是不是 1.10.0 版本基岩版模型文件
            if (!pojo.getFormatVersion().equals(OLD_BEDROCK_VERSION)) {
                LOGGER.warn(MARKER, "{} model version is not 1.10.0", modelLocation);
                // TODO: 2019/7/26 添加对高版本基岩版模型的兼容
                return null;
            }

            // 如果 model 字段不为空
            if (pojo.getGeometryModel() != null) {
                return new EntityModelJson(pojo);
            } else {
                // 否则日志给出提示
                LOGGER.warn(MARKER, "{} model file don't have model field", modelLocation);
            }
        } catch (IOException ioe) {
            // 可能用来判定错误，打印下
            LOGGER.warn(MARKER, "Failed to load model: {}", modelLocation);
            ioe.printStackTrace();
        } finally {
            // 关闭输入流
            IOUtils.closeQuietly(input);
        }
        // 如果前面出了错，返回 Null
        return null;
    }


    }