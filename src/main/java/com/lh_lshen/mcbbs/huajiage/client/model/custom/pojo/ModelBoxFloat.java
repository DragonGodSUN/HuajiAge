package com.lh_lshen.mcbbs.huajiage.client.model.custom.pojo;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
@SideOnly(Side.CLIENT)
public class ModelBoxFloat extends ModelBox {
    public ModelBoxFloat(ModelRenderer renderer, float texU, float texV, float x, float y, float z, float dx, float dy, float dz, float delta, boolean mirror) {
        super(renderer, (int) texU, (int) texV, x, y, z, (int) dx, (int) dy, (int) dz, delta, mirror);
        this.posX2 = x + dx;
        this.posY2 = y + dy;
        this.posZ2 = z + dz;
        float f = x + dx;
        float f1 = y + dy;
        float f2 = z + dz;
        x = x - delta;
        y = y - delta;
        z = z - delta;
        f = f + delta;
        f1 = f1 + delta;
        f2 = f2 + delta;

        if (mirror) {
            float f3 = f;
            f = x;
            x = f3;
        }

        this.vertexPositions[0].vector3D = new Vec3d(x, y, z);
        this.vertexPositions[1].vector3D = new Vec3d(f, y, z);
        this.vertexPositions[2].vector3D = new Vec3d(f, f1, z);
        this.vertexPositions[3].vector3D = new Vec3d(x, f1, z);
        this.vertexPositions[4].vector3D = new Vec3d(x, y, f2);
        this.vertexPositions[5].vector3D = new Vec3d(f, y, f2);
        this.vertexPositions[6].vector3D = new Vec3d(f, f1, f2);
        this.vertexPositions[7].vector3D = new Vec3d(x, f1, f2);
        dx = (int) dx;
        dy = (int) dy;
        dz = (int) dz;
        this.quadList[0] = new TexturedQuadFloat(new PositionTextureVertex[]{vertexPositions[5], vertexPositions[1], vertexPositions[2], vertexPositions[6]}, texU + dz + dx, texV + dz, texU + dz + dx + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[1] = new TexturedQuadFloat(new PositionTextureVertex[]{vertexPositions[0], vertexPositions[4], vertexPositions[7], vertexPositions[3]}, texU, texV + dz, texU + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[2] = new TexturedQuadFloat(new PositionTextureVertex[]{vertexPositions[5], vertexPositions[4], vertexPositions[0], vertexPositions[1]}, texU + dz, texV, texU + dz + dx, texV + dz, renderer.textureWidth, renderer.textureHeight);
        this.quadList[3] = new TexturedQuadFloat(new PositionTextureVertex[]{vertexPositions[2], vertexPositions[3], vertexPositions[7], vertexPositions[6]}, texU + dz + dx, texV + dz, texU + dz + dx + dx, texV, renderer.textureWidth, renderer.textureHeight);
        this.quadList[4] = new TexturedQuadFloat(new PositionTextureVertex[]{vertexPositions[1], vertexPositions[0], vertexPositions[3], vertexPositions[2]}, texU + dz, texV + dz, texU + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[5] = new TexturedQuadFloat(new PositionTextureVertex[]{vertexPositions[4], vertexPositions[5], vertexPositions[6], vertexPositions[7]}, texU + dz + dx + dz, texV + dz, texU + dz + dx + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);

        if (mirror) {
            for (TexturedQuad texturedquad : this.quadList) {
                texturedquad.flipFace();
            }
        }
    }
}
