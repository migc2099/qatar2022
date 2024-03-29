package com.migc.qatar2022.common

object Constants {

    const val PREFERENCES_NAME = "qatar_preferences"
    const val PREFERENCES_GROUPS_FIXTURE_KEY = "groups_fixture_key"
    const val PREFERENCES_STANDINGS_KEY = "standings_key"
    const val PREFERENCES_GROUPS_KEY = "groups_key"
    const val PREFERENCES_TEAMS_KEY = "teams_key"
    const val PREFERENCES_WINNERS_UPLOAD_ACTION_KEY = "winners_upload_action_key"

    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"
    const val NODE_PREDICTIONS = "predictions"
    const val FIELD_FIRST = "first_place"
    const val FIELD_SECOND = "second_place"
    const val FIELD_THIRD = "third_place"

    const val TOP_PREDICTIONS_NUMBER: Long = 5

    const val GOOGLE_PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=com.migc.qatar2022"
    const val INSTAGRAM_ID = "migc.dev"

    //    const val RANKINGS_BASE_URL = "https://gist.githubusercontent.com/"
    const val TEAMS_RANKINGS_JSON_FILE = "teams_rankings.json"

    const val QATAR_DATABASE = "qatar_database"
    const val STANDINGS_TABLE = "standings_table"
    const val PLAYOFFS_TABLE = "playoffs_table"
    const val GROUPS_TABLE = "groups_table"
    const val TEAMS_TABLE = "teams_table"
    const val FIXTURE_TABLE = "fixture_table"
    const val GROUP_A_KEY = "A"
    const val GROUP_B_KEY = "B"
    const val GROUP_C_KEY = "C"
    const val GROUP_D_KEY = "D"
    const val GROUP_E_KEY = "E"
    const val GROUP_F_KEY = "F"
    const val GROUP_G_KEY = "G"
    const val GROUP_H_KEY = "H"

    const val GROUP_STAGE = 3
    const val ROUND_OF_16_STAGE = 4
    const val ROUND_OF_8_STAGE = 5
    const val SEMI_FINAL_STAGE = 6
    const val FINAL_STAGE = 7

    const val TOTAL_GROUP_MATCHES = 48
    const val TOTAL_GROUPS = 8
    const val TOTAL_TEAMS = 32

    const val WORLD_CUP_START_DATE_MILLIS = 1668960000000
    const val NEXT_WORLD_CUP_START_DATE_MILLIS = 1780902000000

    const val PARAM_GROUP_ID = "group_id"

    const val SIGN_IN_SUCCESS_MESSAGE = "sign_in_success_message"
    const val SIGN_OUT_SUCCESS_MESSAGE = "sign_out_success_message"
    const val UPLOAD_COMPLETED_MESSAGE = "upload_completed_message"
    const val UNEXPECTED_EXCEPTION_ERROR_MESSAGE = "unexpected_exception_error_message"
    const val SIGN_IN_EXCEPTION_TRY_AGAIN_MESSAGE = "sign_in_exception_try_again_message"
    const val CONNECTION_EXCEPTION_ERROR_MESSAGE = "connection_exception_error_message"
    const val SIGN_IN_REQUIRED_MESSAGE = "sign_in_required_message"
    const val GETTING_DATA_ERROR_MESSAGE = "getting_data_error_message"
    const val PERMISSION_DENIED_MESSAGE = "PERMISSION_DENIED: Missing or insufficient permissions"

    const val WARN_NUMBER_LOGIN_ATTEMPTS = 7
    const val TIMES_CLICKED_TO_SHOW_AD = 10

    const val AD_UNIT_SUBMIT_INTERSTITIAL_ID = "ad_unit_submit_interstitial_id"
    const val AD_UNIT_MAP_REWARD_ID = "ad_unit_map_reward_id"
    const val AD_UNIT_MAP_INTERSTITIAL_ID = "ad_unit_map_interstitial_id"
    const val AD_UNIT_LOGIN_INTERSTITIAL_ID = "ad_unit_login_interstitial_id"
}