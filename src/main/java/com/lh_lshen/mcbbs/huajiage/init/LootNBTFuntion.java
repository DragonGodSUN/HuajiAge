package com.lh_lshen.mcbbs.huajiage.init;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;

import java.util.Random;

public class LootNBTFuntion extends LootFunction {

    private final NBTTagList tags;
    private final int number;

    protected LootNBTFuntion(LootCondition[] conditionsIn, NBTTagList tagsIn, int number) {
        super(conditionsIn);
        this.tags = tagsIn;
        this.number = number;
    }

    @Override
    public ItemStack apply(ItemStack stack, Random rand, LootContext context) {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        int count = tags.tagCount();
        int number = rand.nextInt(count);
        NBTTagCompound tag = new NBTTagCompound();
        if (number < count) {
            tag = tags.getCompoundTagAt(number);
        } else {
            tag = tags.getCompoundTagAt(count - 1);
        }

        if (nbttagcompound == null) {
//	        	if(!tag.hasNoTags()) {
            nbttagcompound = tag.copy();
//	        	}
        } else {
            nbttagcompound.merge(tag);
        }
        stack.setTagCompound(nbttagcompound);

        return stack;
    }

    public static final class Serializer extends LootFunction.Serializer<LootNBTFuntion> {

        public Serializer() {
            super(new ResourceLocation(HuajiAge.MODID, "set_nbts_random"), LootNBTFuntion.class);
        }

        @Override
        public void serialize(JsonObject object, LootNBTFuntion functionObject, JsonSerializationContext context) {
            int count = functionObject.tags.tagCount();
            for (int i = 0; i < count; i++) {
                NBTTagCompound tag = functionObject.tags.getCompoundTagAt(i);
                object.addProperty("tag" + (i + 1), tag.toString());
            }
            object.addProperty("number", functionObject.number);
        }

        @Override
        public LootNBTFuntion deserialize(JsonObject object, JsonDeserializationContext context, LootCondition[] conditions) {
            try {

                NBTTagList list = new NBTTagList();

//	        		try{
                int size = JsonUtils.getInt(object, "number");
                if (size < 1) {
                    size = 1;
                }
                for (int i = 0; i < size; i++) {
                    NBTTagCompound nbtTag = JsonToNBT.getTagFromJson(JsonUtils.getString(object, "tag" + (i + 1)));
                    if (nbtTag.hasKey("StandId")){
                        String id = nbtTag.getString("StandId");
                        if (id.contains("-")){
                            nbtTag.setString("StandId",id.replace("-",":"));
                        }
                    }
                    list.appendTag(nbtTag);
                }
                return new LootNBTFuntion(conditions, list, size);
            } catch (NBTException nbtexception) {
                throw new JsonSyntaxException(nbtexception);
            }
        }
    }

}
