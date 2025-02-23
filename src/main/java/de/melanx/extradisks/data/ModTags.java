package de.melanx.extradisks.data;

import de.melanx.extradisks.ExtraDisks;
import de.melanx.extradisks.items.Registration;
import de.melanx.extradisks.items.fluid.ExtraFluidStorageType;
import de.melanx.extradisks.items.item.ExtraItemStorageType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;

public class ModTags {

    public static class Blocks {
        public static final Tag.Named<Block> STORAGE_BLOCKS = tag("storage_blocks");
        public static final Tag.Named<Block> ITEM_STORAGE_BLOCKS = tag("storage_blocks/items");
        public static final Tag.Named<Block> FLUID_STORAGE_BLOCKS = tag("storage_blocks/fluids");
        public static final Map<ExtraItemStorageType, Tag.Named<Block>> STORAGE_BLOCKS_ITEM = new HashMap<>();
        public static final Map<ExtraFluidStorageType, Tag.Named<Block>> STORAGE_BLOCKS_FLUID = new HashMap<>();

        static {
            for (ExtraItemStorageType type : ExtraItemStorageType.values()) {
                STORAGE_BLOCKS_ITEM.put(type, tag("storage_blocks/items/" + type.getName()));
            }
            for (ExtraFluidStorageType type : ExtraFluidStorageType.values()) {
                STORAGE_BLOCKS_FLUID.put(type, tag("storage_blocks/fluids/" + type.getName()));
            }
        }

        private static Tag.Named<Block> tag(String name) {
            return net.minecraft.tags.BlockTags.bind("refinedstorage:" + name);
        }
    }

    public static class Items {
        public static final Tag.Named<Item> STORAGE_BLOCKS = tag("storage_blocks");
        public static final Tag.Named<Item> ITEM_STORAGE_BLOCKS = tag("storage_blocks/items");
        public static final Tag.Named<Item> FLUID_STORAGE_BLOCKS = tag("storage_blocks/fluids");
        public static final Map<ExtraItemStorageType, Tag.Named<Item>> STORAGE_BLOCKS_ITEM = new HashMap<>();
        public static final Map<ExtraFluidStorageType, Tag.Named<Item>> STORAGE_BLOCKS_FLUID = new HashMap<>();

        public static final Tag.Named<Item> PARTS = tag("parts");
        public static final Tag.Named<Item> ITEM_PARTS = tag("parts/items");
        public static final Tag.Named<Item> FLUID_PARTS = tag("parts/fluids");
        public static final Map<ExtraItemStorageType, Tag.Named<Item>> PARTS_ITEM = new HashMap<>();
        public static final Map<ExtraFluidStorageType, Tag.Named<Item>> PARTS_FLUID = new HashMap<>();

        public static final Tag.Named<Item> DISKS = tag("disks");
        public static final Tag.Named<Item> ITEM_DISKS = tag("disks/items");
        public static final Tag.Named<Item> FLUID_DISKS = tag("disks/fluids");
        public static final Map<ExtraItemStorageType, Tag.Named<Item>> DISKS_ITEM = new HashMap<>();
        public static final Map<ExtraFluidStorageType, Tag.Named<Item>> DISKS_FLUID = new HashMap<>();

        static {
            for (ExtraItemStorageType type : ExtraItemStorageType.values()) {
                STORAGE_BLOCKS_ITEM.put(type, tag("storage_blocks/items/" + type.getName()));
                PARTS_ITEM.put(type, tag("parts/items/" + type.getName()));
                DISKS_ITEM.put(type, tag("disks/items/" + type.getName()));
            }
            for (ExtraFluidStorageType type : ExtraFluidStorageType.values()) {
                STORAGE_BLOCKS_FLUID.put(type, tag("storage_blocks/fluids/" + type.getName()));
                PARTS_FLUID.put(type, tag("parts/fluids/" + type.getName()));
                DISKS_FLUID.put(type, tag("disks/fluids/" + type.getName()));
            }
        }

        private static Tag.Named<Item> tag(String name) {
            return net.minecraft.tags.ItemTags.bind("refinedstorage:" + name);
        }
    }

