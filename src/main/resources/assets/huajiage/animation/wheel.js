Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        wheel1 = modelMap.get("wheel1");
        wheel2 = modelMap.get("wheel2");
        wheel3 = modelMap.get("wheel3");
        wheel4 = modelMap.get("wheel4");
        wheel5 = modelMap.get("wheel5");
        wheel6 = modelMap.get("wheel6");
        if (wheel1 != undefined) {
            wheel1.setRotateAngleX(ageInTicks*player.getSpeed()*5000);
        }
        if (wheel2 != undefined) {
            wheel2.setRotateAngleX(ageInTicks*player.getSpeed()*5000);
        }
        if (wheel3 != undefined) {
            wheel3.setRotateAngleX(ageInTicks*player.getSpeed()*5000);
        }
        if (wheel4 != undefined) {
            wheel4.setRotateAngleX(ageInTicks*player.getSpeed()*5000);
        }
        if (wheel5 != undefined) {
            wheel5.setRotateAngleX(ageInTicks*player.getSpeed()*5000);
        }
        if (wheel6 != undefined) {
            wheel6.setRotateAngleX(ageInTicks*player.getSpeed()*5000);
        }
    }
})