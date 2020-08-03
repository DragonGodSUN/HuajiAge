package com.lh_lshen.mcbbs.huajiage.client.model.custom;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.script.Invocable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
@SideOnly(Side.CLIENT)
public class ModelStandJson extends ModelStandBase {
    public final AxisAlignedBB renderBoundingBox;
    private EntityStandWrapper entityStandWrapper;
    private List<Float> positions = Lists.newArrayList();
    private List<Float> rotations = Lists.newArrayList();
    private List<Object> animations = Lists.newArrayList();
    /**
     * 存储 ModelRender 子模型的 HashMap
     */
    private HashMap<String, ModelRendererWrapper> modelMap = new HashMap<>();
    /**
     * 存储 Bones 的 HashMap，主要是给后面寻找父骨骼进行坐标转换用的
     */
    private HashMap<String, BonesItem> indexBones = new HashMap<>();
    /**
     * 哪些模型需要渲染。加载进父骨骼的子骨骼是不需要渲染的
     */
    private List<ModelRenderer> shouldRender = new LinkedList<>();

    public ModelStandJson(CustomModelPOJO pojo) {
        initTranslate(0,0,0);
        initRotations(0,0,0,0);
        this.entityStandWrapper = new EntityStandWrapper();

        // 材质的长度、宽度
        textureWidth = pojo.getGeometryModel().getTexturewidth();
        textureHeight = pojo.getGeometryModel().getTextureheight();

        List<Float> offset = pojo.getGeometryModel().getVisibleBoundsOffset();
        float offsetX = offset.get(0);
        float offsetY = offset.get(1);
        float offsetZ = offset.get(2);
        float width = pojo.getGeometryModel().getVisibleBoundsWidth() / 2.0f;
        float height = pojo.getGeometryModel().getVisibleBoundsHeight() / 2.0f;
        renderBoundingBox = new AxisAlignedBB(offsetX - width, offsetY - height, offsetZ - width, offsetX + width, offsetY + height, offsetZ + width);

        // 往 indexBones 里面注入数据，为后续坐标转换做参考
        for (BonesItem bones : pojo.getGeometryModel().getBones()) {
            // 塞索引，这是给后面坐标转换用的
            indexBones.put(bones.getName(), bones);
            // 塞入新建的空 ModelRenderer 实例
            // 因为后面添加 parent 需要，所以先塞空对象，然后二次遍历再进行数据存储
            modelMap.put(bones.getName(), new ModelRendererWrapper(new ModelRenderer(this)));
        }

        // 开始往 ModelRenderer 实例里面塞数据
        for (BonesItem bones : pojo.getGeometryModel().getBones()) {
            // 骨骼名称，注意因为后面动画的需要，头部、手部、腿部等骨骼命名必须是固定死的
            String name = bones.getName();
            // 旋转点，可能为空
            @Nullable List<Float> rotation = bones.getRotation();
            // 父骨骼的名称，可能为空
            @Nullable String parent = bones.getParent();
            // 塞进 HashMap 里面的模型对象
            ModelRenderer model = modelMap.get(name).getModelRenderer();

            // 镜像参数
            model.mirror = bones.isMirror();

            // 旋转点
            model.setRotationPoint(convertPivot(bones, 0), convertPivot(bones, 1), convertPivot(bones, 2));

            // Nullable 检查，设置旋转角度
            if (rotation != null) {
                setRotationAngle(model, convertRotation(rotation.get(0)), convertRotation(rotation.get(1)), convertRotation(rotation.get(2)));
            }

            // Null 检查，进行父骨骼绑定
            if (parent != null) {
                modelMap.get(parent).getModelRenderer().addChild(model);
            } else {
                // 没有父骨骼的模型才进行渲染
                //viewFirst模型不渲染
                if(!name.equals("viewFirst"))
                {
                    shouldRender.add(model);
                }
            }

            // 我的天，Cubes 还能为空……
            if (bones.getCubes() == null) {
                continue;
            }

            // 塞入 Cube List
            for (CubesItem cubes : bones.getCubes()) {
                List<Float> uv = cubes.getUv();
                List<Float> size = cubes.getSize();
                boolean mirror = cubes.isMirror();
                float inflate = cubes.getInflate();

                model.cubeList.add(new ModelBoxFloat(model, uv.get(0), uv.get(1),
                        convertOrigin(bones, cubes, 0), convertOrigin(bones, cubes, 1), convertOrigin(bones, cubes, 2),
                        size.get(0), size.get(1), size.get(2), inflate, mirror));
            }
        }
    }

