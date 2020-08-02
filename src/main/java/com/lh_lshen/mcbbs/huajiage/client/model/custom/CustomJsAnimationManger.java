package com.lh_lshen.mcbbs.huajiage.client.model.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.IModelInfo;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nullable;
import javax.script.Bindings;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
@SideOnly(Side.CLIENT)
public class CustomJsAnimationManger {
    private static final Map<ResourceLocation, Object> CUSTOM_ANIMATION_MAP = Maps.newHashMap();

    @Nullable
    public static List<Object> getCustomAnimation(IModelInfo item) {
        List<Object> animations = Lists.newArrayList();
        if (item.getAnimation() != null && item.getAnimation().size() > 0) {
            for (ResourceLocation res : item.getAnimation()) {
                Object animation = CustomJsAnimationManger.getCustomAnimation(res);
                if (animation != null) {
                    animations.add(animation);
                }
            }
            return animations;
        }
        return null;
    }

    @Nullable
    public static Object getCustomAnimation(@Nullable ResourceLocation resourceLocation) {
        if (resourceLocation == null) {
            return null;
        }
        if (CUSTOM_ANIMATION_MAP.containsKey(resourceLocation)) {
            return CUSTOM_ANIMATION_MAP.get(resourceLocation);
        }
        InputStream stream = null;
        try {
            Bindings bindings = CommonProxy.NASHORN.createBindings();
            stream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
            Object scriptObject = CommonProxy.NASHORN.eval(IOUtils.toString(stream, StandardCharsets.UTF_8), bindings);
            CUSTOM_ANIMATION_MAP.put(resourceLocation, scriptObject);
            return scriptObject;
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(stream);
        }
        return null;
    }

    public static void clearAll() {
        CUSTOM_ANIMATION_MAP.clear();
    }
}

