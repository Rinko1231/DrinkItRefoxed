package committee.nova.drinkit;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import static committee.nova.drinkit.config.drinkConfig.COMMON_CONFIG;

@Mod(DrinkIt.MODID)
public class DrinkIt {

    public static final String MODID = "drinkit";
    public static final TagKey<Item> TAG_DRINKABLE = tag( "drinkable");
    public static final TagKey<Item> TAG_THICK = tag("thick");
    public static final TagKey<Item> TAG_SILENT = tag("noeatingsound");

    private static TagKey<Item> tag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MODID, name));
    }


    public DrinkIt(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }


}
