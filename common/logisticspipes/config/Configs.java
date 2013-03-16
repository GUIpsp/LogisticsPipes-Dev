package logisticspipes.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import logisticspipes.LogisticsPipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Configs {

	public static final String CATEGORY_MULTITHREAD = "multiThread";

	// Ids
	public static int ITEM_LIQUID_CONTAINER_ID = 6864;
	public static int ITEM_UPGRADE_MANAGER_ID = 6865;
	public static int ITEM_UPGRADE_ID = 6866;
	public static int ITEM_PARTS_ID = 6867;
	public static int ITEM_HUD_ID = 6868;
	public static int ITEM_CARD_ID = 6869;
	public static int ITEM_DISK_ID = 6870;
	public static int ITEM_MODULE_ID = 6871;
	public static int LOGISTICSREMOTEORDERER_ID = 6872;
	public static int LOGISTICSNETWORKMONITOR_ID = 6873;
	public static int LOGISTICSPIPE_BASIC_ID = 6874;
	public static int LOGISTICSPIPE_REQUEST_ID = 6875;
	public static int LOGISTICSPIPE_PROVIDER_ID = 6876;
	public static int LOGISTICSPIPE_CRAFTING_ID = 6877;
	public static int LOGISTICSPIPE_SATELLITE_ID = 6878;
	public static int LOGISTICSPIPE_SUPPLIER_ID = 6879;
	public static int LOGISTICSPIPE_BUILDERSUPPLIER_ID = 6880;
	public static int LOGISTICSPIPE_CHASSI1_ID = 6881;
	public static int LOGISTICSPIPE_CHASSI2_ID = 6882;
	public static int LOGISTICSPIPE_CHASSI3_ID = 6883;
	public static int LOGISTICSPIPE_CHASSI4_ID = 6884;
	public static int LOGISTICSPIPE_CHASSI5_ID = 6885;
	public static int LOGISTICSPIPE_LIQUIDSUPPLIER_ID = 6886;
	public static int LOGISTICSPIPE_CRAFTING_MK2_ID = 6887;
	public static int LOGISTICSPIPE_REQUEST_MK2_ID = 6888;
	public static int LOGISTICSPIPE_REMOTE_ORDERER_ID = 6889;
	public static int LOGISTICSPIPE_PROVIDER_MK2_ID = 6890;
	public static int LOGISTICSPIPE_APIARIST_ANALYSER_ID = 6891;
	public static int LOGISTICSPIPE_APIARIST_SINK_ID = 6892;
	public static int LOGISTICSPIPE_INVSYSCON_ID = 6893;
	public static int LOGISTICSPIPE_ENTRANCE_ID = 6894;
	public static int LOGISTICSPIPE_DESTINATION_ID = 6895;
	public static int LOGISTICSPIPE_CRAFTING_MK3_ID = 6896;
	public static int LOGISTICSPIPE_FIREWALL_ID = 6897;

	public static int LOGISTICSPIPE_LIQUID_CONNECTOR = 6901;
	public static int LOGISTICSPIPE_LIQUID_BASIC = 6902;
	public static int LOGISTICSPIPE_LIQUID_INSERTION = 6903;
	public static int LOGISTICSPIPE_LIQUID_PROVIDER = 6904;
	public static int LOGISTICSPIPE_LIQUID_REQUEST = 6905;

	public static int LOGISTICSCRAFTINGSIGNCREATOR_ID = 6900;

	private static Configuration CONFIGURATION;

	// Configrables
	public static int LOGISTICS_DETECTION_LENGTH = 50;
	public static int LOGISTICS_DETECTION_COUNT = 100;
	public static int LOGISTICS_DETECTION_FREQUENCY = 20;
	public static boolean LOGISTICS_ORDERER_COUNT_INVERTWHEEL = false;
	public static boolean LOGISTICS_ORDERER_PAGE_INVERTWHEEL = false;
	public static final float LOGISTICS_ROUTED_SPEED_MULTIPLIER = 20F;
	public static final float LOGISTICS_DEFAULTROUTED_SPEED_MULTIPLIER = 10F;

	public static int LOGISTICS_HUD_RENDER_DISTANCE = 15;

	public static boolean LOGISTICS_POWER_USAGE_DISABLED = false;
	public static boolean LOGISTICS_TILE_GENERIC_PIPE_REPLACEMENT_DISABLED = false;

	public static boolean TOOLTIP_INFO = LogisticsPipes.DEBUG;
	public static boolean MANDATORY_CARPENTER_RECIPES = true;
	public static boolean ENABLE_PARTICLE_FX = true;

	// GuiOrderer Popup setting
	public static boolean DISPLAY_POPUP = true;

	// BlockID
	public static int LOGISTICS_SIGN_ID = 1100;
	public static int LOGISTICS_SOLID_BLOCK_ID = 1101;

	// MultiThread
	public static boolean MULTI_THREAD_ENABLED = true;
	public static int MULIT_THREAD_NUMBER = 4;
	public static int MULTI_THREAD_PRIORITY = Thread.NORM_PRIORITY;

	public static int POWER_USAGE_MULTIPLIER = 1;

	private static void readoldconfig() {
		Property logisticRemoteOrdererIdProperty = CONFIGURATION.get(
				"logisticsRemoteOrderer.id", Configuration.CATEGORY_ITEM,
				LOGISTICSREMOTEORDERER_ID);
		Property logisticNetworkMonitorIdProperty = CONFIGURATION.get(
				"logisticsNetworkMonitor.id", Configuration.CATEGORY_ITEM,
				LOGISTICSNETWORKMONITOR_ID);
		Property logisticPipeIdProperty = CONFIGURATION.get("logisticsPipe.id",
				Configuration.CATEGORY_ITEM, LOGISTICSPIPE_BASIC_ID);
		Property logisticPipeRequesterIdProperty = CONFIGURATION.get(
				"logisticsPipeRequester.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_REQUEST_ID);
		Property logisticPipeProviderIdProperty = CONFIGURATION.get(
				"logisticsPipeProvider.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_PROVIDER_ID);
		Property logisticPipeCraftingIdProperty = CONFIGURATION.get(
				"logisticsPipeCrafting.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CRAFTING_ID);
		Property logisticPipeSatelliteIdProperty = CONFIGURATION.get(
				"logisticsPipeSatellite.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_SATELLITE_ID);
		Property logisticPipeSupplierIdProperty = CONFIGURATION.get(
				"logisticsPipeSupplier.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_SUPPLIER_ID);
		Property logisticPipeChassi1IdProperty = CONFIGURATION.get(
				"logisticsPipeChassi1.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CHASSI1_ID);
		Property logisticPipeChassi2IdProperty = CONFIGURATION.get(
				"logisticsPipeChassi2.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CHASSI2_ID);
		Property logisticPipeChassi3IdProperty = CONFIGURATION.get(
				"logisticsPipeChassi3.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CHASSI3_ID);
		Property logisticPipeChassi4IdProperty = CONFIGURATION.get(
				"logisticsPipeChassi4.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CHASSI4_ID);
		Property logisticPipeChassi5IdProperty = CONFIGURATION.get(
				"logisticsPipeChassi5.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CHASSI5_ID);
		Property logisticPipeCraftingMK2IdProperty = CONFIGURATION.get(
				"logisticsPipeCraftingMK2.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CRAFTING_MK2_ID);
		Property logisticPipeCraftingMK3IdProperty = CONFIGURATION.get(
				"logisticsPipeCraftingMK3.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_CRAFTING_MK3_ID);
		Property logisticPipeRequesterMK2IdProperty = CONFIGURATION.get(
				"logisticsPipeRequesterMK2.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_REQUEST_MK2_ID);
		Property logisticPipeProviderMK2IdProperty = CONFIGURATION.get(
				"logisticsPipeProviderMK2.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_PROVIDER_MK2_ID);
		Property logisticPipeApiaristAnalyserIdProperty = CONFIGURATION
				.get("logisticsPipeApiaristAnalyser.id",
						Configuration.CATEGORY_ITEM,
						LOGISTICSPIPE_APIARIST_ANALYSER_ID);
		Property logisticPipeRemoteOrdererIdProperty = CONFIGURATION.get(
				"logisticsPipeRemoteOrderer.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_REMOTE_ORDERER_ID);
		Property logisticPipeApiaristSinkIdProperty = CONFIGURATION.get(
				"logisticsPipeApiaristSink.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_APIARIST_SINK_ID);
		Property logisticModuleIdProperty = CONFIGURATION.get(
				"logisticsModules.id", Configuration.CATEGORY_ITEM,
				ITEM_MODULE_ID);
		Property logisticItemDiskIdProperty = CONFIGURATION.get(
				"logisticsDisk.id", Configuration.CATEGORY_ITEM, ITEM_DISK_ID);
		Property logisticItemHUDIdProperty = CONFIGURATION.get(
				"logisticsHUD.id", Configuration.CATEGORY_ITEM, ITEM_HUD_ID);
		Property logisticItemPartsIdProperty = CONFIGURATION.get(
				"logisticsHUDParts.id", Configuration.CATEGORY_ITEM,
				ITEM_PARTS_ID);
		Property logisticCraftingSignCreatorIdProperty = CONFIGURATION.get(
				"logisticsCraftingSignCreator.id", Configuration.CATEGORY_ITEM,
				LOGISTICSCRAFTINGSIGNCREATOR_ID);
		Property logisticPipeBuilderSupplierIdProperty = CONFIGURATION.get(
				"logisticsPipeBuilderSupplier.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_BUILDERSUPPLIER_ID);
		Property logisticPipeLiquidSupplierIdProperty = CONFIGURATION.get(
				"logisticsPipeLiquidSupplier.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_LIQUIDSUPPLIER_ID);
		Property logisticInvSysConIdProperty = CONFIGURATION.get(
				"logisticInvSysCon.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_INVSYSCON_ID);
		Property logisticEntranceIdProperty = CONFIGURATION.get(
				"logisticEntrance.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_ENTRANCE_ID);
		Property logisticDestinationIdProperty = CONFIGURATION.get(
				"logisticDestination.id", Configuration.CATEGORY_ITEM,
				LOGISTICSPIPE_DESTINATION_ID);
		Property logisticItemCardIdProperty = CONFIGURATION.get(
				"logisticItemCard.id", Configuration.CATEGORY_ITEM,
				ITEM_CARD_ID);
		Property detectionLength = CONFIGURATION.get("detectionLength",
				Configuration.CATEGORY_GENERAL, LOGISTICS_DETECTION_LENGTH);
		Property detectionCount = CONFIGURATION.get("detectionCount",
				Configuration.CATEGORY_GENERAL, LOGISTICS_DETECTION_COUNT);
		Property detectionFrequency = CONFIGURATION.get("detectionFrequency",
				Configuration.CATEGORY_GENERAL, LOGISTICS_DETECTION_FREQUENCY);
		Property countInvertWheelProperty = CONFIGURATION.get(
				"ordererCountInvertWheel", Configuration.CATEGORY_GENERAL,
				LOGISTICS_ORDERER_COUNT_INVERTWHEEL);
		Property pageInvertWheelProperty = CONFIGURATION.get(
				"ordererPageInvertWheel", Configuration.CATEGORY_GENERAL,
				LOGISTICS_ORDERER_PAGE_INVERTWHEEL);
		Property pageDisplayPopupProperty = CONFIGURATION.get("displayPopup",
				Configuration.CATEGORY_GENERAL, DISPLAY_POPUP);
		if (CONFIGURATION.categories.containsKey(Configuration.CATEGORY_BLOCK)
				&& CONFIGURATION.categories.get(Configuration.CATEGORY_BLOCK)
						.containsKey("logisticsBlockId")) {
			Property logisticsBlockId = CONFIGURATION.get("logisticsBlockId",
					Configuration.CATEGORY_BLOCK, LOGISTICS_SIGN_ID);
			LOGISTICS_SIGN_ID = Integer.parseInt(logisticsBlockId.value);
			CONFIGURATION.categories.get(Configuration.CATEGORY_BLOCK).remove(
					"logisticsBlockId");
		}
		Property logisticsSignId = CONFIGURATION.get("logisticsSignId",
				Configuration.CATEGORY_BLOCK, LOGISTICS_SIGN_ID);
		Property logisticsSolidBlockId = CONFIGURATION.get(
				"logisticsSolidBlockId", Configuration.CATEGORY_BLOCK,
				LOGISTICS_SOLID_BLOCK_ID);
		Property logisticsPowerUsageDisable = CONFIGURATION.get(
				"powerUsageDisabled", Configuration.CATEGORY_GENERAL,
				LOGISTICS_POWER_USAGE_DISABLED);
		Property logisticsTileGenericReplacementDisable = CONFIGURATION.get(
				"TileReplaceDisabled", Configuration.CATEGORY_GENERAL,
				LOGISTICS_TILE_GENERIC_PIPE_REPLACEMENT_DISABLED);
		Property logisticsHUDRenderDistance = CONFIGURATION.get(
				"HUDRenderDistance", Configuration.CATEGORY_GENERAL,
				LOGISTICS_HUD_RENDER_DISTANCE);
		Property mandatoryCarpenterRecipies = CONFIGURATION.get(
				"mandatoryCarpenterRecipies", Configuration.CATEGORY_GENERAL,
				MANDATORY_CARPENTER_RECIPES);
		Property enableParticleFX = CONFIGURATION.get("enableParticleFX",
				Configuration.CATEGORY_GENERAL, ENABLE_PARTICLE_FX);

		LOGISTICSNETWORKMONITOR_ID = Integer
				.parseInt(logisticNetworkMonitorIdProperty.value);
		LOGISTICSREMOTEORDERER_ID = Integer
				.parseInt(logisticRemoteOrdererIdProperty.value);
		ITEM_MODULE_ID = Integer.parseInt(logisticModuleIdProperty.value);
		ITEM_DISK_ID = Integer.parseInt(logisticItemDiskIdProperty.value);
		ITEM_CARD_ID = Integer.parseInt(logisticItemCardIdProperty.value);
		ITEM_HUD_ID = Integer.parseInt(logisticItemHUDIdProperty.value);
		ITEM_PARTS_ID = Integer.parseInt(logisticItemPartsIdProperty.value);

		LOGISTICSPIPE_BASIC_ID = Integer.parseInt(logisticPipeIdProperty.value);
		LOGISTICSPIPE_REQUEST_ID = Integer
				.parseInt(logisticPipeRequesterIdProperty.value);
		LOGISTICSPIPE_PROVIDER_ID = Integer
				.parseInt(logisticPipeProviderIdProperty.value);
		LOGISTICSPIPE_CRAFTING_ID = Integer
				.parseInt(logisticPipeCraftingIdProperty.value);
		LOGISTICSPIPE_SATELLITE_ID = Integer
				.parseInt(logisticPipeSatelliteIdProperty.value);
		LOGISTICSPIPE_SUPPLIER_ID = Integer
				.parseInt(logisticPipeSupplierIdProperty.value);
		LOGISTICSPIPE_CHASSI1_ID = Integer
				.parseInt(logisticPipeChassi1IdProperty.value);
		LOGISTICSPIPE_CHASSI2_ID = Integer
				.parseInt(logisticPipeChassi2IdProperty.value);
		LOGISTICSPIPE_CHASSI3_ID = Integer
				.parseInt(logisticPipeChassi3IdProperty.value);
		LOGISTICSPIPE_CHASSI4_ID = Integer
				.parseInt(logisticPipeChassi4IdProperty.value);
		LOGISTICSPIPE_CHASSI5_ID = Integer
				.parseInt(logisticPipeChassi5IdProperty.value);
		LOGISTICSPIPE_CRAFTING_MK2_ID = Integer
				.parseInt(logisticPipeCraftingMK2IdProperty.value);
		LOGISTICSPIPE_CRAFTING_MK3_ID = Integer
				.parseInt(logisticPipeCraftingMK3IdProperty.value);
		LOGISTICSPIPE_REQUEST_MK2_ID = Integer
				.parseInt(logisticPipeRequesterMK2IdProperty.value);
		LOGISTICSPIPE_PROVIDER_MK2_ID = Integer
				.parseInt(logisticPipeProviderMK2IdProperty.value);
		LOGISTICSPIPE_REMOTE_ORDERER_ID = Integer
				.parseInt(logisticPipeRemoteOrdererIdProperty.value);
		LOGISTICSPIPE_APIARIST_ANALYSER_ID = Integer
				.parseInt(logisticPipeApiaristAnalyserIdProperty.value);
		LOGISTICSPIPE_APIARIST_SINK_ID = Integer
				.parseInt(logisticPipeApiaristSinkIdProperty.value);
		LOGISTICSPIPE_ENTRANCE_ID = Integer
				.parseInt(logisticEntranceIdProperty.value);
		LOGISTICSPIPE_DESTINATION_ID = Integer
				.parseInt(logisticDestinationIdProperty.value);
		LOGISTICSPIPE_INVSYSCON_ID = Integer
				.parseInt(logisticInvSysConIdProperty.value);
		LOGISTICS_SIGN_ID = Integer.parseInt(logisticsSignId.value);
		LOGISTICS_SOLID_BLOCK_ID = Integer
				.parseInt(logisticsSolidBlockId.value);

		LOGISTICS_DETECTION_LENGTH = Integer.parseInt(detectionLength.value);
		LOGISTICS_DETECTION_COUNT = Integer.parseInt(detectionCount.value);
		LOGISTICS_DETECTION_FREQUENCY = Math.max(
				Integer.parseInt(detectionFrequency.value), 1);
		LOGISTICS_ORDERER_COUNT_INVERTWHEEL = Boolean
				.parseBoolean(countInvertWheelProperty.value);
		LOGISTICS_ORDERER_PAGE_INVERTWHEEL = Boolean
				.parseBoolean(pageInvertWheelProperty.value);

		LOGISTICS_POWER_USAGE_DISABLED = Boolean
				.parseBoolean(logisticsPowerUsageDisable.value);
		LOGISTICS_TILE_GENERIC_PIPE_REPLACEMENT_DISABLED = Boolean
				.parseBoolean(logisticsTileGenericReplacementDisable.value);

		LOGISTICSCRAFTINGSIGNCREATOR_ID = Integer
				.parseInt(logisticCraftingSignCreatorIdProperty.value);

		LOGISTICS_HUD_RENDER_DISTANCE = Integer
				.parseInt(logisticsHUDRenderDistance.value);

		DISPLAY_POPUP = Boolean.parseBoolean(pageDisplayPopupProperty.value);

		LOGISTICSPIPE_BUILDERSUPPLIER_ID = Integer
				.parseInt(logisticPipeBuilderSupplierIdProperty.value);
		LOGISTICSPIPE_LIQUIDSUPPLIER_ID = Integer
				.parseInt(logisticPipeLiquidSupplierIdProperty.value);
		MANDATORY_CARPENTER_RECIPES = Boolean
				.parseBoolean(mandatoryCarpenterRecipies.value);
		ENABLE_PARTICLE_FX = Boolean.parseBoolean(enableParticleFX.value);
	}

	public static void load(FMLPreInitializationEvent event) {
		CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());
		CONFIGURATION.load();

		if (CONFIGURATION.hasCategory("logisticspipe.id")
				|| CONFIGURATION.hasCategory("logisticsPipe.id")) {
			readoldconfig();
			CONFIGURATION.categories.clear();
		}

		Property logisticRemoteOrdererIdProperty = CONFIGURATION.getItem(
				"logisticsRemoteOrderer.id", LOGISTICSREMOTEORDERER_ID);
		logisticRemoteOrdererIdProperty.comment = "The item id for the remote orderer";

		Property logisticNetworkMonitorIdProperty = CONFIGURATION.getItem(
				"logisticsNetworkMonitor.id", LOGISTICSNETWORKMONITOR_ID);
		logisticNetworkMonitorIdProperty.comment = "The item id for the network monitor";

		Property logisticPipeIdProperty = CONFIGURATION.getItem(
				"logisticsPipe.id", LOGISTICSPIPE_BASIC_ID);
		logisticPipeIdProperty.comment = "The item id for the basic logistics pipe";

		Property logisticPipeRequesterIdProperty = CONFIGURATION.getItem(
				"logisticsPipeRequester.id", LOGISTICSPIPE_REQUEST_ID);
		logisticPipeRequesterIdProperty.comment = "The item id for the requesting logistics pipe";

		Property logisticPipeProviderIdProperty = CONFIGURATION.getItem(
				"logisticsPipeProvider.id", LOGISTICSPIPE_PROVIDER_ID);
		logisticPipeProviderIdProperty.comment = "The item id for the providing logistics pipe";

		Property logisticPipeCraftingIdProperty = CONFIGURATION.getItem(
				"logisticsPipeCrafting.id", LOGISTICSPIPE_CRAFTING_ID);
		logisticPipeCraftingIdProperty.comment = "The item id for the crafting logistics pipe";

		Property logisticPipeSatelliteIdProperty = CONFIGURATION.getItem(
				"logisticsPipeSatellite.id", LOGISTICSPIPE_SATELLITE_ID);
		logisticPipeSatelliteIdProperty.comment = "The item id for the crafting satellite pipe";

		Property logisticPipeSupplierIdProperty = CONFIGURATION.getItem(
				"logisticsPipeSupplier.id", LOGISTICSPIPE_SUPPLIER_ID);
		logisticPipeSupplierIdProperty.comment = "The item id for the supplier pipe";

		Property logisticPipeChassi1IdProperty = CONFIGURATION.getItem(
				"logisticsPipeChassi1.id", LOGISTICSPIPE_CHASSI1_ID);
		logisticPipeChassi1IdProperty.comment = "The item id for the chassi1";

		Property logisticPipeChassi2IdProperty = CONFIGURATION.getItem(
				"logisticsPipeChassi2.id", LOGISTICSPIPE_CHASSI2_ID);
		logisticPipeChassi2IdProperty.comment = "The item id for the chassi2";

		Property logisticPipeChassi3IdProperty = CONFIGURATION.getItem(
				"logisticsPipeChassi3.id", LOGISTICSPIPE_CHASSI3_ID);
		logisticPipeChassi3IdProperty.comment = "The item id for the chassi3";

		Property logisticPipeChassi4IdProperty = CONFIGURATION.getItem(
				"logisticsPipeChassi4.id", LOGISTICSPIPE_CHASSI4_ID);
		logisticPipeChassi4IdProperty.comment = "The item id for the chassi4";

		Property logisticPipeChassi5IdProperty = CONFIGURATION.getItem(
				"logisticsPipeChassi5.id", LOGISTICSPIPE_CHASSI5_ID);
		logisticPipeChassi5IdProperty.comment = "The item id for the chassi5";

		Property logisticPipeCraftingMK2IdProperty = CONFIGURATION.getItem(
				"logisticsPipeCraftingMK2.id", LOGISTICSPIPE_CRAFTING_MK2_ID);
		logisticPipeCraftingMK2IdProperty.comment = "The item id for the crafting logistics pipe MK2";

		Property logisticPipeCraftingMK3IdProperty = CONFIGURATION.getItem(
				"logisticsPipeCraftingMK3.id", LOGISTICSPIPE_CRAFTING_MK3_ID);
		logisticPipeCraftingMK3IdProperty.comment = "The item id for the crafting logistics pipe MK3";

		Property logisticPipeFirewallIdProperty = CONFIGURATION.getItem(
				"logisticsPipeFirewall.id", LOGISTICSPIPE_FIREWALL_ID);
		logisticPipeFirewallIdProperty.comment = "The item id for the firewall logistics pipe";

		// DEBUG (TEST) ONLY (LIQUID)
		Property logisticPipeLiquidConnectorIdProperty = null;
		Property logisticPipeLiquidBasicIdProperty = null;
		Property logisticPipeLiquidInsertionIdProperty = null;
		Property logisticPipeLiquidProviderIdProperty = null;
		Property logisticPipeLiquidRequestIdProperty = null;
		if (LogisticsPipes.DEBUG) {

			logisticPipeLiquidConnectorIdProperty = CONFIGURATION.getItem(
					"logisticPipeLiquidConnector.id",
					LOGISTICSPIPE_LIQUID_CONNECTOR);
			logisticPipeLiquidConnectorIdProperty.comment = "The item id for the liquid connector pipe.";

			logisticPipeLiquidBasicIdProperty = CONFIGURATION.getItem(
					"logisticPipeLiquidBasic.id", LOGISTICSPIPE_LIQUID_BASIC);
			logisticPipeLiquidBasicIdProperty.comment = "The item id for the liquid basic pipe.";

			logisticPipeLiquidInsertionIdProperty = CONFIGURATION.getItem(
					"logisticPipeLiquidInsertion.id",
					LOGISTICSPIPE_LIQUID_INSERTION);
			logisticPipeLiquidInsertionIdProperty.comment = "The item id for the liquid insertion pipe.";

			logisticPipeLiquidProviderIdProperty = CONFIGURATION.getItem(
					"logisticPipeLiquidProvider.id",
					LOGISTICSPIPE_LIQUID_PROVIDER);
			logisticPipeLiquidProviderIdProperty.comment = "The item id for the liquid provider pipe.";

			logisticPipeLiquidRequestIdProperty = CONFIGURATION.getItem(
					"logisticPipeLiquidRequest.id",
					LOGISTICSPIPE_LIQUID_REQUEST);
			logisticPipeLiquidRequestIdProperty.comment = "The item id for the liquid requestor pipe.";
		}

		Property logisticPipeRequesterMK2IdProperty = CONFIGURATION.getItem(
				"logisticsPipeRequesterMK2.id", LOGISTICSPIPE_REQUEST_MK2_ID);
		logisticPipeRequesterMK2IdProperty.comment = "The item id for the requesting logistics pipe MK2";

		Property logisticPipeProviderMK2IdProperty = CONFIGURATION.getItem(
				"logisticsPipeProviderMK2.id", LOGISTICSPIPE_PROVIDER_MK2_ID);
		logisticPipeProviderMK2IdProperty.comment = "The item id for the provider logistics pipe MK2";

		Property logisticPipeApiaristAnalyserIdProperty = CONFIGURATION
				.getItem("logisticsPipeApiaristAnalyser.id",
						LOGISTICSPIPE_APIARIST_ANALYSER_ID);
		logisticPipeApiaristAnalyserIdProperty.comment = "The item id for the apiarist logistics analyser pipe";

		Property logisticPipeRemoteOrdererIdProperty = CONFIGURATION.getItem(
				"logisticsPipeRemoteOrderer.id",
				LOGISTICSPIPE_REMOTE_ORDERER_ID);
		logisticPipeRemoteOrdererIdProperty.comment = "The item id for the remote orderer logistics pipe";

		Property logisticPipeApiaristSinkIdProperty = CONFIGURATION.getItem(
				"logisticsPipeApiaristSink.id", LOGISTICSPIPE_APIARIST_SINK_ID);
		logisticPipeApiaristSinkIdProperty.comment = "The item id for the apiarist logistics sink pipe";

		Property logisticModuleIdProperty = CONFIGURATION.getItem(
				"logisticsModules.id", ITEM_MODULE_ID);
		logisticModuleIdProperty.comment = "The item id for the modules";

		Property logisticUpgradeIdProperty = CONFIGURATION.getItem(
				"logisticsUpgrades.id", ITEM_UPGRADE_ID);
		logisticUpgradeIdProperty.comment = "The item id for the upgrades";

		Property logisticUpgradeManagerIdProperty = CONFIGURATION.getItem(
				"logisticsUpgradeManager.id", ITEM_UPGRADE_MANAGER_ID);
		logisticUpgradeManagerIdProperty.comment = "The item id for the upgrade manager";

		Property logisticItemDiskIdProperty = CONFIGURATION.getItem(
				"logisticsDisk.id", ITEM_DISK_ID);
		logisticItemDiskIdProperty.comment = "The item id for the disk";

		Property logisticItemHUDIdProperty = CONFIGURATION.getItem(
				"logisticsHUD.id", ITEM_HUD_ID);
		logisticItemHUDIdProperty.comment = "The item id for the Logistics HUD glasses";

		Property logisticItemPartsIdProperty = CONFIGURATION.getItem(
				"logisticsHUDParts.id", ITEM_PARTS_ID);
		logisticItemPartsIdProperty.comment = "The item id for the Logistics item parts";

		Property logisticCraftingSignCreatorIdProperty = CONFIGURATION.getItem(
				"logisticsCraftingSignCreator.id",
				LOGISTICSCRAFTINGSIGNCREATOR_ID);
		logisticCraftingSignCreatorIdProperty.comment = "The item id for the crafting sign creator";

		Property logisticPipeBuilderSupplierIdProperty = CONFIGURATION.getItem(
				"logisticsPipeBuilderSupplier.id",
				LOGISTICSPIPE_BUILDERSUPPLIER_ID);
		logisticPipeBuilderSupplierIdProperty.comment = "The item id for the builder supplier pipe";

		Property logisticPipeLiquidSupplierIdProperty = CONFIGURATION.getItem(
				"logisticsPipeLiquidSupplier.id",
				LOGISTICSPIPE_LIQUIDSUPPLIER_ID);
		logisticPipeLiquidSupplierIdProperty.comment = "The item id for the liquid supplier pipe";

		Property logisticInvSysConIdProperty = CONFIGURATION.getItem(
				"logisticInvSysCon.id", LOGISTICSPIPE_INVSYSCON_ID);
		logisticInvSysConIdProperty.comment = "The item id for the inventory system connector pipe";

		Property logisticEntranceIdProperty = CONFIGURATION.getItem(
				"logisticEntrance.id", LOGISTICSPIPE_ENTRANCE_ID);
		logisticEntranceIdProperty.comment = "The item id for the logistics system entrance pipe";

		Property logisticDestinationIdProperty = CONFIGURATION.getItem(
				"logisticDestination.id", LOGISTICSPIPE_DESTINATION_ID);
		logisticDestinationIdProperty.comment = "The item id for the logistics system destination pipe";

		Property logisticItemCardIdProperty = CONFIGURATION.getItem(
				"logisticItemCard.id", ITEM_CARD_ID);
		logisticItemCardIdProperty.comment = "The item id for the logistics item card";

		// DEBUG (TEST) ONLY
		Property logisticsLiquidContainerIdProperty = null;
		if (LogisticsPipes.DEBUG) {
			logisticsLiquidContainerIdProperty = CONFIGURATION.getItem(
					"LogisticsLiquidContainer.id", ITEM_LIQUID_CONTAINER_ID);
			logisticsLiquidContainerIdProperty.comment = "The item id for the logistics liquid container";
		}

		Property detectionLength = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "detectionLength",
				LOGISTICS_DETECTION_LENGTH);
		detectionLength.comment = "The maximum shortest length between logistics pipes. This is an indicator on the maxim depth of the recursion algorithm to discover logistics neighbours. A low value might use less CPU, a high value will allow longer pipe sections";

		Property detectionCount = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "detectionCount",
				LOGISTICS_DETECTION_COUNT);
		detectionCount.comment = "The maximum number of buildcraft pipes (including forks) between logistics pipes. This is an indicator of the maximum amount of nodes the recursion algorithm will visit before giving up. As it is possible to fork a pipe connection using standard BC pipes the algorithm will attempt to discover all available destinations through that pipe. Do note that the logistics system will not interfere with the operation of non-logistics pipes. So a forked pipe will usually be sup-optimal, but it is possible. A low value might reduce CPU usage, a high value will be able to handle more complex pipe setups. If you never fork your connection between the logistics pipes this has the same meaning as detectionLength and the lower of the two will be used";

		Property detectionFrequency = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "detectionFrequency",
				LOGISTICS_DETECTION_FREQUENCY);
		detectionFrequency.comment = "The amount of time that passes between checks to see if it is still connected to its neighbours. A low value will mean that it will detect changes faster but use more CPU. A high value means detection takes longer, but CPU consumption is reduced. A value of 20 will check about every second";

		Property countInvertWheelProperty = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "ordererCountInvertWheel",
				LOGISTICS_ORDERER_COUNT_INVERTWHEEL);
		countInvertWheelProperty.comment = "Inverts the the mouse wheel scrolling for remote order number of items";

		Property pageInvertWheelProperty = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "ordererPageInvertWheel",
				LOGISTICS_ORDERER_PAGE_INVERTWHEEL);
		pageInvertWheelProperty.comment = "Inverts the the mouse wheel scrolling for remote order pages";

		Property pageDisplayPopupProperty = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "displayPopup", DISPLAY_POPUP);
		pageDisplayPopupProperty.comment = "Set the default configuration for the popup of the Orderer Gui. Should it be used?";

		Property logisticsSignId = CONFIGURATION.getBlock("logisticsSignId",
				LOGISTICS_SIGN_ID);
		logisticsSignId.comment = "The ID of the LogisticsPipes Sign";

		Property logisticsSolidBlockId = CONFIGURATION.getBlock(
				"logisticsSolidBlockId", LOGISTICS_SOLID_BLOCK_ID);
		logisticsSolidBlockId.comment = "The ID of the LogisticsPipes Solid Block";

		Property logisticsPowerUsageDisable = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "powerUsageDisabled",
				LOGISTICS_POWER_USAGE_DISABLED);
		logisticsPowerUsageDisable.comment = "Diable the power usage trough LogisticsPipes";

		Property logisticsTileGenericReplacementDisable = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "TileReplaceDisabled",
				LOGISTICS_TILE_GENERIC_PIPE_REPLACEMENT_DISABLED);
		logisticsTileGenericReplacementDisable.comment = "Diable the Replacement of the TileGenericPipe trough the LogisticsTileGenericPipe";

		Property logisticsHUDRenderDistance = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "HUDRenderDistance",
				LOGISTICS_HUD_RENDER_DISTANCE);
		logisticsHUDRenderDistance.comment = "The max. distance between a player and the HUD that get's shown in blocks.";

		Property mandatoryCarpenterRecipes = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "mandatoryCarpenterRecipes",
				MANDATORY_CARPENTER_RECIPES);
		mandatoryCarpenterRecipes.comment = "Whether or not the Carpenter is required to craft Forestry related pipes/modules.";

		Property enableParticleFX = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "enableParticleFX",
				ENABLE_PARTICLE_FX);
		enableParticleFX.comment = "Whether or not special particles will spawn.";

		Property powerUsageMultiplyerPref = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "powerUsageMultiplyer",
				POWER_USAGE_MULTIPLIER);
		powerUsageMultiplyerPref.comment = "A Multiplyer for the power usage.";

		Property multiThread = CONFIGURATION.get(CATEGORY_MULTITHREAD,
				"enabled", MULTI_THREAD_ENABLED);
		multiThread.comment = "Enabled the Logistics Pipes multiThread function to allow the network.";

		Property multiThreadCount = CONFIGURATION.get(CATEGORY_MULTITHREAD,
				"count", MULIT_THREAD_NUMBER);
		multiThreadCount.comment = "Number of running Threads.";

		Property multiThreadPrio = CONFIGURATION.get(CATEGORY_MULTITHREAD,
				"priority", MULTI_THREAD_PRIORITY);
		multiThreadPrio.comment = "Priority of the multiThread Threads. 10 is highest, 5 normal, 1 lowest";

		LOGISTICSNETWORKMONITOR_ID = Integer
				.parseInt(logisticNetworkMonitorIdProperty.value);
		LOGISTICSREMOTEORDERER_ID = Integer
				.parseInt(logisticRemoteOrdererIdProperty.value);
		ITEM_MODULE_ID = Integer.parseInt(logisticModuleIdProperty.value);
		ITEM_UPGRADE_ID = Integer.parseInt(logisticUpgradeIdProperty.value);
		ITEM_UPGRADE_MANAGER_ID = Integer
				.parseInt(logisticUpgradeManagerIdProperty.value);
		ITEM_DISK_ID = Integer.parseInt(logisticItemDiskIdProperty.value);
		ITEM_CARD_ID = Integer.parseInt(logisticItemCardIdProperty.value);
		ITEM_HUD_ID = Integer.parseInt(logisticItemHUDIdProperty.value);
		ITEM_PARTS_ID = Integer.parseInt(logisticItemPartsIdProperty.value);

		// DEBUG (TEST) ONLY
		if (LogisticsPipes.DEBUG) {
			ITEM_LIQUID_CONTAINER_ID = Integer
					.parseInt(logisticsLiquidContainerIdProperty.value);
		}

		LOGISTICSPIPE_BASIC_ID = Integer.parseInt(logisticPipeIdProperty.value);
		LOGISTICSPIPE_REQUEST_ID = Integer
				.parseInt(logisticPipeRequesterIdProperty.value);
		LOGISTICSPIPE_PROVIDER_ID = Integer
				.parseInt(logisticPipeProviderIdProperty.value);
		LOGISTICSPIPE_CRAFTING_ID = Integer
				.parseInt(logisticPipeCraftingIdProperty.value);
		LOGISTICSPIPE_SATELLITE_ID = Integer
				.parseInt(logisticPipeSatelliteIdProperty.value);
		LOGISTICSPIPE_SUPPLIER_ID = Integer
				.parseInt(logisticPipeSupplierIdProperty.value);
		LOGISTICSPIPE_CHASSI1_ID = Integer
				.parseInt(logisticPipeChassi1IdProperty.value);
		LOGISTICSPIPE_CHASSI2_ID = Integer
				.parseInt(logisticPipeChassi2IdProperty.value);
		LOGISTICSPIPE_CHASSI3_ID = Integer
				.parseInt(logisticPipeChassi3IdProperty.value);
		LOGISTICSPIPE_CHASSI4_ID = Integer
				.parseInt(logisticPipeChassi4IdProperty.value);
		LOGISTICSPIPE_CHASSI5_ID = Integer
				.parseInt(logisticPipeChassi5IdProperty.value);
		LOGISTICSPIPE_CRAFTING_MK2_ID = Integer
				.parseInt(logisticPipeCraftingMK2IdProperty.value);
		LOGISTICSPIPE_CRAFTING_MK3_ID = Integer
				.parseInt(logisticPipeCraftingMK3IdProperty.value);
		LOGISTICSPIPE_REQUEST_MK2_ID = Integer
				.parseInt(logisticPipeRequesterMK2IdProperty.value);
		LOGISTICSPIPE_PROVIDER_MK2_ID = Integer
				.parseInt(logisticPipeProviderMK2IdProperty.value);
		LOGISTICSPIPE_REMOTE_ORDERER_ID = Integer
				.parseInt(logisticPipeRemoteOrdererIdProperty.value);
		LOGISTICSPIPE_APIARIST_ANALYSER_ID = Integer
				.parseInt(logisticPipeApiaristAnalyserIdProperty.value);
		LOGISTICSPIPE_APIARIST_SINK_ID = Integer
				.parseInt(logisticPipeApiaristSinkIdProperty.value);
		LOGISTICSPIPE_ENTRANCE_ID = Integer
				.parseInt(logisticEntranceIdProperty.value);
		LOGISTICSPIPE_DESTINATION_ID = Integer
				.parseInt(logisticDestinationIdProperty.value);
		LOGISTICSPIPE_INVSYSCON_ID = Integer
				.parseInt(logisticInvSysConIdProperty.value);
		LOGISTICS_SIGN_ID = Integer.parseInt(logisticsSignId.value);
		LOGISTICS_SOLID_BLOCK_ID = Integer
				.parseInt(logisticsSolidBlockId.value);
		LOGISTICSPIPE_FIREWALL_ID = logisticPipeFirewallIdProperty.getInt();

		// DEBUG (TEST) ONLY (LIQUID)
		if (LogisticsPipes.DEBUG) {
			LOGISTICSPIPE_LIQUID_CONNECTOR = logisticPipeLiquidConnectorIdProperty
					.getInt();
			LOGISTICSPIPE_LIQUID_BASIC = logisticPipeLiquidBasicIdProperty
					.getInt();
			LOGISTICSPIPE_LIQUID_INSERTION = logisticPipeLiquidInsertionIdProperty
					.getInt();
			LOGISTICSPIPE_LIQUID_PROVIDER = logisticPipeLiquidProviderIdProperty
					.getInt();
			LOGISTICSPIPE_LIQUID_REQUEST = logisticPipeLiquidRequestIdProperty
					.getInt();
		}

		LOGISTICS_DETECTION_LENGTH = Integer.parseInt(detectionLength.value);
		LOGISTICS_DETECTION_COUNT = Integer.parseInt(detectionCount.value);
		LOGISTICS_DETECTION_FREQUENCY = Math.max(
				Integer.parseInt(detectionFrequency.value), 1);
		LOGISTICS_ORDERER_COUNT_INVERTWHEEL = Boolean
				.parseBoolean(countInvertWheelProperty.value);
		LOGISTICS_ORDERER_PAGE_INVERTWHEEL = Boolean
				.parseBoolean(pageInvertWheelProperty.value);

		LOGISTICS_POWER_USAGE_DISABLED = Boolean
				.parseBoolean(logisticsPowerUsageDisable.value);
		LOGISTICS_TILE_GENERIC_PIPE_REPLACEMENT_DISABLED = Boolean
				.parseBoolean(logisticsTileGenericReplacementDisable.value);

		LOGISTICSCRAFTINGSIGNCREATOR_ID = Integer
				.parseInt(logisticCraftingSignCreatorIdProperty.value);

		LOGISTICS_HUD_RENDER_DISTANCE = Integer
				.parseInt(logisticsHUDRenderDistance.value);

		DISPLAY_POPUP = Boolean.parseBoolean(pageDisplayPopupProperty.value);

		LOGISTICSPIPE_BUILDERSUPPLIER_ID = Integer
				.parseInt(logisticPipeBuilderSupplierIdProperty.value);
		LOGISTICSPIPE_LIQUIDSUPPLIER_ID = Integer
				.parseInt(logisticPipeLiquidSupplierIdProperty.value);
		MANDATORY_CARPENTER_RECIPES = Boolean
				.parseBoolean(mandatoryCarpenterRecipes.value);
		ENABLE_PARTICLE_FX = Boolean.parseBoolean(enableParticleFX.value);

		MULTI_THREAD_ENABLED = multiThread.getBoolean(MULTI_THREAD_ENABLED);
		MULIT_THREAD_NUMBER = multiThreadCount.getInt();
		if (MULIT_THREAD_NUMBER < 1) {
			MULIT_THREAD_NUMBER = 1;
			multiThreadCount.value = Integer.toString(MULIT_THREAD_NUMBER);
		}
		MULTI_THREAD_PRIORITY = multiThreadPrio.getInt();
		if (MULTI_THREAD_PRIORITY < 1 || MULTI_THREAD_PRIORITY > 10) {
			MULTI_THREAD_PRIORITY = Thread.NORM_PRIORITY;
			multiThreadPrio.value = Integer.toString(Thread.NORM_PRIORITY);
		}

		POWER_USAGE_MULTIPLIER = powerUsageMultiplyerPref.getInt();

		if (POWER_USAGE_MULTIPLIER < 1) {
			POWER_USAGE_MULTIPLIER = 1;
			powerUsageMultiplyerPref.value = "1";
		}

		CONFIGURATION.save();
	}

	public static void savePopupState() {
		Property pageDisplayPopupProperty = CONFIGURATION.get(
				Configuration.CATEGORY_GENERAL, "displayPopup", DISPLAY_POPUP);
		pageDisplayPopupProperty.comment = "Set the default configuration for the popup of the Orderer Gui. Should it be used?";
		pageDisplayPopupProperty.value = Boolean.toString(DISPLAY_POPUP);
		CONFIGURATION.save();
	}
}
