package top.xfunny;

import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mod.block.BlockTicketMachine;
import top.xfunny.block.*;


public class Blocks {


    public static final BlockRegistryObject TEST_LIFT_BUTTONS;
    public static final BlockRegistryObject TEST_LIFT_BUTTONS_WITHOUT_SCREEN;
    public static final BlockRegistryObject TEST_LIFT_HALL_LANTERNS;
    public static final BlockRegistryObject TEST_LIFT_PANEL;
    public static final BlockRegistryObject TEST_LIFT_DESTINATION_DISPATCH_TERMINAL;

    public static final BlockRegistryObject OTIS_SERIES_1_BUTTON_1;
    public static final BlockRegistryObject OTIS_SERIES_1_BUTTON_2;
    public static final BlockRegistryObject OTIS_SERIES_1_SCREEN_1;

    public static final BlockRegistryObject MITSUBISHI_NEXWAY_BUTTON_1;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_BUTTON_1_WITHOUT_SCREEN;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_BUTTON_2;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_BUTTON_2_WITHOUT_SCREEN;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_BUTTON_3;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_BUTTON_3_WITHOUT_SCREEN;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_SCREEN_1_EVEN;
    public static final BlockRegistryObject MITSUBISHI_NEXWAY_SCREEN_1_ODD;

    public static final BlockRegistryObject SCHINDLER_D_SERIES_D2BUTTON;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_1_ODD;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_1_EVEN;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_2_GREEN_EVEN;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_2_BLUE_EVEN;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_2_RED_EVEN;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_2_GREEN_ODD;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_2_BLUE_ODD;
    public static final BlockRegistryObject SCHINDLER_D_SERIES_SCREEN_2_RED_ODD;
    public static final BlockRegistryObject SCHINDLER_M_SERIES_BUTTON;
    public static final BlockRegistryObject SCHINDLER_M_SERIES_TOUCH_BUTTON;
    public static final BlockRegistryObject SCHINDLER_S_SERIES_GREY_BUTTON;
    public static final BlockRegistryObject SCHINDLER_M_SERIES_SCREEN_1;
    public static final BlockRegistryObject SCHINDLER_M_SERIES_SCREEN_2_ODD;
    public static final BlockRegistryObject SCHINDLER_M_SERIES_SCREEN_2_EVEN;
    public static final BlockRegistryObject SCHINDLER_Z_LINE_3_KEYPAD_1;

    public static final BlockRegistryObject HITACHI_B85_BUTTON_1;

    public static final BlockRegistryObject PAT_P01_TICKET_BARRIER_ENTRANCE;
    public static final BlockRegistryObject PAT_P01_TICKET_BARRIER_EXIT;
    public static final BlockRegistryObject PAT_P01_TICKET_BARRIER_BARE;
    public static final BlockRegistryObject PAT_TM01_TICKET_MACHINE;

    public static final BlockRegistryObject LIFT_TRACK_EMPTY_FLOOR;

    public static final BlockRegistryObject MITSUBISHI_NEXWAY_DOOR_1;
    public static final BlockRegistryObject SCHINDLER_QKS9_DOOR_1;