    public ModelStandJson(ModelStandJson json){
        initTranslate(0,0,0);
        initRotations(0,0,0,0);
        this.setModelID(json.getModelID());

        textureWidth = json.textureWidth;
        textureHeight = json.textureHeight;

        this.renderBoundingBox =json.renderBoundingBox;
        this.entityStandWrapper = json.createEntityStandWrapper();
//        try {
//            Field field1 = EntityModelJson.class.getDeclaredField("modelMap");
//            field1.setAccessible(true);
//            Object value1 = field1.get(json);
//            this.modelMap = (HashMap<String, ModelRendererWrapper>) value1;
//            Field field2 = EntityModelJson.class.getDeclaredField("indexBones");
//            field2.setAccessible(true);
//            Object value2 = field2.get(json);
//            this.indexBones = (HashMap<String, BonesItem>) value2;
//            Field field3 = EntityModelJson.class.getDeclaredField("shouldRender");
//            field3.setAccessible(true);
//            Object value3 = field3.get(json);
//            this.shouldRender = (List<ModelRenderer>) value3;
//            Field field4 = EntityModelJson.class.getDeclaredField("animations");
//            field4.setAccessible(true);
//            Object value4 = field4.get(json);
//            this.animations = (List<Object>) value4;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        this.positions = json.getPositions();
        this.rotations = json.getRotations();
        this.modelMap = json.getModelMap();
        this.indexBones = json.getIndexBones();
        this.shouldRender = json.getShouldRender();

    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.translate(0, MathHelper.sin(ageInTicks/20)*0.1,0);
        for (ModelRenderer model : shouldRender) {
            model.render(scale);
        }
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        if (animations == null) {
            return;
        }
        Invocable invocable = (Invocable) CommonProxy.NASHORN;
        if (entityIn instanceof EntityPlayer) {
            entityStandWrapper.setData((EntityPlayer)entityIn, swingProgress, isRiding);
            String modelId = this.getModelID();
            if(modelId!=null){
                try {
                    for (Object animation : animations) {
                        invocable.invokeMethod(animation, "animation",
                                entityStandWrapper, limbSwing * ConfigHuaji.Stands.maidLimbSwing , limbSwingAmount * ConfigHuaji.Stands.maidLimbSwingAmount , ageInTicks, netHeadYaw, headPitch, scaleFactor, modelMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    CustomResourceLoader.STAND_MODEL.removeAnimation(modelId);
                }
            }
        }
    }

    private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }



    public boolean hasArmPositioningModel(EnumHandSide side) {
        ModelRendererWrapper arm = (side == EnumHandSide.LEFT ? modelMap.get("armLeftPositioningBone") : modelMap.get("armRightPositioningBone"));
        return arm != null;
    }

    public void postRenderArmPositioningModel(float scale, EnumHandSide side) {
        ModelRenderer arm = (side == EnumHandSide.LEFT ? modelMap.get("armLeftPositioningBone").getModelRenderer() : modelMap.get("armRightPositioningBone").getModelRenderer());
        if (arm != null) {
            arm.postRender(scale);
        }
    }

    public void postRenderArm(float scale, EnumHandSide side) {
        ModelRenderer arm = (side == EnumHandSide.LEFT ? modelMap.get("armLeft").getModelRenderer() : modelMap.get("armRight").getModelRenderer());
        if (arm != null) {
            arm.postRender(scale);
        }
    }

    public boolean hasLeftArm() {
        return modelMap.containsKey("armLeft");
    }

    public boolean hasRightArm() {
        return modelMap.containsKey("armRight");
    }

    public boolean hasHead() {
        return modelMap.containsKey("head");
    }

    public void postRenderCustomHead(float scale) {
        ModelRenderer customHead = modelMap.get("head").getModelRenderer();
        if (customHead != null) {
            customHead.postRender(scale);
        }
    }

    public void setAnimations(List<Object> animations) {
        this.animations = animations;
    }

    /**
     * 基岩版的旋转中心计算方式和 Java 版不太一样，需要进行转换
     * <p>
     * 如果有父模型
     * <li>x，z 方向：本模型坐标 - 父模型坐标
     * <li>y 方向：父模型坐标 - 本模型坐标
     * <p>
     * 如果没有父模型
     * <li>x，z 方向不变
     * <li>y 方向：24 - 本模型坐标
     *
     * @param index 是 xyz 的哪一个，x 是 0，y 是 1，z 是 2
     */
    private float convertPivot(BonesItem bones, int index) {
        if (bones.getParent() != null) {
            if (index == 1) {
                return indexBones.get(bones.getParent()).getPivot().get(index) - bones.getPivot().get(index);
            } else {
                return bones.getPivot().get(index) - indexBones.get(bones.getParent()).getPivot().get(index);
            }
        } else {
            if (index == 1) {
                return 24 - bones.getPivot().get(index);
            } else {
                return bones.getPivot().get(index);
            }
        }
    }

    /**
     * 基岩版和 Java 版本的方块起始坐标也不一致，Java 是相对坐标，而且 y 值方向不一致。
     * 基岩版是绝对坐标，而且 y 方向朝上。
     * <li>如果是 x，z 轴，那么只需要方块起始坐标减去旋转点坐标
     * <li>如果是 y 轴，旋转点坐标减去方块起始坐标，再减去方块的 y 长度
     *
     * @param index 是 xyz 的哪一个，x 是 0，y 是 1，z 是 2
     */
    private float convertOrigin(BonesItem bones, CubesItem cubes, int index) {
        if (index == 1) {
            return bones.getPivot().get(index) - cubes.getOrigin().get(index) - cubes.getSize().get(index);
        } else {
            return cubes.getOrigin().get(index) - bones.getPivot().get(index);
        }
    }

    /**
     * 基岩版用的是度，Java 版用的是弧度，这个转换很简单
     */
    private float convertRotation(float degree) {
        return (float) (degree * Math.PI / 180);
    }

    //ModelStandBase渲染的方法
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
                                  float rotatePitch, float scale, Entity entity, float power, float speed) {
        setRotationAngles(limbSwing, limbSwingAmount, rotateFloat, rotateYaw, rotatePitch, scale, entity);
    }

