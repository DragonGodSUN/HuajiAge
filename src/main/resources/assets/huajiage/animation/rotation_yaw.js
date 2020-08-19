Java.asJSONCompatible({
    animation: function (player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, modelMap) {
        rotationYaw = modelMap.get("rotationYaw");
        if (rotationYaw != undefined) {
            rotationYaw.setRotateAngleY(netHeadYaw * 0.017453292);
        }
    }
})