    static {

        TEST_LIFT_BUTTONS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "test_lift_buttons"), () -> new Block(new TestLiftButtons()));
        MITSUBISHI_NEXWAY_BUTTON_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_button_1"), () -> new Block(new MitsubishiNexWayButton1()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_BUTTON_1_WITHOUT_SCREEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_button_1_without_screen"), () -> new Block(new MitsubishiNexWayButton1WithoutScreen()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_BUTTON_2 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_button_2"), () -> new Block(new MitsubishiNexWayButton2()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_BUTTON_2_WITHOUT_SCREEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_button_2_without_screen"), () -> new Block(new MitsubishiNexWayButton2WithoutScreen()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_BUTTON_3 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_button_3"), () -> new Block(new MitsubishiNexWayButton3()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_BUTTON_3_WITHOUT_SCREEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_button_3_without_screen"), () -> new Block(new MitsubishiNexWayButton3WithoutScreen()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_SCREEN_1_EVEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_screen_1_even"), () -> new Block(new MitsubishiNexWayScreen1Even()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        MITSUBISHI_NEXWAY_SCREEN_1_ODD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "mitsubishi_nexway_screen_1_odd"), () -> new Block(new MitsubishiNexWayScreen1Odd()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        TEST_LIFT_HALL_LANTERNS = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "test_lift_hall_lanterns"), () -> new Block(new TestLiftHallLanterns()));
        TEST_LIFT_PANEL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "test_lift_panel"), () -> new Block(new TestLiftPanel()));
        TEST_LIFT_BUTTONS_WITHOUT_SCREEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "test_lift_buttons_without_screen"), () -> new Block(new TestLiftButtonsWithoutScreen()));
        TEST_LIFT_DESTINATION_DISPATCH_TERMINAL = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "test_lift_destination_dispatch_terminal"), () -> new Block(new TestLiftDestinationDispatchTerminal()));

        OTIS_SERIES_1_BUTTON_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "otis_series_1_button_1"), () -> new Block(new OtisSeries1Button()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        OTIS_SERIES_1_BUTTON_2 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "otis_series_1_button_2"), () -> new Block(new OtisSeries1Button()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        OTIS_SERIES_1_SCREEN_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "otis_series_1_screen_1"), () -> new Block(new OtisSeries1Screen()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_D2BUTTON = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_d2button"), () -> new Block(new SchindlerDSeriesD2Button()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_1_ODD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_1_odd"), () -> new Block(new SchindlerDSeriesScreen1Odd()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_1_EVEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_1_even"), () -> new Block(new SchindlerDSeriesScreen1Even()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_2_GREEN_EVEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_2_green_even"), () -> new Block(new SchindlerDSeriesScreen2GreenEven()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_2_GREEN_ODD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_2_green_odd"), () -> new Block(new SchindlerDSeriesScreen2GreenOdd()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_2_BLUE_EVEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_2_blue_even"), () -> new Block(new SchindlerDSeriesScreen2BlueEven()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_2_BLUE_ODD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_2_blue_odd"), () -> new Block(new SchindlerDSeriesScreen2BlueOdd()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_2_RED_EVEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_2_red_even"), () -> new Block(new SchindlerDSeriesScreen2RedEven()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_D_SERIES_SCREEN_2_RED_ODD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_d_series_screen_2_red_odd"), () -> new Block(new SchindlerDSeriesScreen2RedOdd()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_M_SERIES_BUTTON = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_m_series_button"), () -> new Block(new SchindlerMSeriesButton()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_M_SERIES_TOUCH_BUTTON = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_m_series_touch_button"), () -> new Block(new SchindlerMSeriesTouchButton()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_M_SERIES_SCREEN_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_m_series_screen_1"), () -> new Block(new SchindlerMSeriesScreen1()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_M_SERIES_SCREEN_2_ODD = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_m_series_screen_2_odd"), () -> new Block(new SchindlerMSeriesScreen2Odd()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_M_SERIES_SCREEN_2_EVEN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_m_series_screen_2_even"), () -> new Block(new SchindlerMSeriesScreen2Even()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_S_SERIES_GREY_BUTTON = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_s_series_grey_button"), () -> new Block(new SchindlerSSeriesGreyButton()), CreativeModeTabs.YTE_LIFT_FIXTURES);
        SCHINDLER_Z_LINE_3_KEYPAD_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "schindler_z_line_3_keypad_1"), () -> new Block(new SchindlerZLine3Keypad1()), CreativeModeTabs.YTE_LIFT_FIXTURES);

        HITACHI_B85_BUTTON_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "hitachi_b85_button_1"), () -> new Block(new HitachiB85Button1()), CreativeModeTabs.YTE_LIFT_FIXTURES);

        PAT_P01_TICKET_BARRIER_ENTRANCE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "pat_p01_ticket_barrier_entrance"), () -> new Block(new PATTicketBarrier(true)), CreativeModeTabs.YTE_RAILWAY_FACILITIES);
        PAT_P01_TICKET_BARRIER_EXIT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "pat_p01_ticket_barrier_exit"), () -> new Block(new PATTicketBarrier(false)), CreativeModeTabs.YTE_RAILWAY_FACILITIES);
        PAT_P01_TICKET_BARRIER_BARE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "pat_p01_ticket_barrier_bare"), () -> new Block(new PATTicketBarrierBareBlock(BlockHelper.createBlockSettings(false,false).strength(4.0f).nonOpaque())), CreativeModeTabs.YTE_RAILWAY_FACILITIES);
        PAT_TM01_TICKET_MACHINE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "pat_tm01_ticket_machine"), () -> new Block(new BlockTicketMachine(BlockHelper.createBlockSettings(true, false, blockState -> 5))), CreativeModeTabs.YTE_RAILWAY_FACILITIES);

        LIFT_TRACK_EMPTY_FLOOR = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "lift_track_empty_floor"), () -> new Block(new EmptyFloor()));

        MITSUBISHI_NEXWAY_DOOR_1 = Init.REGISTRY.registerBlock(new Identifier(Init.MOD_ID, "mitsubishi_nexway_door_1"), () -> new Block(new MitsubishiNexWayDoor1()));
        SCHINDLER_QKS9_DOOR_1 = Init.REGISTRY.registerBlock(new Identifier(Init.MOD_ID, "schindler_qks9_door_1"), () -> new Block(new SchindlerQKS9Door1()));
    }


    public static void init() {
        Init.LOGGER.info("正在注册方块");
    }
}