    public static class BlockTags extends BlockTagsProvider {
        public BlockTags(DataGenerator generator, ExistingFileHelper helper) {
            super(generator, ExtraDisks.MODID, helper);
        }

        @Override
        protected void addTags() {
            TagAppender<Block> itemBlocksBuilder = this.tag(Blocks.ITEM_STORAGE_BLOCKS);
            for (ExtraItemStorageType type : ExtraItemStorageType.values()) {
                Tag.Named<Block> tag = Blocks.STORAGE_BLOCKS_ITEM.get(type);
                this.tag(tag).add(Registration.ITEM_STORAGE_BLOCK.get(type).get());
                itemBlocksBuilder.addTag(tag);
            }

            TagAppender<Block> fluidBlocksBuilder = this.tag(Blocks.FLUID_STORAGE_BLOCKS);
            for (ExtraFluidStorageType type : ExtraFluidStorageType.values()) {
                Tag.Named<Block> tag = Blocks.STORAGE_BLOCKS_FLUID.get(type);
                this.tag(tag).add(Registration.FLUID_STORAGE_BLOCK.get(type).get());
                fluidBlocksBuilder.addTag(tag);
            }

            //noinspection unchecked
            this.tag(Blocks.STORAGE_BLOCKS).addTags(Blocks.ITEM_STORAGE_BLOCKS, Blocks.FLUID_STORAGE_BLOCKS);
        }
    }

    public static class ItemTags extends ItemTagsProvider {
        public ItemTags(DataGenerator generator, ExistingFileHelper helper, BlockTagsProvider provider) {
            super(generator, provider, ExtraDisks.MODID, helper);
        }

        @Override
        protected void addTags() {
            TagAppender<Item> itemPartsBuilder = this.tag(Items.ITEM_PARTS);
            TagAppender<Item> itemDisksBuilder = this.tag(Items.ITEM_DISKS);
            for (ExtraItemStorageType type : ExtraItemStorageType.values()) {
                Tag.Named<Item> tag = Items.PARTS_ITEM.get(type);
                this.tag(tag).add(Registration.ITEM_STORAGE_PART.get(type).get());
                itemPartsBuilder.addTag(tag);

                tag = Items.DISKS_ITEM.get(type);
                this.tag(tag).add(Registration.ITEM_STORAGE_DISK.get(type).get());
                itemDisksBuilder.addTag(tag);
            }

            TagAppender<Item> fluidPartsBuilder = this.tag(Items.FLUID_PARTS);
            TagAppender<Item> fluidDisksBuilder = this.tag(Items.FLUID_DISKS);
            for (ExtraFluidStorageType type : ExtraFluidStorageType.values()) {
                Tag.Named<Item> tag = Items.PARTS_FLUID.get(type);
                this.tag(tag).add(Registration.FLUID_STORAGE_PART.get(type).get());
                fluidPartsBuilder.addTag(tag);

                tag = Items.DISKS_FLUID.get(type);
                this.tag(tag).add(Registration.FLUID_STORAGE_DISK.get(type).get());
                fluidDisksBuilder.addTag(tag);
            }

            //noinspection unchecked
            this.tag(Items.PARTS).addTags(Items.ITEM_PARTS, Items.FLUID_PARTS);
            //noinspection unchecked
            this.tag(Items.DISKS).addTags(Items.ITEM_DISKS, Items.FLUID_DISKS);

            // blocks
            this.copy(Blocks.ITEM_STORAGE_BLOCKS, Items.ITEM_STORAGE_BLOCKS);
            for (ExtraItemStorageType type : ExtraItemStorageType.values()) {
                this.copy(Blocks.STORAGE_BLOCKS_ITEM.get(type), Items.STORAGE_BLOCKS_ITEM.get(type));
            }

            this.copy(Blocks.FLUID_STORAGE_BLOCKS, Items.FLUID_STORAGE_BLOCKS);
            for (ExtraFluidStorageType type : ExtraFluidStorageType.values()) {
                this.copy(Blocks.STORAGE_BLOCKS_FLUID.get(type), Items.STORAGE_BLOCKS_FLUID.get(type));
            }

            this.copy(Blocks.STORAGE_BLOCKS, Items.STORAGE_BLOCKS);
        }
    }
}
