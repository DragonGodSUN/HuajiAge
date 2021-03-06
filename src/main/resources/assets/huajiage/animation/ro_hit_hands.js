var MathHelper = Java.type("com.lh_lshen.mcbbs.huajiage.util.HAMathHelper.CommonMath");

Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        handl1 = modelMap.get("handl1");
        handl2 = modelMap.get("handl2");
        handl3 = modelMap.get("handl3");
        handl4 = modelMap.get("handl4");
        handl5 = modelMap.get("handl5");
        handl6 = modelMap.get("handl6");

        handr1 = modelMap.get("handr1");
        handr2 = modelMap.get("handr2");
        handr3 = modelMap.get("handr3");
        handr4 = modelMap.get("handr4");
        handr5 = modelMap.get("handr5");
        handr6 = modelMap.get("handr6");

        if (handl1 != undefined) {
            handl1.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks) * 1.2 * player.getRotationFactorFirst() );
        }
        if (handl2 != undefined) {
            handl2.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 1.03) * 1.2 * player.getRotationFactorFirst() );
        }
        if (handl3 != undefined) {
            handl3.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 1.5) * 1.2 * player.getRotationFactorFirst() );
        }
        if (handl4 != undefined) {
            handl4.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 2.06) * 1.2 * player.getRotationFactorFirst() );
                }
        if (handl5 != undefined) {
            handl5.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 2.5) * 1.2 * player.getRotationFactorFirst() );
                }
        if (handl6 != undefined) {
            handl6.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 3.14) * 1.2 * player.getRotationFactorFirst() );
                }

         if (handr1 != undefined) {
            handr1.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks) * 1.2 * player.getRotationFactorFirst() );
                }
        if (handr2 != undefined) {
            handr2.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 1.03) * 1.2 * player.getRotationFactorFirst() );
        }
        if (handr3 != undefined) {
            handr3.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 1.5) * 1.2 * player.getRotationFactorFirst() );
        }
        if (handr4 != undefined) {
            handr4.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 2.06) * 1.2 * player.getRotationFactorFirst() );
                }
        if (handr5 != undefined) {
            handr5.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 2.5) * 1.2 * player.getRotationFactorFirst() );
                }
        if (handr6 != undefined) {
            handr6.setRotateAngleY(MathHelper.cos(1.5 * ageInTicks + 3.14) * 1.2 * player.getRotationFactorFirst() );
                }
    }
})