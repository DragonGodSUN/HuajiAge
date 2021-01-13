//这里使用了酒石酸的代码：animation/maid/default/arm/default.js
//详情查看：https://github.com/TartaricAcid/TouhouLittleMaid
Java.asJSONCompatible({
    animation: function (entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        armLeft = modelMap.get("armLeftStand");
        armRight = modelMap.get("armRightStand");

        f1 = 1.0 - Math.pow(1.0 - entity.getSwingProgress(), 4);
        f2 = Math.sin(f1 * Math.PI);
        f3 = Math.sin(entity.getSwingProgress() * Math.PI) * -0.7 * 0.75;

        if (armLeft != undefined) {
            armLeft.setRotateAngleX(-Math.cos(limbSwing * 0.67) * limbSwingAmount);
            armLeft.setRotateAngleY(0);
            armLeft.setRotateAngleZ(Math.cos(ageInTicks * 0.05) * 0.025 - 0.04);
            // 手部使用动画
            if (entity.getSwingProgress() > 0.0 && entity.isSwingLeftHand()) {
                armLeft.setRotateAngleX(armLeft.getRotateAngleX() - (f2 * 1.2 + f3));
                armLeft.setRotateAngleZ(armLeft.getRotateAngleZ() + Math.sin(entity.getSwingProgress() * Math.PI) * -0.4);
            }
        }

        if (armRight != undefined) {
            armRight.setRotateAngleX(Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount);
            armRight.setRotateAngleY(0);
            armRight.setRotateAngleZ(-Math.cos(ageInTicks * 0.05) * 0.025 + 0.04);
            // 手部使用动画
            if (entity.getSwingProgress() > 0.0 && !entity.isSwingLeftHand()) {
                armRight.setRotateAngleX(armRight.getRotateAngleX() - (f2 * 1.2 + f3));
                armRight.setRotateAngleZ(armRight.getRotateAngleZ() + Math.sin(entity.getSwingProgress() * Math.PI) * -0.4);
            }
        }
    }
})