package com.ibm.watsonwork;

public class WatsonWorkConstants {

    public static final String CLIENT_CREDENTIALS = "client_credentials";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    public static final String BASIC = "Basic ";
    public static final String BEARER = "Bearer ";
    public static final String X_OUTBOUND_TOKEN = "X-OUTBOUND-TOKEN";

    public static final String WATSONWORK_AUTH_URI_KEY = "watsonworkAuthUri";
    public static final String RESPONSE_TYPE_KEY = "responseType";
    public static final String CLIENT_ID_KEY = "clientId";
    public static final String REDIRECT_URI_KEY = "redirectUri";
    public static final String STATE_KEY = "state";
    public static final String CODE_VALUE = "code";
    public static final String STATE_VALUE = "123456678";
    public static final String CALLBACK_TEMPLATE = "callback";
    public static final String INDEX_TEMPLATE = "index";
    public static final String NAME_KEY = "name";
    public static final String COOKIE_ID_VALUE = "id";

    public static final String HTTPS_OAUTH_CALLBACK = "https://%s/oauthCallback";

    public static final String HELP_TEXT  = "JIRA Integration for Watson Work supports the following:\n" +
            "\n" +
            "JIRA App for Watson Workspace allows you to look for issues by priority and assign it to JIRA users.\n" +
            "The app will reflect those changes to your JIRA system. (https://watsonworkspace.atlassian.net/) \n" +
            "\n" +
            "*Fetch issues by the following status:*\n" +
            "- Highest\n" +
            "- High\n" +
            "- Medium\n" +
            "- Low\n" +
            "- Lowest\n" +
            "\n" +
            "*Issues status:*\n" +
            "- Assigned\n" +
            "- Unassigned\n" +
            "\n" +
            "*Example intents:*\n" +
            "- show me all issues with low priority \n" +
            "  (Replace *low*  with any of the status above)\n" +
            "\n" +
            "- what issues are currently unassigned with highest priority?  \n" +
            "  (Replace *highest*  with any of the status above, Replace *unassigned* with Assigned)" +
            "\n" +
            "*Same info is accessible via /jira ...*";

    public static final String SOURCE_TEXT = "*You can replace `CNN` (news source) with any of the following:*\n" +
            "\n" +
            "```\n" +
            "Financial Times, Ars Technica, The Huffington Post, Reuters, Daily Mail\n" +
            "CNN, BBC News, The Guardian UK, IGN, ESPN, Independent, The Verge, Engadget\n" +
            "Bloomberg, TechCrunch, Hacker News, The Wall Street Journal, BBC Sport, ESPN Cric Info,\n" +
            "Metro, The Economist, Buzzfeed, Football Italia, NFL News, Reddit r/all,\n" +
            "The New York Times, Al Jazeera English, Associated Press, CNBC, Google News\n" +
            "```\n";


}