    //进行手部渲染前对其进行的调整
    @Override
    public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {

    }

    //第一人称手部模型的渲染
    @Override
    public void renderFirst(float x, float y, float z, float scale, float alpha) {
        ModelRendererWrapper wrapper = modelMap.get("viewFirst");
        if(wrapper!=null){
           GlStateManager.color(1,1,1,scale);
           GlStateManager.translate(x,y,z);
           ModelRenderer view = wrapper.getModelRenderer();
           view.render(scale);
           GlStateManager.color(1,1,1,1);
        }
    }

    //附加特效
    @Override
    public void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    }

    //高阶段的额外特效
    @Override
    public void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

    }

    //设置替身位置与旋转角
    @Override
    public void setPosition() {
        if (!rotations.isEmpty()&&rotations.size()>=4) {
            GlStateManager.rotate(rotations.get(0),rotations.get(1),rotations.get(2),rotations.get(3));
        }
        if (!positions.isEmpty()&&positions.size()>=3) {
            GlStateManager.translate(positions.get(0),positions.get(1),positions.get(2));
        }
    }

    //初始化transfer数据
    public void initTranslate(float x, float y, float z){
        positions.add(x);
        positions.add(y);
        positions.add(z);
    }

    //初始化rotation数据
    public void initRotations(float degree, float y, float p, float w){
        rotations.add(degree);
        rotations.add(y);
        rotations.add(p);
        rotations.add(w);
    }

    //设置位置
    public void setPositions(float x, float y, float z){
        if (!positions.isEmpty()&&positions.size()>=3) {
            positions.set(0,x);
            positions.set(1,y);
            positions.set(2,z);
        }
    }

    //设置旋转角
    public void setRotations(float degree, float y, float p, float w) {
        if (!rotations.isEmpty()&&rotations.size()>=4) {
            rotations.set(0,degree);
            rotations.set(1,y);
            rotations.set(2,p);
            rotations.set(3,w);
        }
    }

    @Override
    public ModelStandBase copy() {
        ModelStandBase model = HAModelFactory.getModel(this.getModelID());
        if(model instanceof ModelStandJson){
            return new ModelStandJson((ModelStandJson) model);
        }
        return new ModelTheWorld();
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return renderBoundingBox;
    }

    public EntityStandWrapper createEntityStandWrapper() {
        return new EntityStandWrapper();
    }

    public List<Float> getPositions() {
        return positions;
    }

    public List<Float> getRotations() {
        return rotations;
    }

    public List<Object> getAnimations() {
        return animations;
    }

    public HashMap<String, ModelRendererWrapper> getModelMap() {
        return modelMap;
    }

    public HashMap<String, BonesItem> getIndexBones() {
        return indexBones;
    }

    public List<ModelRenderer> getShouldRender() {
        return shouldRender;
    }
}
